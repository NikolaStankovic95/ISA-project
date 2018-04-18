$(document).on('click', '#add', function(e) {
	var name = $("#name").val();
	var description = $("#desc").val();

	var quantity = $("#quantity").val();
	

	var ad = JSON.stringify({

		"name" : name,
		"description" : description,
		"quantity" : quantity,

	})
	var url = window.location.href;
	var id = url.substring(url.lastIndexOf('#')+1,url.length);
	$.ajax({
			type : 'POST',

			url : '/adController/addOfficialAd/'+id,
			contentType : 'application/json',
			dataType : 'json',
			data : ad,
			success : function(data) {
				alert("OK");
				window.top.location="/fanzone/"+id;
			}
		})
})