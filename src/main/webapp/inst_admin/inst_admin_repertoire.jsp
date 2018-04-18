<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

		<script type="application/javascript">
			function editRepertoire() {
				var name = $("#nameInput").val().trim();
				$.ajax({
					method: 'PATCH',
					url: '/repertoire/${ repertoire.id }',
					contentType : 'application/json',
					data: JSON.stringify({ name: name }),
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

	<body style="margin: 15px;">
		<c:import url="../_navbar.jsp"></c:import>

		<h4>Osnovne informacije:</h4>
		<table>
			<tr>
				<td>Ime:</td>
				<td><input id="nameInput" type="text"
					value="${ repertoire.name }"></td>
			</tr>
		</table>
		<input type="button" value="Sacuvaj" onclick="editRepertoire()">
		<h4>Projekcije:</h4>
		<ul>
			<c:forEach var="projection" items="${ repertoire.projections }">
				<li><a href="/inst_admin/institution/${ institution.id }/projection/${ projection.id }">${ projection.name }</a>
				</li>
			</c:forEach>
		</ul>
		<a href="/inst_admin/institution/${ institution.id }/repertoire/${ repertoire.id }/create_projection">Dodaj projekciju</a>
	</body>

</html>