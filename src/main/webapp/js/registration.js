$(document).on('click','#login',function(e){
	window.top.location="Login.jsp"
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
		return toastr.error("Email is required");
	}
	if(validateEmail(email)==false){
		return toastr.error("Wrong email syntax");
	}
	if(password==""){
		return toastr.error("Password is required");
	}
	if(firstname==""){
		return toastr.error("Name is required");
	}
	if(lastname==""){
		return toastr.error("Surname is required");
	}
	
	if(password!=checkpassword){
		return toastr.error("Password must be equals");

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
			toastr.success("Check email and click on registration link");
		},
		error:function(data){
			toastr.error("Email is already used!");
		}
	})
})
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}