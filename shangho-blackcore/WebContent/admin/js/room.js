$(function () {
	initial();
});

function initial(){
	setRouter();
}

function showMainContent(){
	var tpl_code = $('#tpl_page_main').html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var html = tpl_bin();
	
	//清空
	$('#main-content').empty();
	$('#main-content').html(html);
	showDataList();
	
}
function showDataList(){	
	var arr = dataProcess();
	var roomModuleArr = getRoomModuleData();

	var obj = {
		'arr' : arr,
		'roomModuleArr':roomModuleArr
	}
	
	buildTemplate('tpl_room_list',obj,'table_list');

	$("[data-toggle='tooltip']").tooltip();

	$('#data-table').DataTable({
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"ordering": true,
		"info": false,
		"autoWidth": true,
		"displayLength": 20
		// "aoColumns": [
		// 	null,
		// 	{ "bSortable": false },
		// 	{ "bSortable": false }
		// ]
    });
   	setBindMain();
}
function showAdd(){
	var roomModuleArr = getRoomModuleData();
	
	var obj = {
		'roomModuleArr' : roomModuleArr
	}

	$('#main-content').empty();
	
	buildTemplate('tpl_room_add',obj,'main-content');

	selectPrcoess(roomModuleArr[0].name);
	setBindAdd();
}
function selectPrcoess(_module){
	var roomArr = getRoomData();

	var selectObj = {
		'module' : $.trim(_module),
		'selectArr' : roomArr
	}

	buildTemplate('tpl_multiple_select',selectObj,'multiple-select');

	$('#multiple_select').bootstrapDualListbox({
		nonSelectedListLabel: 'Non-selected',
		selectedListLabel: 'Selected',
		preserveSelectionOnMove: 'moved',
		moveOnSelect: false ,
		showFilterInputs :false
	});
}
function setBindMain(){
    //module change
	$("#table_list").unbind("click.room-module");
	$("#table_list").on("click.room-module", ".room-module", function(event){ 
		if(confirm("確定要修改?")){
			$('#module').text($(this).text());
	    	$('#module').attr('data-code',$(this).attr('data-code'));
	    	var obj = {
	    		'roomno' : $(this).attr('data-room-no'),
	    		'groupid' : $(this).attr('data-code')
	    	}
	    	updateModuleSubmit(obj);
		}
    });
}
function setBindAdd(){
	//module change
	$("#main-content").unbind("click.room-module");
	$("#main-content").on("click.room-module", ".room-module", function(event){ 
    	$('#module').text($(this).text());
    	$('#module').attr('data-code',$(this).attr('data-code'));
    	selectPrcoess($(this).text());
    });

    $("#main-content").unbind("click.submit-add");
	$("#main-content").on("click.submit-add", "#submit-add", function(event){ 
		submitAddProcess();
    });
}
function setRouter(){
	var routes = {		
		'add': showAdd,
		'':showMainContent()
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showMainContent();
  	};
    router.init('');
}
function getData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/roominfo',
		// data: data,
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			data = obj.data;
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
function dataProcess(){
	var data = getData();
 	var arr = [];
	if(!_.isNull(data)){
		for(var i=0;i<data.length;i++){
			var sos = 'N';
			var dnd = 'N';
			var mur = 'N';
			var card = 'N';
			var hvac = 'N';
			var rcu = 'N';
			var rcuTime = null;
			var person = 'N';//暫無這個功能(房間內是否有人)

			// process about Service info
			if(!_.isNull(data[i]['service']) && !_.isUndefined(data[i]['service'])){
				for(var x=0;x<data[i]['service'].length;x++){
					var tempStr = data[i]['service'][x]['keycode'];
					if(tempStr=='SOS'){
						sos = 'Y';
						break;
					}
					if(tempStr=='MUR'){
						mur = 'Y';
						break;
					}
					if(tempStr=='DND'){
						dnd = 'Y';
						break;
					}
				}
			}

			if(!_.isNull(data[i]['card']) && !_.isUndefined(data[i]['card'])){
				if(data[i]['card']['status']){
					card = 'Y';
				}
			}

			if(!_.isNull(data[i]['hvac']) && !_.isUndefined(data[i]['hvac'])){
				//頁面 暫時只顯示有無空調 2016/12/21
				for(var x=0;x<data[i]['hvac'].length;x++){
					hvac = 'Y';
					break;
				}
			}
			
			if(!_.isNull(data[i]['rcu']) && !_.isUndefined(data[i]['rcu'])){
				rcu = 'Y';
				var tempTime = dateTimeProcess(data[i]['rcu'].time);
				if(tempTime<=0){
					rcuTime = 'heartbeat';
				}else{
					rcuTime = dateTimeProcess(data[i]['rcu'].time)  + ' minute ago';
				}
			}

			// var roomModuleObj = {
			// 	'moduleName' : ''
			// }
			// var roomModuleArr = [];

			// roomModuleArr.push(roomModuleObj);
			// roomModuleArr.push(roomModuleObj);

			var roomObj = {
				'roomNo' : data[i]["roomNo"],
				'isCheckin' : data[i]["isCheckin"]==0?'N':'Y',
				'sosStatus' : sos,
				'cardStatus' : card,
				'hvacStatus' : hvac,
				'rcuStatus': rcu,
				'personStatus' : person,
				'roomModule' : data[i]["type"],
				'rcuTime':rcuTime,
				'dndStatus': dnd,
				'murStatus':mur
			}
			arr.push(roomObj)
		}
	}

	return arr;
}
function dateTimeProcess(_starTime){

	Date.prototype.dateDiff = function(interval,objDate){
	    var dateEnd = new Date(objDate);
	    if(isNaN(dateEnd)) return undefined;
	    switch (interval) {
	        case "s":return parseInt((dateEnd - this) / 1000);
	        case "n":return parseInt((dateEnd - this) / 60000);
	        case "h":return parseInt((dateEnd - this) / 3600000);
	        case "d":return parseInt((dateEnd - this) / 86400000);
	        case "w":return parseInt((dateEnd - this) / (86400000 * 7));
	        case "m":return (dateEnd.getMonth()+1)+((dateEnd.getFullYear()-this.getFullYear())*12) - (this.getMonth()+1);
	        case "y":return dateEnd.getFullYear() - this.getFullYear();
	    }
	}

	var starTime = new Date(_starTime);
	var endTime = new Date();

	return starTime.dateDiff("n",endTime);
}
function buildTemplate(templateId,_obj,pageId){
	var tpl_code = $('#' + templateId).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function submitAddProcess(){
	var moduleId = $('#module').attr('data-code');

	var roomArr = [];
	$('select[name="_helper2"] option').each(function() {
	    roomArr.push($(this).val());
	});

	var obj = {
		"groupid": moduleId,
		"rooms": roomArr
    }

    doSubmitAdd(obj);
}
//取得房型資料
function getRoomModuleData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/listrcugroup',
		// data: 'data',
		success: function(obj){
			data = obj.data;
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});

	return sortById(data);
}
//取得房間資訊
function getRoomData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/allroominfo',
		// data: 'data',
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			data = obj.data;
		},
		failure: function(errMsg) {
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return sortByfloor(data);
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
function sortByfloor(array){
	array.sort(function(objA, objB){
		var floorA = parseInt(objA.floor);
		var floorB = parseInt(objB.floor);

        if (floorA > floorB) {
	    	return 1;
	  	}
  		if (floorA < floorB) {
	    	return -1;
	  	}
	  	return 0;
    });
    return array;
}
function updateModuleSubmit(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/roommodule/update',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			alert('success');
			window.history.replaceState(null,null,'#');
			showMainContent();
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function doSubmitAdd(data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/roommodule/insert',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			alert('success');
			window.history.replaceState(null,null,'#');
			showMainContent();
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}