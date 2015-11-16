$(function(){
	
	//Register Event
	$("#submit").click(function(){
		var result = sendRequest("/SSH/rest/user/registerCheck",$('#registerForm').serialize());
		if(!result){
			result = sendRequest("/SSH/rest/user/recordUserInfo",$('#registerForm').serialize());
			if(result){
//				alert("Registered successfully.");
				window.location.href = "./login.html";
//				window.navigate("./login.html");
			}else{
				alert("Registered failed.");	
			}
		}else{
			alert("This user name has been used, please change it and try again.");	
		}
	});
	$("#cancle").click(function(){
		alert("Cancel");
	});
});