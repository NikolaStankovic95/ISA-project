<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
<h2>Pozorista</h2>

<ul>
    <c:forEach var="theatre" items="${theatres}">
        <li>${ theatre.name }</li>
    </c:forEach>
</ul>
</body>

</html>