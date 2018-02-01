$(document).on('click','#login',function(e){
	var email=$("#email").val();
	var password=$("#password").val();
	
	var user=JSON.stringify({
		"email":email,
		"password":password,
		
	})
	$.ajax({
		type:'POST',
		url:'/registrationController/login',
		contentType : 'application/json',
		dataType : 'json',
		data:user,
		success:function(data){
			if(data!=null){
				alert("Ulogovoan");
				window.top.location="Cinema.html";
			}
			else {
				alert("Prihvatite aktivacioni mail");
				window.top.location="Login.html";
			}
			
		}
	})
})