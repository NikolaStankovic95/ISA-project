<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/toastr.min.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/login.js"> </script>
	
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/toastr.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
	
<title>Login</title>
</head>
<body>
	<c:import url="_navbar.jsp"></c:import>
	
	<div class="logDiv">
		<div class="content">
			<label >Username:</label> <input type="text" id="email"><br> 
			<label>Password:</label> <input type="password" id="password"><br>
			 <input	type="button" class="btn btn-primary" id="login" value="Login"> 
			 <input type="button" class="btn btn-primary" id="register" value="Registration"><br>
			</div>
	</div>
</body>
</html>