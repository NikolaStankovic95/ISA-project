function convertDate(time){
	var item=new Date(time);
	var date=item.getDate()
	var month=item.getMonth()+1
	var year=item.getYear()+1900
	var h=item.getHours()
	var m=item.getMinutes()
	var s=item.getSeconds()
	var mili=item.getMilliseconds()
	var formatted=year;
	if(month<10){
		formatted+="-0"+month+"-"+date+" "+h+":"+m;
	}else{
		formatted=+"-"+month+"-"+date+" "+h+":"+m;
	}
	if(s<10){
		formatted+=":0"+s+"."+mili;
	}else
		formatted+=":"+s+"."+mili
	return formatted;
}
$(document).on('click','.delete',function(e){
	e.preventDefault();
	var confirmed=confirm("Are you sure you want to decline reservation?")
	if(confirmed){
		var url=$(this).attr("href");
		$.ajax({
			url:url,
			type:"DELETE",
			success:function(data){
				if(data.length!=0){
					$("tr:not('.header')").remove();
					
					$.each(data,function(index,reservation){
						
						$("#table").append("<tr><td>"+reservation.institution.name+"</td>" +
								"<td>"+reservation.hall.name+"</td>" +
								"<td>"+reservation.projection.name+"</td>" +
								"<td>"+convertDate(reservation.period.date)+"</td>" +
								"<td>"+reservation.seats.regNumber+"</td>" +
								"<td><a class=\"delete\" href='/myReservations/delete/"+reservation.id+"\'>Decline</a></td></tr>");
					})
				}else if(data.length==0){
					$("tr:not('.header')").remove();
					
				}
				else{
					alert("You can decline your reservation only 30 minutes before projection starts!")
				}
			}
		})
	}
})