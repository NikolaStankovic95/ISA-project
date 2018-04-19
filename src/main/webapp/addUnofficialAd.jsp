<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"> </script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/addUnofficialAd.js"> </script>
	
	<title>Create Ad</title>
</head>
<body>
<c:import url="_navbar.jsp"></c:import>
<div>
	
	<input type="text" id="name" placeholder="Name of Ad" name=""><br>

	<input type="text"  id="desc" placeholder="Description" name=""><br>

	<input type = "date" id="date" placeholder="Expiration date" name=""><br>
	<input id="imageInput" type="file" placeholder="Image">
     <input type="button" value="Aploaduj" onclick="uploadImage()">
	<input type="button" id="add" value="Add"><br>
</div>

</body>
</html>