$(function () {
	$.serverurl = window.location.protocol + '//'+ window.location.host + '/' + window.location.pathname.split( '/' )[1];

	var websocketProtocol = 'ws://';

	if(window.location.protocol==='https:'){
		websocketProtocol = 'wss://';
	}

	$.websocketurl = websocketProtocol + window.location.host + '/' + window.location.pathname.split( '/' )[1];
});