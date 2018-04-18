<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

	<body style="margin: 15px;">
		<c:import url="_navbar.jsp"></c:import>

		<h2>Pozorista</h2>

		<ul>
			<c:forEach var="theatre" items="${theatres}">
				<li>
					<div>
						<a href="/institution/${ theatre.id }"><h3>${ theatre.name }</h3></a>
						<p>${ theatre.description }</p>
						<p>Adresa: ${ theatre.address }</p>
						<p>Ocena: ${ theatre.getAverageRating() }</p>
					</div>
				</li>
			</c:forEach>
		</ul>
	</body>

</html>