
function acceptRequest(id){
	alert("Aloooo")
	$.ajax({
		url:'../../invitationController/accept/'+id,
		type:'GET',
		
	})
}