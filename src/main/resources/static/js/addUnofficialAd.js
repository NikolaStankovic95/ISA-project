
$(document).on('click', '#add', function(e) {

	var name = $("#name").val();
	var description = $("#desc").val();

	var expirationDate = $("#date").val();
	console.log(expirationDate);

	var ad = JSON.stringify({

		"name" : name,
		"description" : description,
		"expirationDate" : expirationDate,

	})

	$.ajax({
		type : 'POST',

		url : '/adController/addUnofficialAd',
		contentType : 'application/json',
		dataType : 'json',
		data : ad,
		success : function(data) {
			window.top.location = "fanZone.html";
		}
	})

})