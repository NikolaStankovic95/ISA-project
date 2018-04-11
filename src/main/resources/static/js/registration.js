$(document).on('click','#login',function(e){
	window.top.location="Login.html"
})
$(document).on('click','#register',function(e){
	var email=$("#email").val();
	var password=$("#password").val();
	var checkpassword=$("#checkpassword").val();
	var firstname=$("#firstname").val();
	var lastname=$("#lastname").val();
	var city=$("#city").val();
	var phone=$("#phone").val();
	
	
	if(email==""){
		return alert("Email is required");
	}
	if(validateEmail(email)==false){
		return alert("Wrong email syntax");
	}
	if(password==""){
		return alert("Password is required");
	}
	if(firstname==""){
		return alert("Name is required");
	}
	if(lastname==""){
		return alert("Surname is required");
	}
	
	if(password!=checkpassword){
		return alert("Password must be equals");

	}
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
		url:'/registrationController/register',
		contentType : 'application/json',
		dataType : 'json',
		data:user,
		success:function(data){
			alert("Check email and click on registration link");
		},
		error:function(data){
			alert("Email is already used!");
		}
	})
})
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}