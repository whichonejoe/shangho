$(function () {	
	initial();	
});

var token = null;

function initial(){	
	handleCheckLogin();
	setRouter();
}
function handleCheckLogin(){	
	token = window.localStorage.getItem('hmitoken');
	if(!checkObj(token)){
		window.location.href="login.jsp";
	}
}
function showMainContent(){
	$('#main-content').empty();
	buildTemplate('tpl_page_main',null,'main-content');
	
	showDataList();
	setBindMain();	
}
function showDataList(){
	var obj = {
		arr : getLogSetting({'token':token})
	};

	buildTemplate('tpl_mode_list',obj,'table_list');

	$("[data-toggle='tooltip']").tooltip();

	$('#data-table').DataTable({
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"ordering": false,
		"info": false,
		"autoWidth": true,
		"displayLength": 10
    });
}
function setBindMain(){
	$("#table_list").unbind("click.log-turnoff");
    $("#table_list").on("click.log-turnoff", ".log-turnoff", function(event){ 
        // if(confirm('Are you sure?')){
        	var name = $(this).attr('value');
        	doSubmit({
        		name :  name,
        		token : token,
        		isenable : false
        	});
        	// return;
        // }
    });

    $("#table_list").unbind("click.log-turnon");
    $("#table_list").on("click.log-turnon", ".log-turnon", function(event){ 
        // if(confirm('Are you sure?')){
        	var name = $(this).attr('value');
        	doSubmit({
        		name :  name,
        		token : token,
        		isenable : true
        	});
        	// return;
        // }
    });
}
function setRouter(){
	var routes = {		
		'':showMainContent()
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showMainContent();
  	};
    router.init('');
}
function buildTemplate(templateId,_obj,pageId){
	var tpl_code = $('#' + templateId).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function buildTemplateMultipleHtml(tpl_code,_obj,pageId){
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function doRefreshToken(data){
	var newToken = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/token/refresh',
		data: JSON.stringify(data),
		success: function(obj){			
			if(obj.status==0){
				newToken = obj.data;	
			}else{
				handleCheckLogin();	
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
			handleCheckLogin();
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return newToken;
}
function sortById(array){
	array.sort(function(objA, objB){
        if (objA.id > objB.id) {
	    	return 1;
	  	}
  		if (objA.id < objB.id) {
	    	return -1;
	  	}
	  	return 0;
    });
    return array;
}
function checkObj(_obj){
	if(_.isNull(_obj)){
		return false;
	}
	if(_.isUndefined(_obj)){
		return false;
	}
	if(_.isNaN(_obj)){
		return false;
	}
	if(_obj.length<=0){
		return false;
	}
	return true;
}
function handleRefreshToken(callback){
	var obj ={
		"token" : token
	}	
	token = doRefreshToken(token);
	window.localStorage.setItem('hmitoken',token);
	
	if(!checkObj(token)){
		window.localStorage.removeItem('hmitoken');
		handleCheckLogin();
	}else{
		if(callback!=null){
			callback();
		}
	}
}
function getLogSetting(_data){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/log/setting/list',
		data: JSON.stringify(_data),
		success: function(obj){
			if(obj.status==0){			
				data = obj.data
			}else if(obj.status==2001){
				handleRefreshToken();
				_data.token = token;
				data = getLogSetting(_data);
			}else{
				window.localStorage.removeItem('hmitoken');
				alert("fail("+obj.message+").");
				handleCheckLogin();
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return data;
}
function doSubmit(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/log/setting/update',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status==0){			
				window.history.replaceState(null,null,'#');
				showMainContent();
			}else if(obj.status==2001){
				handleRefreshToken();
				data.token = token;
				doSubmit(data);
			}else{
				window.localStorage.removeItem('hmitoken');
				alert("fail("+obj.message+").");
				handleCheckLogin();
			}
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}

