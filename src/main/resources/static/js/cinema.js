var repertoireID;
var institution;
var hall;
var projection;
var seats=[];
var period;
window.onload=function(){
	var date=new Date();
	document.getElementById('calendar').value = date.toISOString().slice(0,10);

	$.ajax({
		url:"reservationController/getCinemas",
		type:"GET",
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			$.each(data,function(index,cinema){
				$("#nameOfCinema").append("<option value=\'"+cinema.id+"\'>"+cinema.name+"</option>");
			});
			$.each(data,function(index,cinema){
				$("#nameOfCinema1").append("<option value=\'"+cinema.id+"\'>"+cinema.name+"</option>");
			});
			
		}
	})
}
function getLoggedUser(){
	var userL;
	$.ajax({
		url:'userController/loggedUser',
		type:'GET',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			userL=data;
		}
	})
	return userL;
}
function getCinemaProjections(){
	$("#projections").empty();
	$.ajax({
		url:"reservationController/getProjections/"+$("#repertoireID").val()+"/"+$("#calendar").val(),
		type:"GET",
		contentType : 'application/json',
		dataType : 'json',
		async:false,
		success:function(data){
			
			$.each(data,function(index,projection){
				
				$("#projections").append("<option value=\'"+projection.id+"\'>"+projection.name+"</option>");
				$('#projections option:selected').val()
			});
			
			}
	})
}

