<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="jquery.min.js"> </script>	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
	<title>ChangePass</title>
</head>
<body>

<c:import url="_navbar.jsp"></c:import>
<form action="/registrationController/changePass/${id}" method="POST">
	<input type="password" name="oldPass" placeholder="Old password"><br>
	<input type="password" name="newPass"  placeholder="New password"><br>
	<input type="password" name="newPassRepeat" placeholder="Repaeat new password"><br>
	<input type="submit" value="Change">
</form>
</body>
</html>