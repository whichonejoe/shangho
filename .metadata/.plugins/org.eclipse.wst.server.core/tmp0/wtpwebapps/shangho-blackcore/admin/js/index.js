var data = null;
var websocket = null;
var connectionCount = 0;
var langs = 'zh_TW';
var roomarray = [];
var timerID = 0;
var sosStopArr = [];
var ipAddress = null;
var stopAlarmEnable = false;
var systemSettingData = null;
var fireBaseSetting = [];
var checkCount = 0;
$(function () {
	
	initial();
	
});
function initial(){
	//getSettingData();
	
	
	//alarm audio
	//放到 get
	//playAlarm();
}
function updateCount(){
	getDndTotalData();
	getMakeUpTotalData();
	getbulterTotalData();
	getOccupiedCountData();
	getVacantData();
	getLostRCUCountData();
	getLostRCUHeartbeatCountData();
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
    	// console.log(evt.data);
    	data = $.parseJSON( evt.data ); 
    	getSOSData();
    	updateCount();
    };
    
    waitForSocketConnection(websocket, function(){
    	websocket.send(JSON.stringify(obj)); 
    });
}
function keepAlive() {
    var timeout = 120000; 

    var obj = " ";

    if (websocket.readyState == websocket.OPEN) {
    	console.log('keepAlive-OPEN '+ new Date());
        // websocket.send(JSON.stringify(obj)); 
        websocket.send(obj); 

        if(checkCount>0){
        	handleWebsocketMessageRecevie();
        }

	 	var timerId = setTimeout(keepAlive, timeout);  

	    while (timerId--) {
			window.clearTimeout(timerId); 
		}
		++checkCount;
    }else{
    	console.log('websocket.fail '+ new Date());

    	waitForSocketConnection(websocket, function(){
	    	sendMsg();
	    });
    }   
} 
function waitForSocketConnection(socket, callback){
	connectionCount++;
	checkCount = 0;
	
    if(connectionCount>=2000){
    	cleanSetTimeOut();
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
	        	socket = new WebSocket($.websocketurl + "/socketindexinfo" );
	        }finally{	        	
	        	waitForSocketConnection(socket, callback);
	        }
	    }
	}, 500); 
}
function handleWebsocketMessageRecevie(){
	var dndCount = parseInt($('#index_dnd h3').text());
	var murCount = parseInt($('#index_make_up h3').text());
	var occupiedCount = parseInt($('#index_occupied h3').text());
	var vacantCount = parseInt($('#index_vacant h3').text());
	var sosCount = $('.sos-item').length;

	if(sosCount<=0){
		if(dndCount==0 && murCount==0 && occupiedCount==0 && vacantCount==0){
				var restartOBJ = {
					token : fireBaseSetting[0].content.token
				}
				submitRestartProcess(restartOBJ);
				handleWaitAndReloadPage();
		}
	}
}
function handleWaitAndReloadPage(){
	cleanSetTimeOut();
	setTimeout(function () {
	    location.reload();
	}, 1000 * 10); 
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
function stopAlarmProcess(){	
	if(systemSettingData == null){
		return false;
	}

	if(_.isUndefined(systemSettingData)){
		return false;
	}

	var sosSetting = $.grep(systemSettingData, function(event) {
        return event.key === "SosAlarmSetting";
    });

    if(sosSetting.length<=0){
    	return false;
    }
	stopAlarmEnable = sosSetting[0].content.isenable;
}
function getSOSData(){
	var arr = [];
	sosStopArr = [];
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "SOS"){
				var alertFlag = "N";

				//和心跳失聯 超過五分鐘顯示提示
				if(data[i]["lostminute"] >= 5){
					alertFlag = "Y";
				}

				if(!_.isUndefined(data[i]["condition"].isstopalarm) && data[i]["condition"].isstopalarm){
					sosStopArr.push(data[i]["roomno"]);
				}
				var sosMessage = ' ';

				if(stopAlarmEnable){
					sosMessage = '手動停止';
					if($.inArray( data[i]["roomno"], sosStopArr)>=0){
						//sosMessage = '此房間已經手動停止';
					}
				}
				
				var sosObj = {
					'roomNo' : data[i]["roomno"],
					'intervalTime' : data[i]["lostminute"],
					'alertFlag' : alertFlag,
					'sosMessage' : sosMessage
				}
				arr.push(sosObj);				 
			}
		}
	}

	handleSOSAlarmAudio(arr,sosStopArr);

	var obj = {
		'arr' : arr,
		'isEnable': stopAlarmEnable
	}

	buildTemplate('tpl_sos_list',obj,'list_sos');
	//綁定事件
	if(stopAlarmEnable){
		setBindSOS();
	}
}
function getSOSDataFromFirebase(_data){
	var arr = [];
	if(sosStopArr==null){
		sosStopArr = [];
	}
	
	for(var i=0;i<_data.length;i++){
		if(_data[i]["keycode"] == "SOS"){
			var alertFlag = "N";		

			if(!_.isUndefined(_data[i]["condition"].isstopalarm) && _data[i]["condition"].isstopalarm){
				sosStopArr.push(_data[i]["roomno"]);
			}
			var sosMessage = ' ';

			if(stopAlarmEnable){
				sosMessage = '手動停止';
				if($.inArray(_data[i]["roomno"], sosStopArr)>=0){
					//sosMessage = '此房間已經手動停止';
				}
			}

			if($('#list_sos .area-sos-'+_data[i]["roomno"]).length>0){
				continue;
			}
			
			var sosObj = {
				'roomNo' : _data[i]["roomno"],
				'intervalTime' : _data[i]["lostminute"],
				'alertFlag' : alertFlag,
				'sosMessage' : sosMessage
			}			
			arr.push(sosObj);				 
		}
	}

	handleSOSAlarmAudio(arr,sosStopArr);

	var obj = {
		'arr' : arr,
		'isEnable': stopAlarmEnable
	}

	buildTemplate('tpl_sos_list',obj,'list_sos_from_firebase');
	//綁定事件
	if(stopAlarmEnable){
		setBindSOS();
	}
}
function handleSOSAlarmAudio(_sosArr,_sosStopArr){
	var sosCount = 0;
	var isExist = true;
	$('.sos-item').each(function(index) {
		++sosCount;
		//檢查有沒有手動停止的項目
		if($.inArray($(this).children('input[name=sos-roomno]').val(), _sosStopArr)>=0){
			isExist = false;
		}else{
			isExist = true;
			return false;
		}			
	});

	//如果有資料 警報
	if(_sosArr.length>0 || sosCount>0){
		//只要有其中一個沒有手動停止 就要響
		if(isExist){
			$("#alarm")[0].play();
		}else{
			$("#alarm")[0].pause();
		}
	}else{
		$("#alarm")[0].pause();
	}
}
function getSosTotalData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "SOS"){
				count++;
			}
		}
	}

	var obj = {
		'sosCount':count 
	}

	buildTemplate('tpl_sos',obj,'index_sos');
}
function getDndTotalData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "DND"){
				count++;
			}
		}
	}

	var obj = {
		'dndCount':count 
	}

	buildTemplate('tpl_dnd',obj,'index_dnd');
}
function getMakeUpTotalData(){
	var count = 0;
	
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "MUR"){
				count++;
			}
		}
	}

	var obj = {
		'makeUpRoomCount':count 
	}

	buildTemplate('tpl_make_up',obj,'index_make_up');
}
function getbulterTotalData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "BUTLER"){
				count++;
			}
		}
	}

	var obj = {
		'bulterCount':count
	}
}
function getOccupiedCountData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "CHECKIN"){
				count = data[i]["status"];
				break;
			}
		}
	}

	var obj = {
		'occupiedCount' : count
	}

	buildTemplate('tpl_occupied',obj,'index_occupied');
}
function getVacantData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "CHECKOUT"){
				count = data[i]["status"];
				break;
			}
		}
	}
	var obj = {
		'vacantCount' : count
	}
	buildTemplate('tpl_vacant',obj,'index_vacant');
}
function getMakeUpData(){
	var obj = {
		'arr': getServiceData('MUR') ,
		'title': '請打掃'
	}
	return obj;	
}
function getDndData(){
	var obj = {
		'arr': getServiceData('DND') ,
		'title': '請勿打擾'
	}
	return obj;	
}
function setBindSOS(){
	$("#list_sos").unbind("click.sos-stop");
    $("#list_sos").on("click.sos-stop", ".sos-stop", function(event){ 
        sosStopArr.push($(this).attr('data-index'));
        sosRecordProcess($(this).attr('data-index'));
    });    
    $("#list_sos_from_firebase").unbind("click.sos-stop");
    $("#list_sos_from_firebase").on("click.sos-stop", ".sos-stop", function(event){ 
    	$('#list_sos_from_firebase .area-sos-'+$(this).attr('data-index')).remove();
    	if($('#list_sos_from_firebase .sos-item').length<=0){
    		$('#list_sos_from_firebase').html('');
    	}
        sosStopArr.push($(this).attr('data-index'));
        sosRecordProcess($(this).attr('data-index'));
    });  
}
function getLostRCUCountData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "LostRoomNo"){
				count = data[i]["status"];
				break;
			}
		}
	}

	var obj = {
		'count' : count
	}

	buildTemplate('tpl_lost_rcu',obj,'lost_rcu_count');
	setBindLostRCU();
}
function getLostRCUHeartbeatCountData(){
	var count = 0;
	if(data != null){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == "RCULostHeartbeat"){
				count = data[i]["status"];
				break;
			}
		}
	}

	var obj = {
		'count' : count
	}

	buildTemplate('tpl_no_return',obj,'no_return_count');
	setBindLostRCUHeartbeat();
}
function setBindLostRCU(){
	$("#index_lost_rcu").unbind("click.show-lost-rcu");
	$("#index_lost_rcu").on("click.show-lost-rcu", ".show-lost-rcu", function(event){ 
		showModel('tpl_model_service',getLostRCUData);
		handleReInitRCURoom();
	});
}
function setBindLostRCUHeartbeat(){
	$("#index_no_return").unbind("click.show-no-return");
	$("#index_no_return").on("click.show-no-return", ".show-no-return", function(event){ 
		showModel('tpl_model_service',getLostRCUHeartbeatData);
		//handleReInitRCURoom();
	});
}
function setBindDND(){
	$("#service_content").unbind("click.show-dnd");
	$("#service_content").on("click.show-dnd", ".show-dnd", function(event){ 
		showModel('tpl_model_service',getDndData);
	});
}
function setBindRefreshRCURoom(){
	$("#myModal").unbind("click.refreshRCURoom");
	$("#myModal").on("click.refreshRCURoom", "#refreshRCURoom", function(event){ 
		submitRefreshRCURoomProcess();
	});
}
function setBindMUR(){
	$("#service_content").unbind("click.show-mur");
	$("#service_content").on("click.show-mur", ".show-mur", function(event){ 
		showModel('tpl_model_service',getMakeUpData);
	});
}
function showModel(tpl_id,cb){
	var tpl_code = $('#' + tpl_id).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = cb();
	var html = tpl_bin(obj);
	$('#index_modal-content').html(html);
	$('#myModal').modal('show');
}
function handleReInitRCURoom(){

	var statement = ''+
	'<div>'+
		'<a class="btn btn-success" id="refreshRCURoom" href="javascript:void(0)"><i class="fa fa-refresh"></i></a>'+
		'&nbsp;<b>手動取得資訊.</b><br>'+		
	'</div>';
	
	//加上按鈕,敘述
	$('#modal-body').before(statement);
	
	buildTemplate('tpl_data_table',getLostRCUData(),'modal-body');
	$('#data-table').DataTable({
		"paging": true,
		"lengthChange": false,
		"searching": false,
		"ordering": false,
		"info": false,
		"autoWidth": true,
		"displayLength": 10
    });

    var footerStatement = ''+
    '<div  style=" text-align :left">'+
	'   step 1:按下左上角綠色按鈕,重新取得房間資訊.<br />'+
	'   step 2:房號消失,表示HMI已可以處理該房號.<br />'+
	'   ◆若持續顯示於此,請至HMI房間資訊功能,檢查是否有該房號.<br />'+
	'   ◆如沒有該房號,請重新加入,否則請通知相關人員.<br />'+
	'</div>';
	//加上按鈕,敘述
	$('#modal-footer').html(footerStatement);

    setBindRefreshRCURoom();
}
function getLostRCUData(){
	if(data==null){
		return;
	}
	var lostArr = $.grep(data, function(obj) {
        return obj.keycode === "LostRoomNo";
    });

    if(lostArr.length<=0){
    	return false;
    }

    var arr = [];

	for(var x=0;x<lostArr[0].condition.rooms.length;x++){
		
		var obj = {
			'roomNo' : lostArr[0].condition.rooms[x]
		}

		arr.push(obj);
	}

	var modalObj = {
		'arr': arr ,
		'title': 'RCU有傳資訊給HMI,但HMI在初始化時,未能取得該房資訊,故無法處理.'
	}

	return modalObj;
}
function getLostRCUHeartbeatData(){
	if(data==null){
		return;
	}
	var lostArr = $.grep(data, function(obj) {
        return obj.keycode === "RCULostHeartbeat";
    });

    if(lostArr.length<=0){
    	return false;
    }

    var arr = [];

	for(var x=0;x<lostArr[0].condition.rooms.length;x++){
		var lostStr = '';
		if(_.isUndefined(lostArr[0].condition.rooms[x].lostminute)){
			lostStr = '從未收到';
		}else{
			lostStr += lostArr[0].condition.rooms[x].lostminute + ' minute ago';
		}
		
		
		var obj = {
			'roomNo' : lostArr[0].condition.rooms[x].room,
			'triggerTime' : lostStr
		}
		arr.push(obj);
	}

	var modalObj = {
		'arr': arr ,
		'title': '有設定RCU的房號,但沒回傳資訊.'
	}

	return modalObj;
}
function getServiceData(keycode){
	var arr = [];
	if(!_.isNull(data)){
		for(var i=0;i<data.length;i++){
			if(data[i]["keycode"] == keycode && data[i]["status"] == 1){
				var tempTime = new Date(data[i]["time"]);

				var triggerTime = 
					tempTime.getFullYear() + '/' + (tempTime.getMonth()+1) + '/' + tempTime.getDate() +
					' ' + tempTime.getHours() + ':' + tempTime.getMinutes() + ':' + tempTime.getSeconds();

				var dndObj = {
					'roomNo' : data[i]["roomno"],
					'triggerTime' : triggerTime
				}
				arr.push(dndObj);
			}
		}
	}
	return arr;
}
function requireJS(script) {
    $.ajax({
        url: script,
        dataType: "script",
        async: false,           
        success: function () {
            handleFireBase();
        },
        error: function () {
            throw new Error("Could not load script " + script);
        }
    });
}
function initFireBaseSetting(){
	if(systemSettingData == null){
		return false;
	}

	if(_.isUndefined(systemSettingData)){
		return false;
	}

	fireBaseSetting = $.grep(systemSettingData, function(event) {
        return event.key === "FirebaseMessage";
    });

    if(fireBaseSetting.length<=0){
    	return false;
    }
    if(fireBaseSetting[0].content.isenable){
    	requireJS("https://www.gstatic.com/firebasejs/5.0.4/firebase.js");
    }
}
function handleFireBase(){
	firebase.initializeApp(readFireBaseSetting());	
	initFireBaseMessage();
}
function handleStoreFireBaseToken(_token){
	var obj = {
		'firebasetoken' : _token,
	}
	submitFireBaseTokenProcess(obj);
}
function handleRecevieFireBase(_message){
	//console.log(_message.data);
	if(_message.data==null || _message.data == ''){
		return;
	}

	if(_message.data.content==null || _message.data.content == ''){
		return;
	}

	var messageData = $.parseJSON(_message.data.content);

	var sosData = $.grep(messageData, function(obj) {
        return obj.keycode === "SOS" && obj.status=="1";
    });

	if(data == null || data == ''){
		getSOSDataFromFirebase(sosData);
	}else{
		var newSOSArr = [];
		for(var x=0;x<sosData.length;x++){
			var element = sosData[x];

			var websocketSOSData = $.grep(data, function(obj) {
				return obj.keycode=="SOS" && obj.roomno==element.roomno;
			});

			if(websocketSOSData.length<=0){
				newSOSArr.push(element);
			}
		}
		getSOSDataFromFirebase(newSOSArr);
	}
}
function handleFireBaseMessage(_messaging){
	_messaging.onMessage(function(payload) {
		//console.log("Message received. "+ payload);
		handleRecevieFireBase(payload);
	});
}
function initFireBaseMessage(){	
	navigator.serviceWorker.register('/rcu-hmi/admin/js/firebase-messaging-sw.js?')
	.then((registration) => {		
		console.log(registration);
	  	const messaging = firebase.messaging();
		messaging.useServiceWorker(registration);		
		messaging.requestPermission().then(function(){        
	        return messaging.getToken();
	    }).then(function(token){
	    	console.log(token);
	    	if(token!=null && token!=''){
		    	handleStoreFireBaseToken(token);
		    } 
	    }).catch(function(err){
	        console.log('err',err);
	        return;
	    });
	    handleFireBaseMessage(messaging);
	}).catch(function(err){
        console.log('register err',err);
    });   
}
function readFireBaseSetting(){
	var config = {
	    apiKey: "AIzaSyCGQntVBcg1RdZdOnM-bVvbJ9cCajCcwYk",
	    authDomain: "sidcfcmtest.firebaseapp.com",
	    databaseURL: "https://sidcfcmtest.firebaseio.com",
	    projectId: "sidcfcmtest",
	    storageBucket: "sidcfcmtest.appspot.com",
	    messagingSenderId: "432292274138"
	};
	return config;
}
function buildTemplate(templateId,_obj,pageId){
	var tpl_code = $('#' + templateId).html();
	var tpl_bin = Handlebars.compile(tpl_code);
	var obj = _obj;
	var html = tpl_bin(obj);
	$('#' + pageId).html(html);
}
function removeArea(areaID){
	$(areaID).remove();
}
function sosRecordProcess(_roomNo){
	if(ipAddress==null){
		ipAddress = "127.0.0.1";
	}

	var obj = {
		'roomno' : _roomNo,
		'action' : 'stopalarm',
		'clientrole' : 'hmiuser',
		'clientip' : ipAddress
	}
	submitSosRecordProcess(obj);
}
function getIpAddress(){
	$.get("http://ipinfo.io", function(response) {
    	ipAddress = response.ip;
	},"jsonp");
}
function submitFireBaseTokenProcess(_data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/firebase/token/insert',
		data: JSON.stringify(_data),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
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
function submitSosRecordProcess(_data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/sos/record',
		data: JSON.stringify(_data),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			alert('success');
			window.history.replaceState(null,null,'#');			
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function submitRestartProcess(_data){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/restart',
		data: JSON.stringify(_data),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			console.log('restart');
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function submitRefreshRCURoomProcess(){
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/room/init',
		// data: JSON.stringify(_data),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			alert('success');
			$('#myModal').modal('hide');
		},
		failure: function(errMsg) {
			data = null;
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function getSettingData(){
	var _obj = {
		"keys": ["SosAlarmSetting","FirebaseMessage"]
	}
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/system/setting',
		data: JSON.stringify(_obj),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				return false;
			}
			systemSettingData = obj.data;
		},
		failure: function(errMsg) {
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
}
function getUnique(oldArray, newArray) {	
	var flag = false;
	for (var i = 0; i < newArray.length; i++) {
		if ((jQuery.inArray(newArray[i], oldArray)) == -1) {
			oldArray.push(newArray[i]);
			flag = true;
		}
	}
	return flag;
}
$(window).on('beforeunload', function(){
    websocket.close(); 
});