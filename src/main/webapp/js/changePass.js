$(document).on('click','#change',function(e){
	var data=JSON.stringify({
		"oldPass":$("#oldPass"),
		"newPass":$("newPass"),
		"newPassRepeat":$("newPassRepeat")
	})
	$.ajax({
		url:'/registrationController/changePass/'+$("#id").val(),
		type:'POST',
		data:data,
		contentType:'application/x-www-form-urlencoded',
		success:function(data){
			window.top.location=data;
			
		}
	})
	
})