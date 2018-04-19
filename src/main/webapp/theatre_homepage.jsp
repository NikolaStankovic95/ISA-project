<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cinema_homepage.css">
	</head>

	<body style="margin: 15px;">
		<c:import url="_navbar.jsp"></c:import>
		<div class="page-layout">
			<h2>Pozorista</h2>

			<ul>
				<c:forEach var="theatre" items="${theatres}">
					<li>
						<div>
							<a href="/institution/${ theatre.id }"><h3>${ theatre.name }</h3></a>
							<p>Adresa: ${ theatre.address }</p>
							<c:if test="${ theatre.getAverageRating() != 'NaN' }">
								<p>Ocena: ${ theatre.getAverageRating() }</p>
							</c:if>
							<c:if test="${ theatre.getAverageRating() == 'NaN' }">
								<p>Ocena: nema ocene.</p>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</body>

</html>