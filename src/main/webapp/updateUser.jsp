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
	<nav id="navigation" class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div id="navigation" class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>


			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul id="nav" class="nav navbar-nav">
					<c:if test="${not empty loggedUser}">
				
							<li><a  href="../user/${loggedUser.id}">Profile</a></li>
							<li><a href="../../myReservations/">My reservations</a></li>
										<li class="nav-item dropdown">
      				    		   <a class="nav-link dropdown-toggle"  id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Create reservation
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="../../reservation/cinemaReservation">Cinema reservation</a>
          <a class="dropdown-item" href="../../reservation/theatreReservation">Theatre reservation</a>
        </div>
      </li>
					</c:if>
					<li><a href="../../reservation/cinemas">Cinemas</a></li>
					<li><a href="../../reservation/theatres">Theatres</a></li>
		
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty loggedUser}">
						<li><a href="../../Login.html" id="Login">Log in</a></li>
					</c:if>
					<c:if test="${not empty loggedUser}">
						<li><a href="../../updateUser.jsp">Update account</a></li>
						<li><a href="../userController/logout">Log out</a></li>
					</c:if>
					
				</ul>
			</div>
		</div>
		
	</nav>
	
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