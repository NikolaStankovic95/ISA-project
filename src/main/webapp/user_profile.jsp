<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/userProfile.js"> </script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">


</head>
<body>
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
						<li><a href="../logout">Log out</a></li>
					</c:if>
					
				</ul>
			</div>
		</div>
		
	</nav>
	<div class="lists">
		<div class="searchDiv">
			<input type="text" disabled id="name" value="${ user.name }">
			<input type="text" disabled id="surname" value="${ user.surname }">
			<input type="text" disabled id="email" value="${ user.email } ">
			<input type="text" disabled id="city" value="${ user.city }">
			<input type="text" disabled id="number" value="${ user.number }">
			<input type="hidden" id="userId" value="${user.id}">
		
			<c:if test="${not empty loggedUser && loggedUser.email!=user.email }">
					<div id="friendRequest"></div>
			</c:if>
		</div>
	<c:if test="${loggedUser.email==user.email }">
		<div class="history">
			<h2>History</h2>
			<br>
			<ul id="visitedCinemaProjections">
				<c:forEach items="${loggedUser.reservations}" var="reservation">
					<c:if test="${reservation.accepted==true }">
						<li><a>${reservation.projection.name}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		</div>
		<div class="lists">
				<div class="friends">
					<h2>Friends:</h2>
					<ul id="friendList"></ul>
				</div>
				<div class="friends">
					<h2>Friend request</h2>
					<ul id="friendRequestList"></ul>
				</div>
		<div class="searchDiv">	
			<div class="lists">
				<input type="text" id="nameSearch" placeholder="Type name">
				<input type="text" id="surnameSearch" placeholder="Type surname">
				<input type="button" id="search" class="btn btn-warning"  value="Search">
				<input type="button" id="reset" class="btn btn-danger" value="Reset">
				</div>
		<table border=1 class="table table-hover" id="notFriends">
		<tr class="header">
			<th>Name</th>
			<th>Surname</th>
			<th></th>
			</tr>
		<c:forEach items="${notFriends}" var="addFriend">
			<tr id='${addFriend.id}'>
			
			<td>${addFriend.name}</td>
			<td> ${addFriend.surname}</td>
			<td><input type="button" class="btn btn-info" value="Send request"  onclick="sendFriendRequest('${addFriend.id}')"></td>
			</tr>
			
		</c:forEach>
		
			</table>
			</div>
		</div>
  	</c:if>
 	
</body>
</html>