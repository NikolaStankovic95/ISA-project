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
			if(data!="/Login.html"){
				window.top.location=data;
				
			}else{
				alert("You inserted wrong email or password");
				
			}
			
		}
	})
})
$(document).on('click','#register',function(e){
	window.top.location="Registration.html";
})