$(document).on('click','.delete',function(e){
	e.preventDefault();
	var confirmed=confirm("Are you sure you want to decline reservation?")
	if(confirmed){
		var url=$(this).attr("href");
		$.ajax({
			url:url,
			type:"DELETE",
			success:function(data){
				$("#table").empty();
				if(data!=null){
					$.each(data,function(index,reservation){
						
						$("#table").append("<tr><td>"+reservation.institution.name+"</td>" +
								"<td>"+reservation.hall.name+"</td>" +
								"<td>"+reservation.projection.name+"</td>" +
								"<td>"+reservation.period.date+"</td>" +
								"<td>"+reservation.seats.regNumber+"</td>" +
								"<td><a class=\"delete\" href='/myReservations/delete/"+reservation.id+"\'>Decline</a></td></tr>");
					})
				}
			}
		})
	}
})