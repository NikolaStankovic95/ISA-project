<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js">
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fanZoneAdmin.js">
	
</script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>FanZone Admin</title>
</head>
<body>
<c:import url="_navbar.jsp"></c:import>
	<div class="tab">
		<button class="tablinks" id = "homebtn" >Home</button>
		<button class="tablinks" id = "adbtn">Ads</button>

	</div>

	<!-- Tab content -->
	<div id="Home" class="tabcontent">
		<h3>HOME</h3>
	</div>

	<div id="Ads" class="tabcontent" style="display: none">
		
	</div>

	<a href=changePass.jsp>Change Password</a>



</body>
</html>