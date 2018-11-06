$(function () {	
	initial();	
});

function initial(){	
	setRouter();
}
function showMainContent(){
	$('#main-content').empty();
	buildTemplate('tpl_page_main',null,'main-content');
	
	showDataList();
	setBindMain();	
}
function showDataList(){
	var listOBJ = {
		content : {
			token : 'token'
		}
	};
	var arr = doListSubmit(listOBJ);

	var obj = {
		arr : arr
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
	$("#table_list").unbind("click.turnoff");
    $("#table_list").on("click.turnoff", ".turnoff", function(event){ 
    	console.log($(this));
    	var id = $(this).attr('value');
    	var name = $('#name_' + id).text();
    	$('#name_' + id).attr('style','display:none');
        $('#name_modify_' + id).html('<input name="input_name_'+ id +'" value="'+ description +'"/>');
    });

    $("#table_list").unbind("click.turnon");
    $("#table_list").on("click.turnon", ".turnon", function(event){ 
    
    });

    //修改
    $("#table_list").unbind("click.edit");
    $("#table_list").on("click.edit", ".edit", function(event){ 
       	console.log($(this));
    	var id = $(this).attr('value');
    	var name = $('#name_' + id).text();
    	var description = $('#description_' + id).text();
    	$('#name_' + id).attr('style','display:none');
        $('#name_modify_' + id).html('<input name="input_name_'+ id +'" value="'+ name +'"/>');
        $('#description_' + id).attr('style','display:none');
        $('#description_modify_' + id).html('<input name="input_description_'+ id +'" value="'+ description +'"/>');

        $('#edit_' + id).attr('style','display:none');
        $('#check_' + id).attr('style','display');
        $('#delete_' + id).attr('style','display:none');
        $('#turnoff_' + id).attr('style','display:none');
        $('#turnoff_' + id).attr('style','display:none');
    });

    //修改
    $("#table_list").unbind("click.check");
    $("#table_list").on("click.check", ".check", function(event){ 
    	var id = $(this).attr('value');
		var OBJ = {
			content : {
				token : 'token',
				id : id,
				name : $('input[name=input_name_'+ id +']').val(),
				status : $('#status_'+ id).val(),
				description : $('input[name=input_description_'+ id +']').val()
			}
		}
		doUpdateSubmit(OBJ,id);    	
    });

    //刪除
    $("#table_list").unbind("click.delete");
    $("#table_list").on("click.delete", ".delete", function(event){ 
    	if(confirm("確認要刪除?")){
    		var OBJ = {
    			content : {
					token : 'token',
					id : $(this).attr('value')
				}
    		}
    		doDeleteSubmit(OBJ);
    	}
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
function doListSubmit(_data){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/category/list',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				data = obj.data;
				console.log(obj);
			}else{
				alert(obj.message)
				console.log(obj.message);	
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
function doUpdateSubmit(_data,_ID){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/category/update',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				console.log(obj);
				handleUpdateSuccess(_ID);
			}else{
				alert(obj.message)
				console.log(obj.message);
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
function doDeleteSubmit(_data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/location/category/delte',
		data: JSON.stringify(_data),
		success: function(obj){			
			if(obj.status==0){
				console.log(obj);
			}else{
				alert(obj.message)
				console.log(obj.message);	
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
function handleUpdateSuccess(_ID){
	var description = $('input[name=input_description_' + _ID +']').val();
	var name = $('input[name=input_name_' + _ID +']').val();
	$('#description_' + _ID).text(description);
	$('#name_' + _ID).text(description);
	$('#name_' + _ID).attr('style','display');
    $('#name_modify_' + _ID).html('');
    $('#description_' + _ID).attr('style','display');
    $('#description_modify_' + _ID).html('');

    $('#edit_' + _ID).attr('style','display');
    $('#check_' + _ID).attr('style','display:none');
    $('#delete_' + _ID).attr('style','display');
    $('#turnoff_' + _ID).attr('style','display');
    $('#turnoff_' + _ID).attr('style','display');
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

