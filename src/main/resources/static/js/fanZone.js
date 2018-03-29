window.onload = function() {

	$.ajax({
		
		url : "fanZoneController/getFanzone",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {

			$("#name").append(data.name);
		}

	});

	$.ajax({
		url : "adController/getOfficialAds",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			$.each(data, function(index, ad) {
				$("#OfficialAds").append(
						"<fieldset>Title:" + ad.name
								+ "<br> Description: " + ad.description
								+ "<button onClick=Reserve("+ad.id+")>Reserve</button></fieldset>");
			})
		}
	});
	
	$.ajax({
		url : "adController/getUnofficialAds",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			$.each(data, function(index, ad) {
				$("#UnofficialAds").append(
						"<fieldset>Title:" + ad.name
								+ "<br> Description: " + ad.description
								+ "<input type=text id=bid"+ad.id+"></input><button onClick=makeBid("+ad.id+")>Make bid</button></fieldset>");
			})
		}
	});
	
}

$(document).on('click', '#shop', function(e) {
	
	$("#officialShop").show();
	$("#Ads").hide();
})

$(document).on('click', '#adshop', function(e) {
	
	$("#officialShop").hide();
	$("#Ads").show();
})

$(document).on('click', '#addAd', function(e) {
	console.log("Pogodio");
	window.top.location="addOfficialAd.html";
})
$(document).on('click', '#addAdun', function(e) {
	console.log("Pogodio");
	window.top.location="addUnofficialAd.html";
})

function Reserve(id){
	$.ajax({
		url : "adController/reserveAd/"+id,
		type : "POST",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			alert("Succeful reserved " + data.title);
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

function makeBid(id){
	var bid = $("#bid"+id+"").val();
	var info = bid + "#" + id;
	console.log(info);
	$.ajax({
		url : "adController/makeBid",
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