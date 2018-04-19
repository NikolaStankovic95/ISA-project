<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"> </script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/changeAd.js"> </script>
	
	<title>Create Ad</title>
</head>
<body>

<div>
	<c:import url="_navbar.jsp"></c:import>
	${ad.fanZone.id}
	<input type="text" id="name" placeholder="Name of Ad" name="" value="${ad.name}"><br>

	<input type="text"  id="desc" placeholder="Description" name="" value="${ad.description}"><br>

	<input type = "text" id="quantity" placeholder="Quantity" name="" value="${ad.quantity}"><br>
	
	<input type="button" id="add" value="Change" onClick="changeAd(${ad.id},${ad.fanZone.id})"><br>
</div>

</body>
</html>