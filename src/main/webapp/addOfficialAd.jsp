<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"> </script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/addOfficialAd.js"> </script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
	<title>Create Ad</title>
</head>
<body>
<c:import url="_navbar.jsp"></c:import>
<div>
	
	<input type="text" id="name" placeholder="Name of Ad" name=""><br>

	<input type="text"  id="desc" placeholder="Description" name=""><br>

	<input type = "text" id="quantity" placeholder="Quantity" name=""><br>
	
	<input id="imageInput" type="file" placeholder="Image">
     <input type="button" value="Aploaduj" onclick="uploadImage()">
	<input type="button" id="add" value="Add"><br>
</div>

</body>
</html>