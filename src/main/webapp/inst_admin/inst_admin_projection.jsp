<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

		<script type="application/javascript">
			var periods = [];
            var halls = [];
			var image_url;

 			function init() {
                initListeners();
            }

            function initListeners() {
                $("#selectHall").change(function () {
                    $.ajax({
                        method: 'GET',
                        url: '/hall/' + this.value + '/free_periods',
                        dataType: 'json',
                        success: function(data) {
                            $("#selectPeriod").empty();
                            for(let period of data) {
                                console.log(period);
                                $("#selectPeriod").append('<option value=' + period.id + '>'+ new Date(period.date) +'</option>');
                            }
                        },
                        error: function(data) {
                            console.log(data);
                            alert("Neuspesno.");
                        }
                    });
                });
            }

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


			function editProjection() {
				var name = $("#nameInput").val().trim();
				var description = $("#descriptionInput").val().trim();
				var actors = $("#actorsInput").val().trim();
				var genre = $("#genreInput").val().trim();
				var directorName = $("#directorNameInput").val().trim();
				var price = $("#priceInput").val().trim();
				var duration = $("#durationInput").val().trim();

				console.log(periods);

				$.ajax({
					method: 'PATCH',
					url: '/projection/${ projection.id }',
					contentType : 'application/json',
					data: JSON.stringify({
						name: name,
						description: description,
						actors: actors,
						genre: genre,
						directorName: directorName,
						duration, duration,
						price: price,
						halls: halls,
						periods: periods,
						imageLink: image_url
					}),
					success: function(data) {
						console.log(data);
						alert("Uspesno apdejtovano.");
					},
					error: function(data) {
						alert(data);
						console.log("Neuspesno.");
					}
				});
			}


			function deleteProjection() {
				$.ajax({
					method: 'DELETE',
					url: '/projection/${ projection.id }',
					success: function(data) {
						console.log(data);
						alert("Uspesno izbrisana projekcija.");
					},
					error: function(data) {
						console.log(data);
						alert(data.responseText);
					}
				});
			}

			function addDateAndHall() {
                if (periods.filter(e => e.id == $("#selectPeriod").val()).length > 0 && halls.filter(e => e.id == $("#selectHall").val()).length > 0) {
                    return;
                }
                periods.push({ id: $("#selectPeriod").val() });
                halls.push({ id: $("#selectHall").val() });
                $("#selectedPeriods").append('<p>Sala ' + $("#selectHall").val() + ' Datum: ' + $("#selectPeriod").text()+'</p>');
            }

		</script>

	</head>

	<body onload="init()" style="margin: 15px;">
		<c:import url="../_navbar.jsp"></c:import>

		<h4>Osnovne informacije:</h4>
		<table>
			<tr>
				<td>Ime:</td>
				<td><input id="nameInput" type="text"
					value="${ projection.name }"></td>
			</tr>
			<tr>
				<td>Opis:</td>
				<td><input id="descriptionInput" type="text"
					value="${ projection.description }"></td>
			</tr>
			<tr>
				<td>Glumci:</td>
				<td><input id="actorsInput" type="text"
					value="${ projection.actors }"></td>
			</tr>
			<tr>
				<td>Zanr:</td>
				<td><input id="genreInput" type="text"
					value="${ projection.genre }"></td>
			</tr>
			<tr>
				<td>Ime reditelja:</td>
				<td><input id="directorNameInput" type="text"
					value="${ projection.directorName }"></td>
			</tr>
			<tr>
                <td>Slika:</td>
                <td><input id="imageInput" type="file"></td>
                <td><input type="button" value="Aploaduj" onclick="uploadImage()"></td>
            </tr>
			<tr>
				<td>Trajanje:</td>
				<td><input id="durationInput" type="text"
					value="${ projection.duration }"></td>
			</tr>
			<tr>
				<td>Cena:</td>
				<td><input id="priceInput" type="text"
					value="${ projection.price }"></td>
			</tr>
		</table>

		<select id="selectHall">
            <option value="-1">Izaberi salu...</option>
            <c:forEach var="hall" items="${ institution.halls }">
                <option value="${ hall.id } ">${ hall.name }</option>
            </c:forEach>
        </select>
        <select id="selectPeriod">

		</select>
		<br>
		<input type="button" value="Dodaj salu" onclick="addDateAndHall()">
        <ul id="selectedPeriods">

        </ul>

		<input type="button" value="Sacuvaj" onclick="editProjection()">
		<input type="button" value="Izbrisi projekciju" onclick="deleteProjection()">
	</body>

</html>