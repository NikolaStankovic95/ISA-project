<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"> </script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/reservations.js"> </script>


</head>
<body>

	<table id="table" border=1>
	<c:forEach items="${reservations}" var="reservation">
		<c:if test="${loggedUser.email==reservation.owner.email}">
			<tr>
				<td>${reservation.institution.name}</td>
				<td>${reservation.hall.name}</td>
				<td>${reservation.projection.name}</td>
				<td>${reservation.period.date}</td>
				<td>${reservation.seats.regNumber}</td>
				<td><a class="delete" href="/myReservations/delete/${reservation.id}">Decline</a></td>
			</tr>
		</c:if>
	
	</c:forEach>
	</table>
</body>

</html>