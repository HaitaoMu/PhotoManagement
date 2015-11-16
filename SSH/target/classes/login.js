$(function(){
	
	//Register Event
	$("#login").click(function(){
		var result = sendRequest("/SSH/rest/user/validateLogin",$('#loginForm').serialize());
		if(result){
			window.location.href = "../bootstrap/demo1.html";
		}else{
			window.location.href = "./register.html";
		}
	});
	$("#cancle").click(function(){
		
	});
});