function getProjectionHalls(){
	$("#projectionHalls").empty();
	$("#projectionHalls2").empty();
	if($('#projections option:selected').val()!=undefined){
		$.ajax({
			url:"reservationController/getProjectionHalls/"+$('#projections option:selected').val(),
			type:"GET",
			async:false,
			contentType : 'application/json',
			dataType : 'json',
			success:function(data){
				
				
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
}
function drawHallSeats(data){
	
    for(i=0;i<data.length;i++){
    	if(data[i].type=="LEFT_BALCONY"){
    		var div=$('.rotatedLeft').empty();
    		  
    		var rbm=0
    		div.append("<h3>Left balcony</h3>")  
    		for(r=0;r<data[i].numberOfRows;r++){
    				for(k=0;k<data[i].numberOfColumns;k++){
    					var sediste=data[i].seats[rbm]
		    			if(data[i].seats[rbm].free==false){
		    				div.append('<input class=\'check\'  type=\'checkbox\' value=\''+data[i].seats[rbm].id+'\'>');
		    				rbm++
		    			}else{
		    				div.append("<input class=\'check\' type=\'checkbox\' value=\'"+data[i].seats[rbm].id+"\'  checked  {checkStat == 1 ? disabled : }>");
		    				rbm++
		    			}
		    		}
    				div.append("<br>")
		    	}
    		
    		
    	}else if(data[i].type=="RIGHT_BALCONY"){
    		var div=$('.rotatedRight').empty();
    		  
    		var rbm=0
    		div.append("<h3>Right balcony</h3>")  
    		for(r=0;r<data[i].numberOfRows;r++){
    				for(k=0;k<data[i].numberOfColumns;k++){
		    			if(data[i].seats[rbm].free==false){
		    				div.append('<input class=\'check\'  type=\'checkbox\' value=\''+data[i].seats[rbm].id+'\'>');
		    				rbm++
		    			}else{
		    				div.append("<input class=\'check\' type=\'checkbox\' value=\'"+data[i].seats[rbm].id+"\'  checked  {checkStat == 1 ? disabled : }>");
		    				rbm++
		    			}
		    		}
    				div.append("<br>")
		    	}
    		
    		
    	}
    }
}
function convertTime(time){
	var date = new Date(time);
	// Hours part from the timestamp
	var hours = date.getHours();
	// Minutes part from the timestamp
	var minutes = "0" + date.getMinutes();

	var formattedTime = hours + ':' + minutes.substr(-2) ;
	return formattedTime;
}
function getProjectionPeriods(){
	$("#term").empty();
	if($('#projections option:selected').val()!=undefined){

		
		$.ajax({
			url:"reservationController/getProjectionsPeriod/"+$('#projections option:selected').val(),
			type:"GET",
			async:false,
			contentType : 'application/json',
			dataType : 'json',
			success:function(data){
			
				
				$.each(data,function(index,period){
					
					$("#term").append("<option value=\'"+period.id+"\'>"+convertTime(period.date)+"</option>");
					
				})
			
			}
		})
	}
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
			institution=data;
			$("#repertoireID").val(data.repertoire.id);
			$("#nameOfCinema ").val(data.id);
			$("#nameOfCinema1 ").val(data.id);
			
		}
	})
	getCinemaProjections();
	getProjectionPeriods();
	getProjectionHalls();

		
})
$(document).on('change',"#calendar",function(e){
	getCinemaProjections();
	getProjectionHalls();
	getProjectionPeriods();
	
})
$(document).on('click',"#invite",function(e){
	var selectedSeats=0;
	$("input:checkbox[type=checkbox]:checked").each(function(){
		
		if($(this).prop('disabled')==false){
			selectedSeats++;
		}
	});
	var numberOfInvited=$("#invitedFriends option").length;
	if(numberOfInvited<selectedSeats-1){
		var id=$("#userFriends option:selected").val();
		var text=$("#userFriends option:selected").text();
		if(id!="" && text!=""){
			$("#invitedFriends").append('<option value=\''+id+'\'>'+text+'</option>')
			$('#userFriends').find('option:selected').remove().end();
		}
	}else{
		alert("You must select more seats!")
	}

})
$(document).on('click',"#removeFromList",function(e){
	var id=$("#invitedFriends option:selected").val();
	var text=$("#invitedFriends option:selected").text();
	if(id!="" && text!=""){
		$("#userFriends").append('<option value=\''+id+'\'>'+text+'</option>')
		$('#invitedFriends').find('option:selected').remove().end();
	}
})
$(document).on('change',"#projectionHalls2",function(e){
	getSeats($('#projectionHalls2 option:selected').val())
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
function findInvitedUsers(index){
	var user;
	var invited=$("#invitedFriends option");
	alert("Covek "+invited[index-1].value)
	
	$.ajax({
		url:'userController/userID/'+invited[index-1].value,
		type:'GET',
		contentType : 'application/json',
		dataType : 'json',
		async:false,
		success:function(data){
			user=data;
		}
	})
	return user;
}

$(document).on('click',"#submit",function(e){
	var checkList=[];
	$("input:checkbox[type=checkbox]:checked").each(function(){
		
		if($(this).prop('disabled')==false){
			checkList.push($(this).val());
		}
	});
	
	if(checkList.length!=0){
		reserveSeats(checkList);
		
		console.log("SEDISTA"+seats);
		getSelectedPeriod();
		getHallById();
		getProjectionById();
		u=getLoggedUser();
		var data=JSON.stringify({
				"institution":institution,
				"hall":hall,
				"seats":seats[0],
				"projection":projection,
				"period":period,
				"owner":u
				
		})
		
		callReservation(data,"false");
	}			
	if(seats.length>1){
		$.each(seats,function(index,seat){
			if( index === 0 )
		        return true;
			var data=JSON.stringify({
				"institution":institution,
				"hall":hall,
				"seats":seats[index],
				"projection":projection,
				"period":period,
				"owner":findInvitedUsers(index)
			
				
		})
		callReservation(data,"true");
		
		})
	}
})
function callReservation(data,invite){
	
	$.ajax({
		url:'reservationController/makeReservation/'+invite,
		data:data,
		type:'PATCH',
		contentType : 'application/json',
		dataType : 'json',
		async:false,
		success:function(data){
			console.log(data);
		}
	})
	
}
function getSeats(data){
	
	var data=JSON.stringify({
		"instID":$('#nameOfCinema option:selected').val(),
		"hallID":data,
		"periodID":$('#term option:selected').val(),
		"projectionID":$('#projections option:selected').val()
		
})
	$.ajax({
		url:'reservationController/getHallSeats',
		data:data.toString(),
		type:'POST',
		async:false,
		contentType : 'application/json',
		dataType : 'json',
		success:function(data){
			drawHallSeats(data);
		}
})
}
$(document).on('click',"#Next3",function(e){
	$("#userFriends").empty();
	$.ajax({
		url:'userController/findUserFriends',
		type:"GET",
		success:function(data){
			var invited=$("#invitedFriends option");
			var selectedSeats=0;
			$("input:checkbox[type=checkbox]:checked").each(function(){
				
				if($(this).prop('disabled')==false){
					selectedSeats++;
				}
			});
			if(invited.length!=0 && selectedSeats<=invited.length){
				invited[length].remove()
			}
			var invited=$("#invitedFriends option");
			if(invited.length!=0){
				$.each(data,function(index,friendship){
					$.each(invited,function(index,f){
						if(friendship.friend.id!=f.value){
							$("#userFriends").append("<option value=\'"+friendship.friend.id+"\'>"+friendship.friend.name+" "+friendship.friend.surname+"</option>")
							
						}
					})
				})
			}else{
				$.each(data,function(index,friendship){
					$("#userFriends").append("<option value=\'"+friendship.friend.id+"\'>"+friendship.friend.name+" "+friendship.friend.surname+"</option>")
					
					
				})
			}
		}
	})
})

$(document).on('click',"#Next2",function(e){
	$("#projectionHalls2").val($('#projectionHalls option:selected').val())
	getSeats($('#projectionHalls option:selected').val());
})
function getCinemaRepertorie(){
	$.ajax({
		url:'reservatinoController/getCinemaRepertoire'+$('#nameOfCinema option:selected').val(),
		type:'GET',
		success:function(data){
			$.each(data,function(index,repertoire){
				
			})
		}
	})
}
function getHallById(){
	$.ajax({
		url:'reservationController/findHallById/'+$('#projectionHalls option:selected').val(),
		type:'GET',
		contentType:'application/json',
		dataType:'json',
		async:false,
		success:function(data){
			hall=data;
		}
	})
}
function reserveSeats(data){
	
	$.ajax({
		url:'reservationController/getReservedSeats/'+data.toString()+'/'+$('#projectionHalls option:selected').val(),
		type:'GET',
		contentType:'application/json',
		dataType:'json',
		async:false,
		success:function(data){
			seats=data;
			console.log(seats);
		}
	})
}
function getProjectionById(){
	alert($('#projections option:selected').val())
	$.ajax({
		url:'reservationController/findProjectionById/'+$('#projections option:selected').val(),
		type:'GET',
		contentType:'application/json',
		dataType:'json',
		async:false,
		success:function(data){
			projection=data;
		}
	})
}
function getSelectedPeriod(){
	$.ajax({
		url:'reservationController/getSelectedPeriod/'+$('#term option:selected').val(),
		type:'GET',
		contentType:'application/json',
		dataType:'json',
		async:false,
		success:function(data){
			period=data;
		}
	})
}