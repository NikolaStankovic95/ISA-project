<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	 <script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/toastr.min.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/updateUser.js"> </script>
	
		<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

   <link rel="stylesheet" href="${pageContext.request.contextPath}/toastr.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateUser.css">
	
	<title>Update User</title>
</head>
<body>
	<c:if test="${empty loggedUser }">
		<c:redirect url="/Login.html"/>
	</c:if>

	<c:import url="_navbar.jsp"></c:import>
<div>
	<table>
		<tr><td>Email</td><td> <input type="text" id = "email" class="form-control" name="Email"></td></tr>
		<tr><td>City</td><td>  <input type="text" id = "city" class="form-control" name="City"></td></tr>
		<tr><td>Name</td><td><input type="text" id = "name" class="form-control" name="Name"></td></tr>
		<tr><td>Surname</td><td><input type="text" id= "surname" class="form-control" name="Surname"></td></tr>
		<tr><td>Number</td><td> <input type="text" id = "number" class="form-control" name="number"></td></tr>
		<tr><td colspan=2 align=center><input type="button" class="btn btn-primary" id="change" value="Change"></td></tr>
	
	</table>
</div>
</body>
</html>