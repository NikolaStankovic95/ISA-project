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
window.onload = function() {
	$.ajax({
		url : "adController/getInitAds",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			$.each(data, function(index, ad) {
				console.log(ad);
				$("#Ads").append(
						"<fieldset>Title:" + ad.name + "<br> Description: "
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
	$.ajax({
		url : "adController/approveAd/" + id,
		type : "PATCH",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			location.reload()
		}
	});
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