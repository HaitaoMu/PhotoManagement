function sendRequest(url,params){
	var resultMsg = "";
	$.ajax({
		  type: "POST",
		  url: url,
		  data: params,
		  async: false,
          success:function(result){
        	  resultMsg = result;
          },
          fail:function(){

          }
	});
	return resultMsg;
}