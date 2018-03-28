<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="application/javascript">
        function editProjection() {
            var name = $("#nameInput").val().trim();
            var description = $("#descriptionInput").val().trim();
            var actors = $("#actorsInput").val().trim();
            var genre = $("#genreInput").val().trim();
            var directorName = $("#directorNameInput").val().trim();
            var price = $("#priceInput").val().trim();
            var rating = $("#ratingInput").val().trim();

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
                    rating: rating,
                    price: price
                }),
                success: function(data) {
                    console.log(data);
                    alert("Uspesno apdejtovano.");
                },
                error: function(data) {
                    console.log(data);
                    alert("Neuspesno.");
                }
            });
        }
    </script>

</head>

<body>
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
			<td>Cena:</td>
			<td><input id="priceInput" type="text"
				value="${ projection.price }"></td>
		</tr>
		<tr>
			<td>Ocena:</td>
			<td><input id="ratingInput" type="text"
				value="${ projection.rating }"></td>
		</tr>
	</table>
	<input type="button" value="Sacuvaj" onclick="editProjection()">

</body>

</html>