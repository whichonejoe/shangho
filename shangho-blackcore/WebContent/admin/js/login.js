$(function () {	
	initial();	
});

function initial(){	
	buttonIsCheck();
	$("#myForm").validate();
	setBind();
}
function handlerCheckLogin(){	
	var token = window.localStorage.getItem('hmitoken');
	if(!checkObj(token)){
		window.location.href="login.jsp";
	}
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
function buttonIsCheck(){
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-red'
    });
}
function handleSubmit(){
	var obj = {
		id : $('#login-id').val(),
		password : $('#login-pwd').val()
	}
	return doSubmit(obj);
}
function handleLoginFail(){
	$('#error-mesage').attr('style','display');
}
function handleLocalStorage(_data){
	window.localStorage.setItem('hmitoken',_data);	
}
function doSubmit(data){
	var isPass = false;
	$.ajax({
		type: "POST",
		async: false,
		url: $.serverurl + '/rcu/login/tester',
		data: JSON.stringify(data),
		success: function(obj){
			if(obj.status!=0){
				console.log(obj.message);
				handleLoginFail();
				return isPass;
			}else{
				isPass = true;
				handleLocalStorage(obj.data);
			}
		},
		failure: function(errMsg) {
			alert(errMsg);
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	});
	return isPass;
}
function setBind(){
	$("#login-body").unbind("click.submit");
	$("#login-body").on("click.submit", "#submit", function(event){ 
		if(handleCheckInput()){
			if(handleSubmit()){
				window.location = $.serverurl + "/index.jsp";
			}
		}else{
			return false;
		}
    });
}
function handleCheckInput(){
	if($('#login-id').val().length<=0){
		$('#login-id').css('border-color','#f70000')
		return false;
	}
	if($('#login-pwd').val().length<=0){
		$('#login-pwd').css('border-color','#f70000')
		return false;
	}
	return true;
}