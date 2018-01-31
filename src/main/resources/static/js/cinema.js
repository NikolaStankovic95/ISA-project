var repertoireID;
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
function getCinemaProjections(){

	$.ajax({
		url:"reservationController/getProjections/"+$("#repertoireID").val(),
		type:"GET",
		contentType : 'application/json',
		dataType : 'json',
		async:false,
		success:function(data){
			$("#projections").empty();
			$.each(data,function(index,projection){
				
				$("#projections").append("<option value=\'"+projection.id+"\'>"+projection.name+"</option>");
				$('#projections option:selected').val()
			});
			
			}
	})
}

function getProjectionHalls(){
	$.ajax({
		url:"reservationController/getProjectionHalls/"+$('#projections option:selected').val(),
		type:"GET",
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			$("#projectionHalls").empty();
			$("#projectionHalls2").empty();
			
			$.each(data,function(index,hall){
				
				$("#projectionHalls").append("<option value=\'"+hall.id+"\'>"+hall.name+"</option>");
				
			})
			$.each(data,function(index,hall){
				alert(hall)
				$("#projectionHalls2").append("<option value=\'"+hall.id+"\'>"+hall.name+"</option>");
				
			})
		
		}
	})
}
function drawHallSeats(data){
	var div=$('#div');
	var brmesta=data.length;
	var rbm=0;
	var redova=Math.ceil(brmesta/5);
	console.log(redova);
	var brkolona=5;
	
	for(var i=0;i<redova;i++){
	  for(var j=0;j<brkolona;j++){
	   var zauzeto=data[rbm].free;
	    if(zauzeto==false){
	      div.append('<input type=\'checkbox\'>');
	     }else {

	      div.append("<input type=\'checkbox\' checked  {checkStat == 1 ? disabled : }>");
	  
	      }
	    rbm++;
	  }
	  
	    brmesta=brmesta-5;
	  if(brmesta<5){
	    brkolona=brmesta;
	  }

	  div.append("<br>");
	}

}
function getProjectionPeriods(){
	alert($('#projections option:selected').val())
	$.ajax({
		url:"reservationController/getProjectionsPeriod/"+$('#projections option:selected').val(),
		type:"GET",
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
		
			$("#term").empty();
			
			$.each(data,function(index,period){
				
				$("#term").append("<option value=\'"+period.id+"\'>"+period.date+"</option>");
				
			})
		
		}
	})
}
$(document).on('click',"#Next1",function(e){
	
	var repertoireID=$("#repertoireID");
	
	alert($("#nameOfCinema1").find('option:selected').text());
	$.ajax({
		url:'reservationController/getCinemaByName/'+$('#nameOfCinema1 option:selected').text(),
		type:'GET',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			
			$("#repertoireID").val(data.repertoire.id);
			$("#nameOfCinema ").val(data.id-1);
			$("#nameOfCinema1 ").val(data.id-1);
			
		}
	})
	getCinemaProjections();
	getProjectionHalls();
	getProjectionPeriods();
		
})

$(document).on('change',"#projections",function(e){

	//getCinemaProjections();
	getProjectionHalls();
	getProjectionPeriods();
	
})

$(document).on('change',"#nameOfCinema",function(e){
	$.ajax({
		url:'reservationController/getCinemaByName/'+$('#nameOfCinema option:selected').text(),
		type:'GET',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			
			$("#repertoireID").val(data.repertoire.id);
			
		}
	})
	getCinemaProjections();
	getProjectionHalls();
	getProjectionPeriods();
	
})
$(document).on('click',"#Next2",function(e){
	
		$.ajax({
		url:'reservationController/getHallSeats/'+$('#projectionHalls2 option:selected').val(),
		type:'GET',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			
				drawHallSeats(data);
		}
	})
		
})
