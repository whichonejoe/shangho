$(function () {	
	initial();	
});

var groupData = null;
var roomRCUData = null;
var websocket = null;
var connectionCount = 0;
var originStatus = null;
var checkAmount = 5;
var sendCommandAmount = 0;
var token = null;
var originalKeycodeArr = [];
var requestStatus = null;
var isAllTest = false;
var deviceAmount = 0;
var isPassDeviceArr = [];

function initial(){	
	handleCheckLogin();

	setRouter();

	groupData = handleGroupDevice(getGroupData());

	roomRCUData = getRoomInfoData();
}
function handleCheckLogin(){	
	token = window.localStorage.getItem('hmitoken');
	if(!checkObj(token)){
		window.location.href="login.jsp";
	}
}
function showIndex(){
	var obj = {
		
	}

	$('#main-content').empty();

	buildTemplate('tpl_page_add',obj,'main-content');
	buildTemplate('tpl_index',obj,'body-model');
	
	setBindAdd();

	// $('#enter-group').attr('style','display:none');
	
	roomTabProcess();
}
function handleStoreOriginalStatus(_roomNo,_keycode,_status){
	var isExist = false;
	$.each(originalKeycodeArr, function(key,element) {
		if(element.keycode == _keycode && element.roomNo == _roomNo){
			element.status = _status;
		}
	});

	if(!isExist){
		var obj = {
			roomNo : _roomNo,
			keycode : _keycode,
			status : _status
		}
		originalKeycodeArr.push(obj);
	}
}
function handleCheckRequest(_roomNo,_keycode,_status,_groupid){
	if(!checkObj(_roomNo)){
		return false;
	}
	if(!checkObj(_keycode)){
		return false;
	}
	if(!checkObj(_status)){
		return false;
	}
	if(!checkObj(_groupid)){
		return false;
	}
	return true;
}
function handleSubmitCommand(){
	isPassDeviceArr = [];
	handleCheckLogin();
	handleWebsocketClose();

	cleanSetTimeOut();

	var roomNo = $('#hiddenRoomNo').val();
	var group = $('#displayGroup').text();
	var keycode = $('#displayDevice').text();
	var status = $('input[name=function]:checked').val();
	var groupid = $('#displayGroup').attr('data-index');

	if(!handleCheckRequest(roomNo,keycode,status,groupid)){
		return false;
	}
	
	checkAmount = parseInt($('#returnCount').text());
	sendCommandAmount = 0;

	requestStatus = status;

	cleanMessage();
	clearDialogueBox();

	handleDialogueBoxWithDifferentRoomNo();

	if(group=="All"){
		isAllTest = true;
		handleAllTest(roomNo,status);
		return ;
	}else{
		isAllTest = false;
	}

	var originalStatus = handleGetOriginalStatus(roomNo,groupid,keycode,status);

	handleDialogueBoxMessage('房號 : '+ roomNo +' 開始測試.');
	handleDialogueBoxMessage('測試設備 : '+ keycode +
		'<span id="message-roomStatus-'+keycode+'" data="'+status+'"> 當下設備狀態 : '
		+originalStatus+'</span>'+'.');

	var commandOBJ = [{
		"groupid": groupid,
		"keycode": keycode.toUpperCase()
	}]

	handleSendCommand(roomNo,commandOBJ,status);

	handleWebsocketClient();

	setTimeout(function(){
		handleWebsocketMessage(roomNo,groupid,keycode,status);
    }, 1000);		
}
function handleAllTest(_roomNo,_status){
	cleanMessage();

	var data = handleGetAllDevice(_roomNo);

	var messageOBJ = handleFormatMessageOBJ(data,_roomNo);
	var allStatusOBJ = handleFormatAllOriginalStatusOBJ(data,_roomNo);

	handleCloneAllDeviceToDialogueBox(data,_roomNo);
	handleSendCommandWithAllDevice(_roomNo,data,_status);
	handleWebsocketClient();

	waitForSocketConnection(websocket, function(){
  		for(var i = 0; i < checkAmount; i++){
			//每秒確認訊息
			setTimeout(function(){
				websocket.send(JSON.stringify(messageOBJ));
		    }, i * 1000);
		}
    });
}
function handleResponseAllTestMessage(_obj){
	var displayDeviceAmount = $('#enter-group .box-title').length;

	$.each(_obj.info, function(key,element) {	
		var isExist = $.grep(isPassDeviceArr, function(obj) {		
			    return obj == element.keycode.toUpperCase();
			}).length >= 1?true:false;

		if(isExist){
			return;
		}

		if(sendCommandAmount==0){
			handleAllDeviceDialogueBoxMessage(element.keycode.toUpperCase(),'測試中... (<span id="testAmount-'+element.keycode+'">'+ (sendCommandAmount+1) +'</span>/'+checkAmount+')');
		}else{
			$('#testAmount-'+element.keycode).text(sendCommandAmount+1);
		}

		if(checkObj(element.status)){
			if(requestStatus == element.status){
				
				handleAllDeviceDialogueBoxMessage(element.keycode.toUpperCase(),'測試 <span style="color:green">成功</span>.');
				isPassDeviceArr.push(element.keycode.toUpperCase());
				
				if(isPassDeviceArr.length == displayDeviceAmount){
					cleanSetTimeOut();
					handleWebsocketClose();
					isPassDeviceArr = [];
					return;
				}
			}
		}	
	});

	if (++sendCommandAmount==checkAmount){
		$.each(_obj.info, function(key,element) {
			var isExist = $.grep(isPassDeviceArr, function(obj) {		
			    return obj == element.keycode.toUpperCase();
			}).length >= 1?true:false;

			if(!isExist){
				handleAllDeviceDialogueBoxMessage(element.keycode.toUpperCase(),'測試 <span style="color:red">失敗</span>, 成功送出指令,但RCU並無回傳變動資料.');
			}
		});
		isPassDeviceArr = [];
		cleanSetTimeOut();
		handleWebsocketClose();
		sendCommandAmount = 0;
	}
}
function handleAllDeviceDialogueBoxMessage(_keycode,_message){
	var date = new Date();

	var now = addZero(date.getHours()) + ":"+ addZero(date.getMinutes()) + ":" + addZero(date.getSeconds()) + "." + date.getMilliseconds();
	
	var obj = {
		now : now,
		message :　_message
	}

	var tpl_code = $('#tpl_dialogue_message').html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var html = tpl_bin(obj);
	
	$('#box-body-'+_keycode).append(html);
}
function handleCloneAllDeviceToDialogueBox(_data,_roomNo){
	$.each(_data, function(key,element) {	
		$.each( element.devices, function(key2,element2) {
			var obj = {
				'title' :element2.device.toUpperCase()
			}
			var tpl_code = $('#tpl_dialogue_boxes').html();
			var tpl_bin = Handlebars.compile(tpl_code);
			var html = tpl_bin(obj);
			$('#enter-group').append(html);
			handleAllDeviceDialogueBoxMessage(element2.device.toUpperCase(),'房號 : '+ _roomNo +' 開始測試.');
	    });
	});

	$('#enter-group').attr('style','display');
	$('#enter-group').attr('isDisplay','true');
}
function handleFormatAllOriginalStatusOBJ(_data,_roomNo){
	var groups = [];
	$.each(_data, function(key,element) {
		var keycodes = [];
		$.each( element.devices, function(key2,element2) {
	        keycodes.push({	        	
				"keycode": element2.device.toUpperCase()				
	        });
	    });

	    groups.push({
	    	"groupid": element.groupid,
			"keycodes": keycodes	
	    });
	});

	var obj = {
		"token": token,
		"rooms": [{
			"roomno": _roomNo,
			"groups": groups
		}]
	}

	return obj;	
}
function handleFormatMessageOBJ(_data,_roomNo){
	var rcus = [];
	$.each(_data, function(key,element) {
		$.each( element.devices, function(key2,element2) {
	        rcus.push({
	        	"groupid": element.groupid,
				"keycode": element2.device.toUpperCase()				
	        });
	    });
	});

	var messageOBJ = {
		"roomno": _roomNo,
		"rcuinfo": rcus
	}

	return messageOBJ;	
}
function handleGetAllDevice(_roomNo){
	var outputData = handleDataFormat(_roomNo);

	if(!checkObj(outputData)){
		$('#box-title').text('');
		handleDialogueBoxMessage('not find data');
		$('.input-option').attr('style','display:none');
		return false;
	}

	outputData = $.grep(outputData.deviceData, function(obj) {		
	    return (obj.groupid == 1 || obj.groupid == 2 || obj.groupid == 3)
	});

	return outputData;
}
function cleanMessage(){
	$('#box-body').empty();
}
function handleGetOriginalStatus(_roomNo,_groupid,_keycode){
	var obj = {
		"token": token,
		"rooms": [{
			"roomno": _roomNo,
			"groups": [{
				"groupid": _groupid,
				"keycodes": [{
					"keycode": _keycode.toUpperCase()
				}]	
			}]
		}]
	}
	var originalStatus = hadleRoomStatusResponse(_roomNo,_keycode,doSubmitByGetStatus(obj));

	return originalStatus;
}
function hadleRoomStatusResponse(_roomNo,_keycode,_obj){
	var data = $.map(_obj, function(item, index){
		if(_roomNo==_roomNo){
			return item.groups;
		}
	});

	var status = '尚未接收到回傳資訊';
	$.each( data[0].keycode, function(key,element) {
		if(element.keycode == _keycode){
			if(checkObj(element.status)){
				status = element.status;
			}
		}
	});

	if(status=="1"){
		status = "open";
	}else if(status=="0"){
		status = "close";
	}

	return status;
}
function handleSendCommandWithAllDevice(_roomNo,_data,_status){
	var keycodes = [];

	$.each(_data, function(index,element) {
		$.each(element.devices, function(index2,element2) {
			var devoceOBJ = {
                groupid:element.groupid,
				keycode : element2.device.toUpperCase()
            }
			keycodes.push(devoceOBJ);
		});
	});

	var obj = {
		"token" : token,
		"roomno" : _roomNo,
		"status" : _status,
		"keycodes": keycodes
	}

	doSubmit(obj);
}
function handleSendCommand(_roomNo,_keycodes,_status){
	var obj = {
		"token" : token,
		"roomno" : _roomNo,
		// "category" : _groupid,
		// "keycode" : _keycode.toUpperCase(),
		"status" : _status,
		// "groupid" : _groupid,
		"keycodes": _keycodes
	}


	doSubmit(obj);
}
function handleWebsocketMessage(_roomNo,_groupid,_keycode,_status){
	
	var messageOBJ = {
		"roomno": _roomNo,
		"rcuinfo": [{
			"groupid": _groupid,
			"keycode": _keycode.toUpperCase(),
			"status": _status
		}]
	}

	waitForSocketConnection(websocket, function(){
  		for(var i = 0; i < checkAmount; i++){
			//每秒確認訊息
			setTimeout(function(){
				websocket.send(JSON.stringify(messageOBJ));
		    }, i * 1000);
		}
    });
}
function handleWebsocketClient(){
	if(websocket == null || websocket.readyState != websocket.OPEN){
		websocket = new WebSocket($.websocketurl + "/socketroomrcuinfo" );
	}

	websocket.onerror = function(element) {
    	//alert('系統忙碌中!!');
    	handleDialogueBoxMessage('Test fail(client websocket).');
    };

    websocket.onopen = function() {
		console.log('onopen');		
	};

	websocket.onmessage  = function(element) { 
    	//console.log(element.data);
    	var respone = $.parseJSON(element.data);
    	//var status = handleWebsocketResponse(respone);
    	if(isAllTest){
    		handleResponseAllTestMessage(respone.data);
    	}else{
    		handleTest(respone.data);
    	}
    	
    };   
}
function handleTest(_obj){	
	if(requestStatus == _obj.info[0].status){
		handleDialogueBoxMessage('測試 <span style="color:green">成功</span>.' );
		cleanSetTimeOut();
		handleWebsocketClose();
		return;
	}

	if(sendCommandAmount==0){
		handleDialogueBoxMessage('測試中... (<span id="testAmount">'+ (sendCommandAmount+1) +'</span>/'+checkAmount+')');
	}else{
		$('#testAmount').text(sendCommandAmount+1);
	}
	
	if(++sendCommandAmount==checkAmount){
		handleDialogueBoxMessage('測試 <span style="color:red">失敗</span>, 成功送出指令但RCU,並無回傳變動資料.');
		cleanSetTimeOut();
		handleWebsocketClose();
	}	
}
function handleDialogueBoxWithDifferentRoomNo(){
	if($('#box-title').text() != $('#hiddenRoomNo').val()){
		$('#box-body').empty();
		$('#box-title').text($('#hiddenRoomNo').val());
	}
}
function waitForSocketConnection(socket, callback){
	connectionCount++;

    if(connectionCount>=100){
    	cleanSetTimeOut();
    	alert('Websocket time out!!');
    	location.reload();
    	return false;
    }

	setTimeout(function () {
	    if (socket.readyState === 1) {
	        connectionCount = 0;
	        cleanSetTimeOut();
	       	websocket = socket;	
            callback();
	        return;
	    } else {
	        console.log("wait for connection...");
	        try{
	        	socket = new WebSocket($.websocketurl + "/socketindexinfo" );
	        }finally{
	        	waitForSocketConnection(socket, callback);
	        }
	    }
	}, 10); 
}
function handleDialogueBoxDisplay(){
	var isDisplay = $('#enter-group').attr('isDisplay')=='true'?true:false;
	if(!isDisplay){
		buildTemplate('tpl_dialogue_box',null,'enter-group');
		$('#box-title').text($('#hiddenRoomNo').val());
		$('#enter-group').attr('style','display');
		$('#enter-group').attr('isDisplay','true');
	}
	setBindDialogueBox();
}
function clearDialogueBox(){
	$('#enter-group').empty();
	$('#enter-group').attr('isDisplay',false);
}
function handleDialogueBoxMessage(_message){

	handleDialogueBoxDisplay();

	var date = new Date();

	var now = addZero(date.getHours()) + ":"+ addZero(date.getMinutes()) + ":" + addZero(date.getSeconds()) + "." + date.getMilliseconds();
	
	var obj = {
		now : now,
		message :　_message
	}

	var tpl_code = $('#tpl_dialogue_message').html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var html = tpl_bin(obj);
	
	$('#box-body').append(html);	
}
function cleanSetTimeOut(){
	var timeoutId = setTimeout(function() {}, 0);
	while (timeoutId--) {
	    clearTimeout(timeoutId); 
	}

	var intervalId = setInterval(function() {}, 0);
	while (intervalId--) {
	    clearInterval(intervalId); 
	}
}
function handleWebsocketResponse(_response){
	var status = 'empty';
	if(_response.status!=0){
		handleDialogueBoxMessage(_response.message);
		return false;
	}else{
		var responeStatus = _response.data==1?"open":"close";
		//handleDialogueBoxMessage("current status : " + responeStatus);

		status = responeStatus;
	}
	return status;
}
function handleWebsocketClose(){
	if(websocket!=null && websocket.readyState === 1){
		websocket.close(); 
		//handleDialogueBoxMessage("close websocket success.");
	}
}
function handleRoomRCUInfo(_roomNo){
	if(!checkObj(roomRCUData) || !checkObj(groupData)){
		$('#box-title').text('');
		handleDialogueBoxMessage('not find data');
		return false;
	}

	var outputData = handleDataFormat(_roomNo);

	if(!checkObj(outputData)){
		$('#box-title').text('');
		handleDialogueBoxMessage('not find data');
		$('.input-option').attr('style','display:none');
		return false;
	}


	if($.grep(outputData.deviceData, function(obj) {return obj.groupid == "all"}).length<=0){
		outputData.deviceData.push({groupid:"all",groupname:"All"});
	}

	var groupOBJ = {
		groupArr : outputData.deviceData
	}

	$('.input-option').attr('style','display');
	buildTemplate('tpl_group',groupOBJ,'group');

	//暫時只有燈泡、冷氣跟服務
	$('.groupLink').each(function( index ) {
		var dataIndex = $( this ).attr('data-index');
		if(dataIndex != 1 && dataIndex!=2 && dataIndex!=3 && dataIndex!='all'){
			$(this).remove();
		}
	});

	setBindGroup();

	var deviceOBJ = {
		deviceArr : outputData.deviceData[0].devices
	}

	buildTemplate('tpl_device',deviceOBJ,'device');
	setBindDevice();
}
function handlerChangeDeviceDispaly(_groupid){
	var roomNo = $('#hiddenRoomNo').val();
	var outputData = handleDataFormat(roomNo);

	outputData = $.grep(outputData.deviceData, function(obj) {		
	    return obj.groupid == _groupid
	});

	if(!checkObj(outputData[0].devices)){
		$('#device-div').attr('style','display:none;');
		return ;
	}

	var deviceOBJ = {
		deviceArr : outputData[0].devices
	}

	buildTemplate('tpl_device',deviceOBJ,'device');
	setBindDevice();
}
function handleDataFormat(_roomNo){
	var roomInfo = $.grep(roomRCUData, function(obj) {		
	    return obj.roomNo == _roomNo
	});

	if(!checkObj(roomInfo) ){
		//console.log('not find data');
		$('#box-title').text('');
		handleDialogueBoxMessage('not find room info data.');
		return new Array();
	}

	roomInfo = roomInfo[0];

	var groupInfo = $.grep(groupData, function(obj) {		
	    return obj.name == roomInfo.type
	});

	if(!checkObj(groupInfo) ){
		//console.log('not find data');
		$('#box-title').text('');
		handleDialogueBoxMessage('not find group info data.');
		return new Array();
	}

	groupInfo = groupInfo[0];

	return groupInfo;
}
function handleGroupDevice(_groups){
	$.each(_groups, function(index,element) {
		var obj = {
			groupid : element.id
		}
		var deviceData = getDeviceData(obj);

		element.deviceData = deviceData.groupdevices;
	});

	return _groups;
}
function handleRefreshToken(){
	var obj ={
		"token" : token
	}	
	token = doRefreshToken(token);
	window.localStorage.setItem('hmitoken',token);
	
	if(checkObj(token)){
		handleSubmitCommand();
	}else{
		window.localStorage.removeItem('hmitoken');
		handleCheckLogin();
	}
}
function setBindGroup(){
	$("#group").unbind("click.group");
	$("#group").on("click.group", ".groupLink", function(event){ 
		$('#displayGroup').text($(this).attr('data'));
		$('#displayGroup').attr('data-index',$(this).attr('data-index'));
		if($(this).attr('data-index')==2){
			$('#device-div').attr('style','display:none');
		}else{
			$('#device-div').attr('style','display');
		}
		handlerChangeDeviceDispaly($(this).attr('data-index'));
    });
}
function setBindDevice(){
	$("#device").unbind("click.device");
	$("#device").on("click.device", ".deviceLink", function(event){ 
		$('#displayDevice').text($(this).attr('data'));
    });

    $("#returnOption").unbind("click.returnLink");
	$("#returnOption").on("click.returnLink", ".returnLink", function(event){ 
		$('#returnCount').text($(this).text());
    });
}
function setBindDialogueBox(){	
    //remove
	$("#enter-group").unbind("click.remove");
	$("#enter-group").on("click.remove", ".remove", function(event){ 
		$('#enter-group').attr('isDisplay','false');
    });

     //stop
	$("#enter-group").unbind("click.stop");
	$("#enter-group").on("click.stop", ".stop", function(event){ 
		cleanSetTimeOut();
		handleWebsocketClose();
    });
}
function setBindAdd(){	
    //submit
	$("#main-content").unbind("click.submit");
	$("#main-content").on("click.submit", "#submit", function(event){ 
		handleSubmitCommand();
    });

    //stop
	$("#main-content").unbind("click.stop");
	$("#main-content").on("click.stop", "#stop", function(event){ 
		cleanSetTimeOut();
		handleWebsocketClose();
		isPassDeviceArr = [];
    });
}
function setRouter(){
	var routes = {
		'':showIndex()
	};

	var router = Router(routes)
	
	router.notfound = function() {
      	showIndex();
  	};
    router.init('');
}
function addZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
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
function doRefreshToken(data){
	var newToken = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/token/refresh',
		data: JSON.stringify(data),
		success: function(obj){			
			if(obj.status==0){			
				handleDialogueBoxMessage("refresh token success.");
				newToken = obj.data;	
			}else{
				// handleDialogueBoxMessage(obj.message);
				handleWebsocketClose();			
				alert(obj.message);
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
function doSubmitByGetStatus(data){
	var respone = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcustatus',
		data: JSON.stringify(data),
		success: function(obj){			
			if(obj.status==0){			
				respone = obj.data
			}else if(obj.status==2001){
				handleRefreshToken();
			}else{
				window.localStorage.removeItem('hmitoken');
				alert("Test fail("+obj.message+").");
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
	return respone;
}
function doSubmit(data){
	var returnCode = 9999;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/staff/test/rcustatus',
		data: JSON.stringify(data),
		success: function(obj){
			returnCode = obj.status;
			if(obj.status==0){			
				// handleDialogueBoxMessage("send command success.");
				console.log("send command success.");
			}else if(obj.status==2001){
				handleRefreshToken();
			}else{
				window.localStorage.removeItem('hmitoken');
				alert("Test fail("+obj.message+").");
				handleWebsocketClose();
				handleCheckLogin();
			}
		},
		failure: function(errMsg) {
			data = null;
			window.localStorage.removeItem('hmitoken');
			alert(errMsg);
			handleCheckLogin();
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return returnCode;
}
function getRoomData(cb){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/rooms',
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
	return $.parseJSON(data);
}
function roomTabProcess(){
	var roomData = getRoomData();
	var floorArr = [];

	//先把樓層找出來
	for(var i=0;i<roomData.length;i++){
		var isExist = false;
		var floor = roomData[i].floor;

		for(var x=0;x<floorArr.length;x++){
			if(floor == floorArr[x]){
				isExist = true;
				break;
	       	}
		}

		if(!isExist){
			floorArr.push(floor);
		}
	}

	var arr = [];

	for(var i=0;i<floorArr.length;i++){

		//找到同樓層的資料
		var floorData = $.grep(roomData, function(obj) {
		    return obj.floor === floorArr[i];
		});

		var roomArr = [];

		//format 頁面要得格式
		for(var x=0;x<floorData.length;x++){
			var roomObj = {
				'roomNo' : floorData[x].roomNo
			}
			roomArr.push(roomObj);
		}
		
		var floorObj = {
			'floorNo' : floorArr[i],
			'roomArr' : roomArr
		}
		
		arr.push(floorObj);
	}

	var obj = {
		floorArr :arr
	}

	buildTemplate('tpl_tabs',obj,'tabs');
	setBindRoomSelect();
}
function setBindRoomSelect(){
	$("#tabs").unbind("click.selectButton");
	$("#tabs").on("click.selectButton", ".selectButton", function(event){ 
		//$('#box-title').text($(this).val());
		cleanMessage();
		clearDialogueBox();
		handleRoomRCUInfo($(this).val());
		$('#hiddenRoomNo').val($(this).val());
	});
}
function getRoomInfoData(){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/roominfo',
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
	return data;
}
function getGroupData(cb){
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
	return data;
}
function getDeviceData(_obj){
	var data = null;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/group/device',
		data: JSON.stringify(_obj),
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
	return data;
}
$(window).on('beforeunload', function(){
    handleWebsocketClose();
});