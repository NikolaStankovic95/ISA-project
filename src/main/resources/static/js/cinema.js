window.onload=function(){
	$.ajax({
		url:"reservationController/getCinema",
		type:"GET",
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			$.each(data,function(index,cinema){
				$("#nameOfCinema").append("<option>"+cinema.name+"</option>");
			});
			
		}
	})
}
