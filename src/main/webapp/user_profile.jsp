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

	<c:if test="${empty loggedUser }">
	<c:redirect url="/Login.html"/>
	</c:if>
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
  	<label>Lista prijatelja</label>
  		<ul id="friendList">
		</ul>
	<label>Lista zahteva</label>
  	
		<ul id="friendRequestList">
		</ul>
	<input type="text" name="name" id="searchName" placeholder="Name">
	<input type="text" name="surname" id="searchSurname" placeholder="Surname">
	<input type="button" id="searchUsers" value="Search">
	
	<label>List korisnika</label>
	
		<ul id="users">
		</ul>
  	</c:if>
 	
	
		
	
   
    
</body>
</html>