<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
	<script>
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
	
</head>

<body>
	<c:import url="_navbar.jsp"></c:import>
	<div class="main-container">
		<a href="/institution/theatres"><img class="institution_image" src="https://i.imgur.com/eCKG3qm.jpg"></a>
		<a href="/institution/cinemas"><img class="institution_image" src="https://i.imgur.com/Rj6ElKS.jpg"></a>
	</div>
</body>

</html>