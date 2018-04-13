<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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

        function saveProjection() {
            var name = $("#nameInput").val().trim();
            var description = $("#descriptionInput").val().trim();
            var actors = $("#actorsInput").val().trim();
            var genre = $("#genreInput").val().trim();
            var directorName = $("#directorNameInput").val().trim();
            var price = $("#priceInput").val().trim();
            var duration = $("#durationInput").val().trim();

            if(halls.size == 0 || periods.size == 0) {
                alert("Niste izabrali halu.");
                return;
            }

            $.ajax({
                method: 'POST',
                url: '/repertoire/${ repertoire.id }/projection',
                contentType : 'application/json',
                data: JSON.stringify({
                    name: name,
                    description: description,
                    actors: actors,
                    genre: genre,
                    directorName: directorName,
                    price: price,
                    halls: halls,
                    periods: periods,
                    imageLink: image_url,
                    duration: duration
                }),
                success: function(data) {
                    console.log(data);
                    alert("Uspesno dodata projekcija.");
                },
                error: function(data) {
                    console.log(data);
                    alert(data.responseText);
                }
            });
        }

        function uploadImage() {
            let image = $('#imageInput').prop('files')[0];
            var formData = new FormData();
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

        function addDateAndHall() {
            if (periods.filter(e => e.id == $("#selectPeriod").val()).length > 0) {
                return;
            }
            if (halls.filter(e => e.id == $("#selectHall").val()).length > 0) {
                return;
            }
            periods.push({ id: $("#selectPeriod").val() });
            halls.push({ id: $("#selectHall").val() });
            $("#selectedPeriods").append('<p>Sala ' + $("#selectHall").val() + ' Datum: ' + $("#selectPeriod").text()+'</p>');
        }
        
    </script>

</head>

<body onload="init()">
<h4>Osnovne informacije:</h4>
<table>
    <tr>
        <td>Ime:</td>
        <td><input id="nameInput" type="text"></td>
    </tr>
    <tr>
        <td>Opis:</td>
        <td><input id="descriptionInput" type="text"></td>
    </tr>
    <tr>
        <td>Glumci:</td>
        <td><input id="actorsInput" type="text"></td>
    </tr>
    <tr>
        <td>Zanr:</td>
        <td><input id="genreInput" type="text"></td>
    </tr>
    <tr>
        <td>Trajanje:</td>
        <td><input id="durationInput" type="text"></td>
    </tr>
    <tr>
        <td>Ime reditelja:</td>
        <td><input id="directorNameInput" type="text"></td>
    </tr>
    <tr>
        <td>Cena:</td>
        <td><input id="priceInput" type="text"></td>
    </tr>
    <tr>
        <td>Slika:</td>
        <td><input id="imageInput" type="file"></td>
        <td><input type="button" value="Aploaduj" onclick="uploadImage()"></td>
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
<input type="button" value="Dodaj salu" onclick="addDateAndHall()">
<ul id="selectedPeriods">

</ul>
<br>
<hr>
<input type="button" value="Sacuvaj" onclick="saveProjection()">
</body>

</html>