$(document).on('click', '#homebtn', function(e) {
	console.log("USAO");
	$("#Home").show();
	$("#Ads").hide();
})

$(document).on('click', '#adbtn', function(e) {
	console.log("USAO");
	$("#Home").hide();
	$("#Ads").show();
})
var ads = [];
window.onload = function() {
	$.ajax({
		url : "adController/getInitAds",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			console.log(data)
			$.each(data, function(index, ad) {
				ads.push(ad);
				console.log(ad);
				$("#Ads").append(
						"<fieldset><img src="+ad.imageUrl+" height = 100 width = 100 ><br>Title:" + ad.name + "<br> Description: "
								+ ad.description
								+ "<br><button onClick=approve(" + ad.id
								+ ")>Approve</button>"
								+ "<button onClick=refuse(" + ad.id
								+ ")>Refuse</button></fieldset>");
			})
		}
	});
}

function approve(id) {
	var foundedAd = [];
	console.log(ads);
	for(i=0;i<ads.length;i++){
		console.log(ads);
		if(ads[i].id==id){
			foundedAd = ads[i];
			break;
		}
	}
	console.log(foundedAd)
	$.ajax({
		url : "adController/approveAd/" + id,
		type : "PATCH",
		contentType : 'application/json',
		dataType : 'json',
		data:JSON.stringify(foundedAd),
		success : function(data)  {
			location.reload()
		},
		error: function(error){
			alert("PONUDA JE VEC PREUZETA")
			}
		})
		
	
	
}
function refuse(id) {
	$.ajax({
		url : "adController/refuseAd/" + id,
		type : "PATCH",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			location.reload()
		}
	});
}