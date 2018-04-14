<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/createinstitution.js">
	
</script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/createinstitution.css">
</head>

<body>
	<h2>Create Institution</h2>

	<input type="text" placeholder="Name" id="name"></input>
	<br>
	<select id="type">
		<option value="0">Cinema</option>
		<option value="1">Theatre</option>
	</select>
	<br>
	<textarea rows="5" cols="10" placeholder="Description" id="description"></textarea>
	<br>
	<input type="text" placeholder="Address" id="address"></input>
	<br>
	<input type="button" value="Add" id="add"></input>
	<br>
	<div class="dropdown">

		<button onclick="myFunction()" class="dropbtn">FanZone Admins</button>
		<div id="myDropdown" class="dropdown-content">
			<input type="text" placeholder="Search.." id="myInput"
				onkeyup="filterFunction()">
			<ul id='lista'>
				<c:forEach var="user" items="${users}">
					<li id="${ user.id}" value="${user.name}">${user.name}
					<button  onclick="moveToList(${user.id})">Choose</button>
					</li>
				</c:forEach>
			</ul>
		</div>
		<h3>Fanzone Admins:</h3>
		<ul id="addedAdmins"></ul>
	</div><br>
	
	<button onclick="myFunction2()" class="dropbtn">Institution Admins</button>
	<div id="myDropdown2" class="dropdown-content">
			<input type="text" placeholder="Search.." id="myInput2"
				onkeyup="filterFunction2()">
			<ul id='instlista'>
				<c:forEach var="user" items="${users}">
					<li id="inst${user.id}" value="${user.name}">${user.name}
					<button  onclick="moveToInstList(${user.id})">Choose</button>
					</li>
				</c:forEach>
			</ul>
		</div>
		<h3>Institution Admins:</h3>
		<ul id="addedInstdAdmins"></ul>
	</div>
</body>

</html>