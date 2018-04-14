window.onload = function() {
	$.ajax({
		url : "adController/getMyAds",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',
		success : function(data) {
			console.log(data)
				$.each(data, function(index, ad) {
					$("#ads").append(
							"<fieldset>Title:" + ad.reservedAd.name
									+ "<br> Description: " + ad.reservedAd.description
									+ "<br> Bid: "+ ad.bid
									+ "<br><button onClick=accept("+ad.reservedAd.id+","+ad.user.id+")>Accept</button></fieldset>");
	})
		}
	});
}

function accept(adId,userId){
	var info = adId + "#" + userId;
	$.ajax({
		url : "adController/notifyUsers/",
		type : "PATCH",
		contentType : 'application/json',
		data: info,

		success : function(data) {
			alert("All users are notifyed");
			},
		
		error : function(error){
			alert("You must be logged");
		}
	});
}