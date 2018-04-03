<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
	<c:if test="${ empty loggedUser }">
		<a href="Login.html">Login</a>
		<a href="Registration.html">Register</a>
	</c:if>
	<hr>
	<div>
		<a href="reservation/cinemas">Bioskopi</a> <a
			href="reservation/theatres">Pozorista</a>
	</div>
</body>

</html>