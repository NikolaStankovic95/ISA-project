function changeAd(id) {
	var name = $("#name").val();
	var description = $("#desc").val();

	var quantity = $("#quantity").val();
	

	var ad = JSON.stringify({

		"name" : name,
		"description" : description,
		"quantity" : quantity,

	})
	console.log(id)
	
	$.ajax({
			type : 'PATCH',

			url : '/adController/changeAdValue/'+id,
			contentType : 'application/json',
			dataType : 'json',
			data : ad,
			success : function(data) {
				alert("OK");
				window.top.location="/fanzone/"+id;
			}
		})
}