<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<script type="text/javascript"	src="${pageContext.request.contextPath}/jquery.min.js"> </script>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/toastr.min.js"> </script>
	
	<script type="text/javascript" src="//cdn.jsdelivr.net/sockjs/1.0.3/sockjs.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/cinema.js"> </script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
<!-- Koristi stomp biblioteku da se pretplati na brokerov /topic/message endpoint -->
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cinema.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/toastr.min.css">
		
	
	<script type="text/javascript">
		$(document).ready(function() {
			var socket = new SockJS('/nekiEndpoint');
			var stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe("/topic/reservation", function(data) {
					var message = data.body;
					$('input[type=checkbox]').each(function () {
						if($(this).val()==message){
							$(this).prop('disabled','disabled')
							$(this).prop('checked','checked')
							
						}
				});
				});
		});
		})
	</script>
</head>
<body>
	<c:if test="${empty loggedUser }">
		<c:redirect url="/Login.html"/>
	</c:if>
	<nav  id="navigation" class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div id="navigation" class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>


			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul id="nav" class="nav navbar-nav">
					<c:if test="${not empty loggedUser}">
				
							<li><a  href="../userController/user/${loggedUser.id}">Profile</a></li>
							<li><a href="../../myReservations/">My reservations</a></li>
										<li class="nav-item dropdown">
      				   <a class="nav-link dropdown-toggle"  id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Create reservation
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="../../reservation/cinemaReservation">Cinema reservation</a>
          <a class="dropdown-item" href="../../reservation/theatreReservation">Theatre reservation</a>
        </div>
      </li>
					</c:if>
					<li><a href="../../reservation/cinemas">Cinemas</a></li>
					<li><a href="../../reservation/theatres">Theatres</a></li>
		
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty loggedUser}">
						<li><a href="../../Login.html" id="Login">Log in</a></li>
						<li><a href="../../Registration.html" id="Login">Registration</a></li>
			
					</c:if>
					<c:if test="${not empty loggedUser}">
						<li><a href="../updateUser.jsp">Update account</a></li>
						<li><a href="../userController/logout">Log out</a></li>
					</c:if>
					
				</ul>
			</div>
		</div>
		
	</nav>
	<c:if test="${not empty loggedUser}">
	<div class="center">	
		<div id="reservation1">
			<input type="text" id="search" class="search" placeholder="Search institution" onkeypress="combo()" /><br>
		<label>Naziv </label> <select class="form-control" id="nameOfCinema1" >
			<c:forEach items="${institutions}" var="inst">
				<option value=${inst.id }>${inst.name }</option>
			</c:forEach>
		</select> 
		<input	type="button" class="btn btn-primary" value="Next" id="Next1" onclick="next(2)"> <input
			type="hidden" id="repertoireID">

	</div>

	<div id="reservation2" style="display: none;">
		<input type="text" id="search2" placeholder="Search institution" onkeypress="combo2()" />
		<br>
		<label id="repertoire"></label> <label>Naziv </label> <select class="form-control"
			id="nameOfCinema">
			<c:forEach items="${institutions}" var="inst">
				<option value=${inst.id }>${inst.name }</option>
			</c:forEach>
		
		</select><br> <label>Projekcija</label> <select class="form-control" id="projections" name="id"></select><br>
		<label>Datum</label><br> <input type="Date" id="calendar"><br>
		<label>Termin</label> <select class="form-control" id="term"></select><br> <label>Sala</label>
		<select class="form-control" id="projectionHalls"></select><br> <input type="button"
			value="Next" id="Next2" class="btn btn-primary" onclick="next(3)"> <input
			type="button" value="Back" class="btn btn-primary" onclick="back(1)">

	</div>
	<div id="reservation3" style="display:none">
    <label class="hall3">Sala</label>
    <select class="form-control hall3" id="projectionHalls2"></select><br>
      <div class="rotatedLeft hall3"></div>
      <div class="rotatedRight hall3"></div>
      <div class="VIP hall3"></div>
      <div class="parter hall3"></div>
      <br><br><br><br>
      <input type="button" value="Next" class="btn btn-primary" id="Next3"  onclick="next(4)">
    <input type="button" value="Back" class="btn btn-primary" onclick="back(2)">
    </div>
	

	<div id="reservation4" style="display: none;">

		<label>Pozovi prijatelje</label> <select class="form-control" id="userFriends"></select> <input
			type="button" id="invite" class="btn btn-info" value="Invite"><br> <label>Pozvani
			prijatelji</label> <select class="form-control" id="invitedFriends" size="5"></select> <input
			type="button" value="Remove" class="btn btn-danger" id="removeFromList"><br><br><br>&nbsp&nbsp&nbsp <input
			type="button" value="Back" class="btn btn-primary" onclick="back(3)"> <input
			type="button" id="submit" class=" btn btn-success"value="Submit">
	</div>
	</div>
	</c:if>
	<script>

function next(rbrDIV) {
   document.getElementById("reservation"+(rbrDIV-1)).style.display = "none";
   document.getElementById("reservation"+rbrDIV).style.display = "block";
     
}

function back(rbrDIV) {

   document.getElementById("reservation"+(rbrDIV)).style.display = "block";
   document.getElementById("reservation"+(rbrDIV+1)).style.display = "none";
     
}

</script>

</body>
</html>
