
$(document).on('click', '#add', function(e) {

	var name = $("#name").val();
	var description = $("#desc").val();

	var expirationDate = $("#date").val();
	var image = $("#i")
	console.log(expirationDate);

	var ad = JSON.stringify({

		"name" : name,
		"description" : description,
		"expirationDate" : expirationDate,
		"imageUrl" : image_url, 

	})
	var url = window.location.href;
	var id = url.substring(url.lastIndexOf('#')+1,url.length);
	$.ajax({
		type : 'POST',

		url : '/adController/addUnofficialAd/'+id,
		contentType : 'application/json',
		dataType : 'json',
		data : ad,
		success : function(data) {
			window.top.location = "fanZone.html";
		}
	})

})
function uploadImage() {
            let image = $('#imageInput').prop('files')[0];
            let formData = new FormData();
            formData.append("image", image);
            $.ajax({
                method: 'POST',
                headers: {
                    'Authorization': 'Client-ID c98199048ba3773',
                    'Accept': 'application/json'
                },
                url: 'https://api.imgur.com/3/image',
                data: formData,
                processData: false,
                contentType: false,
                mimeType: 'multipart/form-data',
                success: function(data) {
                    image_url = JSON.parse(data).data.link;
                    alert("Uspesno aploadovana.");
                },
                error: function(data) {
                    console.log(data);
                    alert("Neuspesno.");
                }
            });
}