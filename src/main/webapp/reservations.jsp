<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"> </script>	
<script type="text/javascript"	src="${pageContext.request.contextPath}/toastr.min.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/reservations.js"> </script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reservations.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/toastr.min.css">
		

</head>
<body>
	<c:if test="${empty loggedUser }">
		<c:redirect url="/Login.html"/>
	</c:if>

	<c:import url="_navbar.jsp"></c:import>

	<c:if test="${not empty loggedUser }">
	<table id="table" style="width:50%;" class="table table-hover" border=1>
		<tr class="header">
				<th>Institution</th>
				<th>Hall</th>
				<th>Projection</th>
				<th>Date & Time</th>
				<th>Seat number</th>
				<th>Hall segment</th>
				<th></th>
			</tr>
		
	<c:forEach items="${reservations}" var="reservation">
		<c:if test="${loggedUser.email==reservation.owner.email}">
			<tr>
				<td>${reservation.institution.name}</td>
				<td>${reservation.hall.name}</td>
				<td>${reservation.projection.name}</td>
				<td>${reservation.period.date}</td>
				<td>${reservation.seats.regNumber}</td>
				<td>${reservation.hallSegment.type }</td>
				<td><a class="delete" href="/myReservations/delete/${reservation.id}">Decline</a></td>
			</tr>
		</c:if>
	
	</c:forEach>
	</table>
	</c:if>
</body>

</html>