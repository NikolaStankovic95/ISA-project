<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

		<script type="application/javascript">
			function chooseInstitution() {
				var institutionId = $("#institutionSelect").val();
				window.location.href = "/inst_admin/institution/" + institutionId;
			}
		</script>

	</head>

	<body style="margin: 15px;">
		<c:import url="../_navbar.jsp"></c:import>

		<h4>Izaberi instituciju:</h4>

		<select id="institutionSelect">
			<c:forEach var="institution" items="${ institutions }">
				<option value="${ institution.id } ">${ institution.name }
					<c:if test="${ institution.type == 'CINEMA' }"> (bioskop)</c:if>
					<c:if test="${ institution.type == 'THEATRE' }"> (pozoriste)</c:if>
				</option>
			</c:forEach>
			<input type="button" value="Dalje" onclick="chooseInstitution()">
		</select>

	</body>

</html>