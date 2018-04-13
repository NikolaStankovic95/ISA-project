<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/invitation.js"> </script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/invitation.css">

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
				
							<li><a  href="../userController/user/${loggedUser.id}">Profile</a></li>
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
						<li><a href="../updateUser.jsp">Update account</a></li>
						<li><a href="../userController/logout">Log out</a></li>
					</c:if>
					
				</ul>
			</div>
		</div>
		
	</nav>
	

	<c:if test="${loggedUser.email==user.email}">
			<c:if test="${empty reservation }">
		 <h1>You already accept this invitaion.</h1>
		</c:if>

	
	<c:if test="${ not empty reservation }" >
	<p>Your friend ${reservation.owner.name} ${reservation.owner.surname} invited you on this
		event:</p>
	<table border="1" style="width:50%;" class="table table-hover">
		<tr>
			<td>Institution</td>
			<td>${reservation.institution.name}</td>
		</tr>
		<tr>
			<td>Projection</td>
			<td>${reservation.projection.name}</td>
		</tr>
		<tr>
			<td>Hall</td>
			<td>${reservation.hall.name}</td>

		</tr>
		<tr>
			<td>Period</td>
			<td>${reservation.period.date}</td>

		</tr>
		<tr>
			<td>Seat</td>
			<td>${reservation.seat.regNumber}</td>

		</tr>
	</table>
	
		<br>
		<form action="../../invitationController/accept/${reservation.id}">
			<input type="submit" class="btn btn-success" value="Accept" id="accept">
		</form>
		<form action="../../invitationController/reject/${reservation.id}">
			<input type="submit" class="btn btn-danger" value="Reject" id="reject">
		</form>
	</c:if>
	
	</c:if>
</body>
</html>