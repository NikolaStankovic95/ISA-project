$(document).on('click', '#chng', function(e) {
	var login = $("#login").val();
	var ad = $("#ad").val();

	var seat = $("#seat").val();
	var friend = $("#friend").val();
	var gold = $("#gold").val();
	var silver = $("#silver").val();
	var bronze = $("#bronze").val();

	
	var points = JSON.stringify({

		"login" : login,
		"seatReserved" : seat,
		"adReserved" : ad,
		"addedFriend" : friend,
		"gold" : gold,
		"silver" : silver,
		"bronze" : bronze
	})
	$.ajax({
			type : 'POST',

			url : '../points/change',
			contentType : 'application/json',
			dataType : 'json',
			data : points,
			success : function(data) {
				alert("OK");
				location.reload();
			}
		});
})