<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="jquery.min.js"> </script>	
	<script type="text/javascript" src="js/addAd.js"> </script>
	
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