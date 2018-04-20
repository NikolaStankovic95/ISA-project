<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cinema_homepage.css">
	</head>

	<body style="margin: 15px;">
		<c:import url="_navbar.jsp"></c:import>
		<div class="page-layout">
			<h2>Bioskopi</h2>

			<ul>
				<c:forEach var="cinema" items="${ cinemas }">
					<li>
						<div>
							<a href="/institution/${ cinema.id }"><h3>${ cinema.name }</h3></a>
							<p>Adresa: ${ cinema.address }</p>
							<c:if test="${ cinema.getAverageRating() != 'NaN' }">
								<p>Ocena: ${ cinema.getAverageRating() }</p>
							</c:if>
							<c:if test="${ cinema.getAverageRating() == 'NaN' }">
								<p>Ocena: nema ocene.</p>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</body>

</html>