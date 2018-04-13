window.onload = function(){
	$.ajax({
		type:'GET',
		
		url:'/userController/loggedUser',
		contentType : 'application/json',
		dataType : 'json',
		
		success:function(data){
			console.log(data)
			$("#email").val(data.email)
			$("#name").val(data.name)
			$("#surname").val(data.surname)
			$("#city").val(data.city)
			$("#number").val(data.number)
			
			
		}
	})
}
$(document).on('click','#change',function(e){
	var email = $("#email").val();
	var name = $("#name").val();
	var surname = $("#surname").val();
	var city = $("#city").val();
	var number = $("#number").val();
	
	var user=JSON.stringify({
		"email":email,
		
		"name":name,
		"surname":surname,
		"city":city,
		"number":number,
		
	})
	$.ajax({
		type:'POST',
		
		url:'/registrationController/changeUser',
		contentType : 'application/json',
		dataType : 'json',
		data : user,
		success:function(data){
			toastr.success("Successfully changed");
			$("#email").val(data.email);
			$("#name").val(data.name);
			$("#surname").val(data.surname);
			$("#city").val(data.city);
			$("#number").val(data.number);
			
		}
	})
})