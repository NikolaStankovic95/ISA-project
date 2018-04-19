var url = window.location.href;
	var fzid = url.substring(url.lastIndexOf('/')+1,url.length);

	window.onload = function() {
	
	console.log("id je" + fzid);
	$.ajax({
		url : "../adController/getOfficialAds/"+fzid,
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			console.log(data);
			$.each(data, function(index, ad) {
				
				$("#OfficialAds").append(
						"<fieldset><img src="+ad.imageUrl+" height = 100 width = 100 ><br>Title:" + ad.name
								+ "<br> Description: " + ad.description
								+ "<button onClick=Reserve("+ad.id+")>Reserve</button>" 
								+"<button onClick=removeAd("+ad.id+")>Remove</button>"
								+"<button onClick=changeAd("+ad.id+")>Change</button></fieldset>");
			})
		}
	});
	
	$.ajax({
		url : "../adController/getUnofficialAds/"+fzid,
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			$.each(data, function(index, ad) {
				$("#UnofficialAds").append(
						"<fieldset><img src="+ad.imageUrl+" height = 100 width = 100 >Title:" + ad.name
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
	
	window.top.location="/fanzone/createofficial#"+fzid;
})
$(document).on('click', '#addAdun', function(e) {
	console.log("Pogodio");
	window.top.location="/fanzone/createunofficial#"+fzid;
})

function Reserve(id){
	$.ajax({
		url : "../adController/reserveAd/"+id,
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

function removeAd(id){
	$.ajax({
		url : "../adController/deleteAd/"+id,
		type : "POST",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
				location.reload()
			
		},
		error : function(error){
			alert("You dont have premission to delete this");
		}
	});
}
function makeBid(id){
	var bid = $("#bid"+id+"").val();
	var info = bid + "#" + id;
	console.log(info);
	$.ajax({
		url : "../adController/makeBid",
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

function changeAd(id){
	window.top.location="../adController/changeAd/"+id;
}