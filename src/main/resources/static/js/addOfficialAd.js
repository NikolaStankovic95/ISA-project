$(document).on('click', '#add', function(e) {
	var name = $("#name").val();
	var description = $("#desc").val();

	var quantity = $("#quantity").val();
	

	var ad = JSON.stringify({

		"name" : name,
		"description" : description,
		"quantity" : quantity,

	})
	$.ajax({
			type : 'POST',

			url : '/adController/addOfficialAd',
			contentType : 'application/json',
			dataType : 'json',
			data : ad,
			success : function(data) {
				window.top.location = "fanZone.html";
			}
		})
})