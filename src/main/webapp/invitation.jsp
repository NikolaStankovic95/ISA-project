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

	<c:import url="_navbar.jsp"></c:import>

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