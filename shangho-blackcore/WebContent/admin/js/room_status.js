var data = null;
var websocket = null;
var connectionCount = 0;
var langs = 'zh_TW';
var roomarray = [];
var timerID = 0;
var socketUrl = '/socketroomstatus';
var isInitial = false;
$(function () {
	initial();
});

function initial(){
	websocket = new WebSocket($.websocketurl + socketUrl );
  	
    websocket.onerror = function(evt) {
    	alert('系統忙碌中!!');
    };

	sendMsg();
}
function sendMsg(){
	var obj = [
        // {"keycode":"SOS"},
        // {"keycode":"MUR"}
    ];

	websocket.onopen = function() {
		console.log('onopen');
		keepAlive();
	};

    websocket.onmessage  = function(evt) { 
    	data = $.parseJSON( evt.data ); 

    	var formatData = handleData();
    
    	if(formatData.length<=0){
    		hadleEmptyData();
    	}else{
    		if(!isInitial){
	    		handleInitialData(formatData);
	    	}else{
	    		handleWebData(formatData);
	    	}
    	}
    };
    
    waitForSocketConnection(websocket, function(){
    	websocket.send(JSON.stringify(obj)); 
    });
}
function hadleEmptyData(){
	var arr = [];
	var obj = {
		floor : ' '
	}
	arr.push(obj);

	handleFloorData(arr);

	buildTemplate('tpl_no_data',{},'roomStatusDiv');
}

function handleWebData(_data){
	var roomContent = ''
	$.each(_data, function( key, element) {
		$.each(element.data, function(index, obj) {
			//看有沒有這個房號
			if($('#'+obj.roomNo+'_icon').length > 0){
				// $('#'+obj.roomNo+'_statusName').html(obj.statusName);
				handleRoomStatusChange(obj.roomNo,obj.statusName,obj.image,obj.backgroundColor)
			}else{
				if($('#floor_'+element.floor).length>0){
					handleNewRoom(element.floor,obj.roomNo,obj.statusName,obj.image,obj.backgroundColor);
				}else{
					//沒有這個樓層就 從頭buld一次
					handleInitialData(_data);
				}
			}
		});
	});
}
function handleRoomStatusChange(_roomNo,_status,_image,_backgroundColor){
	var obj = {		
		roomNo : _roomNo,
		statusName : _status,
		image : _image,
		backgroundColor : _backgroundColor
	}
	buildTemplate('tpl_room_status_change',obj, _roomNo+'_element');
}
function handleNewRoom(_floor,_roomNo,_status,_image,_backgroundColor){
	var obj = {		
		roomNo : _roomNo,
		statusName : _status,
		image : _image,
		backgroundColor : _backgroundColor
	}
	$('#tab_'+_floor + " .row").append('<div id="new_'+_roomNo+'"></div>');

	buildTemplate('tpl_single_room',obj,'new_'+ _roomNo);
}
function handleInitialData(_data){
	if(!checkObj(_data)){
		return;
	}

	_data = sortByFloor(_data);

	handleFloorData(_data);
	handleRoomStatusData(_data);
	isInitial = true;
}
function handleRoomStatusData(_data){
	var obj = {
		arr : _data
	}
	buildTemplate('tpl_room_status',obj,'roomStatusDiv');
}
function handleFloorData(_data){
	var obj = {
		arr : _data
	}
	buildTemplate('tpl_floor',obj,'floorDiv');
	bindFloor();
}
function buildTemplate(templateId,_obj,pageId){
	var tpl_code = $('#' + templateId).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function sortByFloor(_data) {
	_data.sort(function(objA, objB){
        if ( parseInt(objA.floor) > parseInt(objB.floor)) {
	    	return 1;
	  	}
  		if (parseInt(objA.floor) < parseInt(objB.floor)) {
	    	return -1;
	  	}
	  	return 0;
    });

    return _data;
}
function handleData(){
	var outputArr = [];

	if(!checkObj(data)){
		return outputArr;
	}

	var floorArr = [];

	for(var i=0; i<data.length; i++){
		var element = data[i];
		var floorData = [];

		var formatObj = {
			roomNo : element.roomno,
			statusCode : element.statuscode,
			statusName : element.statusname,
			backgroundColor : element.backgroundcolor,
			image : $.serverurl + "/" + element.image
		}

		var floorObj = {			
			floor : element.floor
		}

		var isFloorExist = findFloor(element.floor,floorArr);

		if(isFloorExist){
			floorData = $.map(outputArr, function(item, index){
				if(item.floor == element.floor){
					return item.data;
			    }
			})
		}else{
			floorArr.push(element.floor);
		}

		floorData.push(formatObj);

		floorObj.data = floorData;

		if(isFloorExist){
			$.grep(outputArr, function(obj) {				
			    if(obj.floor == element.floor){
			    	obj.data = floorData;
			    }
			});

		}else{
			outputArr.push(floorObj);
		}
	}
	return outputArr;	
}
function findFloor(_floor,_floorArr){
	var isFloorExist = false;
	for(var x=0;x<_floorArr.length;x++){
		if(_floor == _floorArr[x]){
			return true;
		}
	}
	return isFloorExist;
}
function getRoomStatusCodeData(){
	var data = null;

	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/roomstatus/code/list',
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
function bindFloor(){
	$("#floorDiv").unbind("click.floor");
    $("#floorDiv").on("click.floor", ".floorClick", function(event){ 
        
    });
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
function keepAlive() {
    var timeout = 120000; 

    var obj = [];

    if (websocket.readyState == websocket.OPEN) {  
    	console.log('keepAlive-OPEN '+ new Date());
        // websocket.send(JSON.stringify(obj)); 
        websocket.send(obj); 

	 	var timerId = setTimeout(keepAlive, timeout);  

	    while (timerId--) {
			window.clearTimeout(timerId); 
		}
    }else{
    	console.log('websocket.fail '+ new Date());

    	waitForSocketConnection(websocket, function(){
	    	sendMsg();
	    });
    }   
} 
function waitForSocketConnection(socket, callback){
	connectionCount++;

    if(connectionCount>=5000){
    	cleanSetTimeOut();
    	// clearInterval(myVar);
    	alert('Websocket time out!!');
    	location.reload();
    	return false;
    }

	setTimeout(function () {
	    if (socket.readyState === 1) {
	        connectionCount = 0;

	        cleanSetTimeOut();

	       	websocket = socket;

	       	keepAlive();

	        if(callback != null){
	            callback();
	        }
	        return;
	    } else {
	        console.log("wait for connection...");
	        try{
	        	socket = new WebSocket($.websocketurl + socketUrl );
	        }finally{
	        	waitForSocketConnection(socket, callback);
	        }
	    }
	}, 10); 
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
$(window).on('beforeunload', function(){
    websocket.close(); 
});