<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/projection_profile.css">
    </head>

    <body style="margin: 15px;">
        <c:import url="_navbar.jsp"></c:import>

        <div class="page-layout">
            <h2>${ projection.name }</h2>

            <div class="projection-info">
                <img class="projection-image" onerror="this.src='http:///i.imgur.com/hfM1J8s.png'" src="${ projection.imageLink }">
                <table class="table">
                    <tr>
                        <td>Opis:</td>
                        <td>${ projection.description }</td>
                    </tr>
                    <tr>
                        <td>Glumci: </td>
                        <td>${ projection.actors }</td>
                    </tr>
                    <tr>
                        <td>Reditelj: </td>
                        <td>${ projection.directorName }</td>
                    </tr>
                    <tr>
                        <td>Zanr:</td>
                        <td>${ projection.genre }</td>
                    </tr>
                    <tr>
                        <td>Trajanje: </td>
                        <td>${ projection.duration } min.</td>
                    </tr>
                    <tr>
                        <td>Cena:</td>
                        <td>${ projection.price } din.</td>
                    </tr>
                    <tr>
                        <c:if test="${ projection.getAverageRating() == null }">
                            <td>Ocena:</td>
                            <td>nema ocene</td>
                        </c:if>
                        <c:if test="${ projection.getAverageRating() != null }">
                            <td>Ocena:</td>
                            <td>${ projection.getAverageRating() }</td>
                        </c:if>
                    </tr>
                </table>
            </div>

            <div>
                <jsp:useBean id="now" class="java.util.Date"/>
                <h3>Periodi prikazivanja:</h3>
                <ol>
                    <c:forEach items="${ projection.periods }" var="period">
                        <c:if test="${ period.date > now }">
                            <li>
                                <div>
                                    <p>${ period.date }</p>
                                    <ul>
                                         <c:forEach items="${ period.halls }" var="hall">
                                             <li>${ hall.name }</li>
                                         </c:forEach>
                                    </ul>
                                </div>
                            </li>
                        </c:if>
                    </c:forEach>
                </ol>
            </div>
        </div>
    </body>

</html>