<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>

    <body>
        <div>
            <h3>${ projection.name }</h3>
            <p>Opis: ${ projection.description }</p>
            <p>Glumci: ${ projection.actors }</p>
            <p>Zanr: ${ projection.genre }</p>
            <c:if test="${ projection.getAverageRating() == null }">
                <p>Ocena: nema ocena.</p>
            </c:if>
            <c:if test="${ projection.getAverageRating() != null }">
                <p>Ocena: ${ projection.getAverageRating() }.</p>
            </c:if>
            <img src="${ projection.imageLink }">
        </div>


        <c:if test="${ not empty loggedUser }">Ulogovan</c:if>
    </body>

</html>