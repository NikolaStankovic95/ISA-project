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


</head>
<body>


	<c:if test="${loggedUser.email==user.email}">


	<p>Your friend ${reservation.owner.name} ${reservation.owner.surname} invited you on this
		event:</p>
	<c:if test="${ not empty reservation }" >
	<table border="1">
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
			<input type="submit" value="Accept" id="accept">
		</form>
		<form action="../../invitationController/reject/${reservation.id}">
			<input type="submit" value="Reject" id="reject">
		</form>
	</c:if>
	</c:if>
</body>
</html>