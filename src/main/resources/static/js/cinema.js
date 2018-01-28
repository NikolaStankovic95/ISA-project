window.onload=function(){
	$.ajax({
		url:"reservationController/getCinemas",
		type:"GET",
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			$.each(data,function(index,cinema){
				$("#nameOfCinema").append("<option value=\'"+index+"\'>"+cinema.name+"</option>");
			});
			$.each(data,function(index,cinema){
				$("#nameOfCinema1").append("<option value=\'"+index+"\'>"+cinema.name+"</option>");
			});
			
		}
	})
	

}
function getCinemasHall(){
	$.ajax({
		url:"reservationController/getCinemasHall/"+$("#institutionID").val(),
		type:"GET",
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			$("#cinemasHalls").empty();
			$("#cinemasHalls2").empty();
			
			$.each(data,function(index,hall){
				$("#cinemasHalls").append("<option value=\'"+index+"\'>"+hall.name+"</option>");
			});
			$.each(data,function(index,hall){
				$("#cinemasHalls2").append("<option value=\'"+index+"\'>"+hall.name+"</option>");
			});
			
			
		}
	})
}
$(document).on('click',"#Next1",function(e){
	var cinemaID=$("#institutionID");
	alert($("#nameOfCinema1").find('option:selected').text());
	$.ajax({
		url:'reservationController/getCinemaByName/'+$('#nameOfCinema1 option:selected').text(),
		type:'GET',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			$("#institutionID").val(data.id);
			$("#nameOfCinema ").val(data.id-1);
			$("#nameOfCinema1 ").val(data.id-1);
			
		}
	})
	getCinemasHall();
		
})
$(document).on('change',"#nameOfCinema",function(e){
	$.ajax({
		url:'reservationController/getCinemaByName/'+$('#nameOfCinema option:selected').text(),
		type:'GET',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			$("#institutionID").val(data.id);
		}
	})
	getCinemasHall();
})