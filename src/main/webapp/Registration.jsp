<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/toastr.min.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/registration.js"> </script>
	
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/toastr.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
		
<title>Registration</title>
</head>
<body>
	<c:import url="_navbar.jsp"></c:import>
	
	<div class="regDiv">
	  <div class="content">
	  	<table>
		<tr >
			<td >Email address:</td>
			<td><input type="text" id="email" ></td>
		 </tr>

		<tr>
			<td>Password:</td>
			<td><input type="password" id="password" ></td>
		</tr>
	
		<tr><td>Repeat password:</td><td><input type="password" id="checkpassword"></td></tr> 
		<tr><td>First name:</td><td><input	type="text" id="firstname" ></td></tr>

		<tr><td>Last name:</td><td><input type="text" id="lastname" ></td></tr>

		<tr><td>City:</td><td><input type="text" id="city" ></td></tr>

		<tr><td>Phone number:</td><td><input type="text" id="phone" ></td></tr>


		<tr>
			<td  align="center"><input type="button" id="login" class="btn btn-primary" value="Login"></td>
			<td  align="center"><input type="button" id="register" class="btn btn-primary" value="Register"></td>
			</tr>
		</table>
		</div>
	</div>

</body>
</html>