<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
    <h2>Bioskopi</h2>

    <ul>
    <c:forEach var="cinema" items="${cinemas}">
        <li>${ cinema.name }</li>
    </c:forEach>
    </ul>
</body>

</html>