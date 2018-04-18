<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>

	<body style="margin: 15px;">
		<c:import url="_navbar.jsp"></c:import>

		<h2>Bioskopi</h2>

		<ul>
			<c:forEach var="cinema" items="${cinemas}">
				<li>
					<div>
						<a href="/institution/${ cinema.id }"><h3>${ cinema.name }</h3></a>
						<p>Adresa: ${ cinema.address }</p>
						<p>Ocena: ${ cinema.getAverageRating() }</p>
					</div>
				</li>
			</c:forEach>
		</ul>
		<c:if test="${ not empty loggedUser }">Ulogovan</c:if>
	</body>

</html>