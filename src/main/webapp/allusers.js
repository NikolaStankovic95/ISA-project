function changeRole(id){
	var e = document.getElementById(id);
	var role = e.options[e.selectedIndex].text.replace(/\s/g, "").toUpperCase();
	var str = id+ "#" + role;
	console.log(str);
	$.ajax({
		url:'/userController/changeRole',
		type:'PATCH',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		data:str,
		success:function(data){
			alert("Uloga je promenjena");
			location.reload();
		}
	});

}