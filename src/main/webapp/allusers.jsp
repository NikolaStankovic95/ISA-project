<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/allusers.js">
	
</script>

</head>

<body>
	<h1>All users</h1>
	<ul id='lista'>
		<c:forEach var="user" items="${users}">
			<li value="${user.name}">${user.name}<select id="${user.id}">
					<option
						<c:if test="${user.role==USER}">selected ="selected"</c:if> value=0>User</option>
					<option
						<c:if test="${user.role=='FANZONEADMIN'}"> selected="selected"</c:if>value=2>Fanzone Admin</option>
					<option
						<c:if test="${user.role=='INSTADMIN'}"> selected="selected"</c:if>value=3>Inst Admin</option>
					<option
						<c:if test="${user.role=='SYSADMIN'}"> selected="selected"</c:if>value=1>Sys Admin</option>
					</select>
					<button  onclick="changeRole(${user.id})">Choose</button>
					</li>
				</c:forEach>
			</ul>
</body>

</html>