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
<script type="text/javascript"	src="${pageContext.request.contextPath}/toastr.min.js"> </script>
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/toastr.min.css">
	

</head>
<body>
	<c:import url="_navbar.jsp"></c:import>

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
			  <jsp:useBean id="today" class="java.util.Date" />
  			
			<ul id="visitedCinemaProjections">
				
				<c:forEach items="${loggedUser.reservations}" var="reservation">
					<c:if test="${ reservation.accepted==true && reservation.period.date lt today && !reservation.projection.deleted }">
						<li><a href="/projection/${ reservation.projection.id }">${reservation.projection.name}</a> (<a href="/projection/${ reservation.projection.id }/rate">oceni</a>)</li>
					</c:if>
					<c:if test="${ reservation.accepted==true && reservation.period.date lt today && reservation.projection.deleted }">
						<li>${reservation.projection.name}</li>
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