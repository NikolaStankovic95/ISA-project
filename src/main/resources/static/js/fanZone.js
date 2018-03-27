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
		url : "adController/getAds",
		type : "GET",
		contentType : 'application/json',
		dataType : 'json',

		success : function(data) {
			$.each(data, function(index, ad) {
				$("#ads").append(
						"<fieldset><p>Title: </p>" + ad.name
								+ "<br> Description: " + ad.description
								+ "</fieldset>");
			})
		}
	});
}
