<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"> </script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/userProfile.js"> </script>


</head>
<body>

	<input type="text" disabled id="name" value="${ user.name }">  <br>
	<input type="text" disabled id="surname" value="${ user.surname }"><br>
	
	<input type="text" disabled id="email" value="${ user.email } "> <br>
	<input type="text" disabled id="city" value="${ user.city }"> <br>
	<input type="text" disabled id="number" value="${ user.number }">  <br>
  	<input type="hidden" id="userId" value="${user.id}">
  	
  	<c:if test="${loggedUser.email!=user.email }">
  		<div id="friendRequest"></div>
  	</c:if>
  	<c:if test="${loggedUser.email==user.email }">
  		<ul id="friendList">
		</ul>
		<ul id="friendRequestList">
		</ul>
		<p> History</p><br>
		<ul id="visitedCinemaProjections">
		<c:forEach items="${loggedUser.reservations}" var="reservation">
			<c:if test="${reservation.institution.type=='CINEMA'}">
			<li><a>${reservation.projection.name}</a></li>
			</c:if>
		</c:forEach>
		</ul>
			<input type="text" id="nameSearch" placeholder="Type name">
			<input type="text" id="surnameSearch" placeholder="Type surname">
			<input type="button" id="search" value="Search">
			<input type="button" id="reset" value="Reset">
			
		<table border=1 id="notFriends">
		<tr class="header">
			<th >Name</th>
			<th >Surname</th>
			</tr>
		<c:forEach items="${notFriends}" var="addFriend">
			<tr id='${addFriend.id}'>
			
			<td>${addFriend.name}</td>
			<td> ${addFriend.surname}</td>
			<td><input type="button"  value="Send request"  onclick="sendFriendRequest('${addFriend.id}')"></td>
			</tr>
			
		</c:forEach>
		
		</table>
  	</c:if>
 	
	
		
	
   
    
</body>
</html>