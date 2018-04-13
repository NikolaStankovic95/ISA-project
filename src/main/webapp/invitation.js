
function acceptRequest(id){
	
	$.ajax({
		url:'../../invitationController/accept/'+id,
		type:'GET',
		success:function(data){
			toastr.success("You accepted invitation");
		}
	})
}