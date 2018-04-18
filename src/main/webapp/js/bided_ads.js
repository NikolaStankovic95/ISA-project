window.onload=function(){
	$.ajax({
		url : "/adController/getBidedAds/",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			//console.log(data[0][0]);
			
			$.each(data, function(index, ad) {
				console.log(ad);
				$("#Ads").append(
						"<fieldset>Title:"+ad[0].name+" <br> Description:  "+ ad[0].description +
						" <input type=text id=bid"+ad[0].id +" value = "+ad[1]+"></input>"
						+"<button onClick=changeBid("+ad[0].id+")>Change Bid</button>"
						+"</fieldset>");
			})
		}
	});
}

function changeBid(id){
		var bid = $("#bid"+id+"").val();
		var info = bid + "#" + id;
		console.log(info);
		$.ajax({
			url : "../adController/changeBid",
			type : "POST",
			contentType : 'application/json',
			data: info,

			success : function(data) {
				alert("Succeful bided " + data.title);
				console.log(data);
				if(data.quantity == 0){
					location.reload()
				}
			},
			error : function(error){
				alert("You must be logged");
			}
		});
	}
