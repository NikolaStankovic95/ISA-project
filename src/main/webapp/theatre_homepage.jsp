<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
	<h2>Pozorista</h2>

	<ul>
		<c:forEach var="theatre" items="${theatres}">
			<li>
				<div>
					<a href="/institution/${ theatre.id }"><h3>${ theatre.name }</h3></a>
					<p>${ theatre.description }</p>
					<p>Adresa: ${ theatre.address }</p>
					<p>Ocena: ${ theatre.rating }</p>
				</div>
			</li>
		</c:forEach>
	</ul>
</body>

</html>