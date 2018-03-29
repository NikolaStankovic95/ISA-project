$(document).on('click','#add',function(e){
	
	var name = $("#name").val();
	var description = $("#desc").val();
	var fanzoneid = 1;
	
	var expirationDate = $("#date").val();
	console.log(expirationDate);
	
	var ad=JSON.stringify({
		
		"name":name,
		"description":description,
		"expirationDate" : expirationDate,
		
		
	})
	console.log(ad)
	
	
	
	
    $.ajax({
		type:'POST',
		
		url:'/adController/addAd',
		contentType : 'application/json',
		dataType : 'json',
		data: ad,
		success:function(data){
			window.top.location="fanZone.html";
		}
	})
	
})