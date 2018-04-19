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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/createinstitution.css">
</head>

<body>
<c:import url="_navbar.jsp"></c:import>
	<h2>Create Institution</h2>

	<input type="text" placeholder="Name" id="name"></input>
	<br>
	<select id="type">
		<option value="0">Cinema</option>
		<option value="1">Theatre</option>
	</select>
	<br>
	<textarea rows="5" cols="20" placeholder="Description" id="description"></textarea>
	<br>
	<input type="text" placeholder="Address" id="address"
		onkeypress='changeMap()'></input>
	<br>
	<div id="googleMap" style="width: 50%; height: 400px;"></div>

	<script>
function myMap() {
var mapProp= {
    center:new google.maps.LatLng(51.508742,-0.120850),
    zoom:5,
};
var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
}
</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGntqueG2mBLJzfEuOAHNluE8yQSVS5JA&callback=myMap"></script>

</body>
<input id="imageInput" type="file" placeholder="Image">
<input type="button" value="Aploaduj" onclick="uploadImage()">
<br>

<div class="dropdown">

	<button onclick="myFunction()" class="dropbtn">FanZone Admins</button>
	<div id="myDropdown" class="dropdown-content">
		<input type="text" placeholder="Search.." id="myInput"
			onkeyup="filterFunction()">
		<ul id='lista'>
			<c:forEach var="user" items="${users}">
				<c:if test="${user.role == 'FANZONEADMIN' }">
					<li id="${ user.id}" value="${user.name}">${user.name}
						<button onclick="moveToList(${user.id})">Choose</button>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<h3>Fanzone Admins:</h3>
	<ul id="addedAdmins"></ul>
</div>
<br>

<button onclick="myFunction2()" class="dropbtn">Institution
	Admins</button>
<div id="myDropdown2" class="dropdown-content">
	<input type="text" placeholder="Search.." id="myInput2"
		onkeyup="filterFunction2()">
	<ul id='instlista'>
		<c:forEach var="user" items="${users}">
			<c:if test="${user.role == 'INSTADMIN' }">
				<li id="inst${user.id}" value="${user.name}">${user.name}
					<button onclick="moveToInstList(${user.id})">Choose</button>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>
<h3>Institution Admins:</h3>
<ul id="addedInstdAdmins"></ul>

<input type="button" value="Add" id="add"></input>
<br>
</body>

</html>