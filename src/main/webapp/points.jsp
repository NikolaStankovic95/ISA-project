<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/points.js">
	
</script>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/createinstitution.css">
</head>

<body>
<c:import url="_navbar.jsp"></c:import>
	<h2>Points</h2>
	Login: <input value="${points.login }" id="login"></input><br>
	Reserved seat: <input value="${points.seatReserved } " id="seat"></input><br>
	Reserved Ad: <input value="${points.adReserved }" id="ad"
	></input><br>
	Added friend: <input value="${points.addedFriend }" id="friend"></input><br>
	Gold user: <input value="${points.gold } " id="gold"></input><br>
	Silver user: <input value="${points.silver }" id="silver"
	></input><br>
	Bronze user: <input value="${points.bronze }" id="bronze"></input><br>
	<input type="button" value="Change" id="chng"></input>
</body>

</html>