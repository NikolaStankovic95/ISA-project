$(document).on('click','#register',function(e){
	var email=$("#email").val();
	var password=$("#password").val();
	var checkpassword=$("#checkpassword").val();
	var firstname=$("#firstname").val();
	var lastname=$("#lastname").val();
	var city=$("#city").val();
	var phone=$("#phone").val();
	
	var user=JSON.stringify({
		"email":email,
		"password":password,
		"name":firstname,
		"surname":lastname,
		"city":city,
		"number":phone,
		
	})
	$.ajax({
		type:'POST',
		url:'/userController/register',
		contentType : 'application/json',
		dataType : 'json',
		data:user,
		success:function(data){
			alert("Korisnik registrovan"+data.email)
		}
	})
})