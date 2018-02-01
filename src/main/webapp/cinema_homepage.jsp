<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
    <h2>Bioskopi</h2>

    <ul>
    <c:forEach var="cinema" items="${cinemas}">
        <li>
            <div>
                <h3>${ cinema.name }</h3>
                <p> ${ cinema.description } </p>
                <p> Adresa: ${ cinema.address } </p>
                <p> Ocena: ${ cinema.rating } </p>
            </div>
        </li>
    </c:forEach>
    </ul>
    <c:if test="${ not empty loggedUser }">Ulogovan</c:if>
</body>

</html>