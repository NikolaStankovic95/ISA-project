<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/institution_profile.css">

        <script>
            function makeFastReservation(reservationId) {
                $.ajax({
                    method: 'POST',
                    url: '/reservation/' + reservationId + '/fast_reserve',
                    success: function(data) {
                        console.log(data);
                        alert("Uspesno rezervisano.");
                        $("#fastReservation" + reservationId).remove();
                    },
                    error: function(data) {
                        console.log(data);
                        alert("Neuspesna rezervacija.");
                    }
                });
            }
        </script>
    </head>

    <body style="margin: 15px;">
        <c:import url="_navbar.jsp"></c:import>
        <div class="page-layout">
            <div class="main-container">
                <div>
                    <h2>${ institution.name }</h2>
                    <div class="info-container">
                        <table class="table">
                            <tr>
                                <td>Opis:</td>
                                <td>${ institution.description }</td>
                            </tr>
<<<<<<< HEAD
=======

                            <tr>
                                <td>Adresa:</td>
                                <td>${ institution.address }</td>
                            </tr>

                            <tr>
                                <td>Ocena:</td>

                                <c:if test="${ institution.getAverageRating() != 'NaN' }">
                                    <td>${ institution.getAverageRating() }</td>
                                </c:if>

                                <c:if test="${ institution.getAverageRating() == 'NaN' }">
                                    <td>nema ocene</td>
                                </c:if>

                            </tr>

                        </table>
                    </div>
                </div>

                <div id="map"></div>
            </div>

            <hr>

            <div class="logged-user-actions-container">
                <div class="repertoire-container">
                    <h3>Repertoar</h3>
                    <c:forEach var="projection" items="${ institution.repertoire.projections }">
                        <div class="projection-preview">
                            <img class="projection-image" onerror="this.src='http:///i.imgur.com/hfM1J8s.png'" src="${ projection.imageLink }">
                            <a href="/projection/${ projection.id }">${ projection.name }</a>
                        </div>
                        <hr>
                    </c:forEach>
                </div>

                <div class="fast-reservation-container">
                    <h3>Brze rezervacije</h3>
                    <c:forEach var="fastReservation" items="${ fastReservations }">
                        <div id="fastReservation${ fastReservation.id }">
                            <div class="fast-reservation-preview">
                                <p>Projekcija: ${ fastReservation.projection.name }</p>
                                <p>Cena: ${ fastReservation.getDiscountedPrice() }</p>
                                <p>Hala: ${ fastReservation.hall.name }</p>
                                <p>Segment: ${ fastReservation.hallSegment.type }</p>
                                <p>Sediste: ${ fastReservation.seat.regNumber }</p>
                                <p>Popust: ${ fastReservation.discount }%</p>
                                <input type="button" class="btn btn-primary" value="Reservisi" onclick="makeFastReservation(${ fastReservation.id })">
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
>>>>>>> branch 'master' of https://github.com/jova1111/ISA-project.git

                            <tr>
                                <td>Adresa:</td>
                                <td>${ institution.address }</td>
                            </tr>

                            <tr>
                                <td>Ocena:</td>

                                <c:if test="${ institution.getAverageRating() != 'NaN' }">
                                    <td>${ institution.getAverageRating() }</td>
                                </c:if>

                                <c:if test="${ institution.getAverageRating() == 'NaN' }">
                                    <td>nema ocene</td>
                                </c:if>

                            </tr>

                        </table>
                    </div>
                </div>

                <div id="map"></div>
            </div>

            <hr>

            <div class="logged-user-actions-container">
                <div class="repertoire-container">
                    <h3>Repertoar</h3>
                    <c:forEach var="projection" items="${ institution.repertoire.projections }">
                        <div class="projection-preview">
                            <img class="projection-image" onerror="this.src='http:///i.imgur.com/hfM1J8s.png'" src="${ projection.imageLink }">
                            <a href="/projection/${ projection.id }">${ projection.name }</a>
                        </div>
                        <hr>
                    </c:forEach>
                </div>

                <div class="fast-reservation-container">
                    <h3>Brze rezervacije</h3>
                    <c:forEach var="fastReservation" items="${ fastReservations }">
                        <div id="fastReservation${ fastReservation.id }">
                            <div class="fast-reservation-preview">
                                <p>Projekcija: ${ fastReservation.projection.name }</p>
                                <p>Cena: ${ fastReservation.getDiscountedPrice() }</p>
                                <p>Hala: ${ fastReservation.hall.name }</p>
                                <p>Segment: ${ fastReservation.hallSegment.type }</p>
                                <p>Sediste: ${ fastReservation.seat.regNumber }</p>
                                <p>Popust: ${ fastReservation.discount }%</p>
                                <input type="button" class="btn btn-primary" value="Reservisi" onclick="makeFastReservation(${ fastReservation.id })">
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>

<<<<<<< HEAD
        <div>
            <h3>${ institution.name }</h3>
            <a href = "/fanzone/${institution.id}">Fan zone </a>
            <img src='${ institution.image}' height = 100 width = 100 >
            <p>Adresa: ${ institution.address }</p>
            <p>Ocena: ${ institution.getAverageRating() }</p>
=======
            <script>
                function initMap() {
                    $.get('https://maps.googleapis.com/maps/api/geocode/json?address=${ institution.address }', function(data){

                        var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: 17,
                            center: data.results[0].geometry.location
                        });
                        var marker = new google.maps.Marker({
                            position: data.results[0].geometry.location,
                            map: map
                        });
                    })

                }
            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGntqueG2mBLJzfEuOAHNluE8yQSVS5JA&callback=initMap">
            </script>

            <c:if test="${ not empty loggedUser }">Ulogovan</c:if>
>>>>>>> branch 'master' of https://github.com/jova1111/ISA-project.git
        </div>
<<<<<<< HEAD

            <script>
                function initMap() {
                    $.get('https://maps.googleapis.com/maps/api/geocode/json?address=${ institution.address }', function(data){

                        var map = new google.maps.Map(document.getElementById('map'), {
                            zoom: 17,
                            center: data.results[0].geometry.location
                        });
                        var marker = new google.maps.Marker({
                            position: data.results[0].geometry.location,
                            map: map
                        });
                    })

                }
            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAGntqueG2mBLJzfEuOAHNluE8yQSVS5JA&callback=initMap">
            </script>

            <c:if test="${ not empty loggedUser }">Ulogovan</c:if>
        </div>
=======
>>>>>>> branch 'master' of https://github.com/jova1111/ISA-project.git
    </body>

</html>
