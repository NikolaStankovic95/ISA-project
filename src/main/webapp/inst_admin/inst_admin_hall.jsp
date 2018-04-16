<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script type="application/javascript">
            var segments = [];

            function init() {
                <c:forEach var="segment" items="${ hall.hallSegments }">
                    segments.push({
                        id: ${ segment.id },
                        type: ${ segment.type.ordinal() },
                        numberOfRows: ${ segment.numberOfRows },
                        numberOfColumns: ${ segment.numberOfColumns },
                        closed: ${ segment.closed } });
                </c:forEach>

                initSegments();

                initListeners()

            }

            function initSegments() {
                segments.forEach(function(segment) {
                    $("#segment" + segment.type).removeClass("invisible");
                    drawSeats(segment);
                    if (segment.closed) {
                        $("#segment" + segment.type).addClass("closed")
                    }
                })
            }

            function initListeners() {

                $(".segment").click(function() {
                    let id = this.id[this.id.length - 1]
                    let segment = segments.find(s => s.type == id);
                    console.log(segment);
                    $("#segmentNameFormInput").text($(this).text());
                    $("#numberOfRowsFormInput").val(segment.numberOfRows);
                    $("#numberOfColumnsFormInput").val(segment.numberOfColumns);
                    $("#isClosedFormInput").prop("checked", segment.closed);
                    $("#numberOfRowsFormInput").attr("name", id);
                    $("#numberOfColumnsFormInput").attr("name", id);
                    $("#isClosedFormInput").attr("name", id);
                });

                /*$("#numberOfRowsFormInput").change(function() {
                    segments[this.name].numberOfRows = this.value;
                    drawSeats(this.name);
                });

                $("#numberOfColumnsFormInput").change(function() {
                    segments[this.name].numberOfColumns = this.value;
                    drawSeats(this.name);
                });
                */
                $("#isClosedFormInput").change(function() {
                    let inputName = this.name;
                    let segmentIndex = segments.findIndex(seg => seg.type == this.name);
                    if(this.checked) {
                        $.ajax({
                            method: 'HEAD',
                            url: '/hall_segment/' + segments[segmentIndex].id + '/check_reservation',
                            contentType: 'application/json',
                            success: function (data) {
                                console.log(data);
                                alert("Segment ima rezervisanih mesta.");
                                return;
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                if(jqXHR.status == 404) {
                                    segments[segmentIndex].closed = true;
                                    $("#segment" + inputName).addClass("closed");
                                } else {
                                    alert(errorThrown);
                                }
                            }
                        });
                    } else {
                        segments[segmentIndex].closed = false;
                        $("#segment" + this.name).removeClass("closed");
                    }
                 });
            }

            function drawSeats(segment) {
                $("#segment" + segment.type + " :not(:first-child)").remove();
                for(let i = 0; i < segment.numberOfRows; i++) {
                    for(let j = 0; j < segment.numberOfColumns; j++) {
                        $("#segment" + segment.type).append("<input class='seat' type='checkbox'>");
                    }
                    $("#segment" + segment.type).append("<br>");
                }
                $(".seat").on("click", false);
            }

            function saveHall() {
                let name = $("#nameInput").val().trim();

                $.ajax({
                    method: 'PATCH',
                    url: '/hall/${ hall.id }',
                    contentType : 'application/json',
                    data: JSON.stringify({
                        name: name,
                        hallSegments: segments
                    }),
                    success: function(data) {
                        console.log(data);
                        alert("Uspesno apdejtovano.");
                    },
                    error: function(data) {
                        console.log(data);
                        alert("Neuspesno.");
                    }
                });
            }

            function addPeriod() {
                let day = $("#periodDayInput").val();
                let startTime = $("#periodStartInput").val();
                let endTime = $("#periodEndInput").val();

                let startDateTime = new Date(day + " " + startTime);
                let endDateTime = new Date(day + " " + endTime);

                $.ajax({
                    method: 'POST',
                    url: '/hall/${ hall.id }',
                    contentType : 'application/json',
                    data: JSON.stringify({
                        date: startDateTime,
                        dateEnd: endDateTime
                    }),
                    success: function(data) {
                        console.log(data);
                        alert('Uspesno dodat period.');
                        $("#periods").append("<p>Od " + startDateTime + " do " + endDateTime + "</p>");
                    },
                    error: function(data) {
                        console.log(data);
                        alert(data.responseText);
                    }
                });

            }
        </script>

        <style>
            .segments {
                display: flex;
                flex-direction: row;
            }

            .middle-hall {
                display: flex;
                flex-direction: column;
                border: none !important;
            }

            .invisible {
                display: none;
            }

            .segments div {
                border: 1px solid black;
            }

            .closed {
                background-color: gray;
            }
        </style>

    </head>

    <body onload="init()" style="margin: 15px;">
        <c:import url="../_navbar.jsp"></c:import>

        <h4>Osnovne informacije:</h4>
        <table>
            <tr>
                <td>Ime: </td>
                <td><input id="nameInput" type="text" value="${ hall.name }"></td>
            </tr>
        </table>
        <input type="button" value="Sacuvaj" onclick="saveHall()">

        <h4>Segmenti:</h4>

        <div class="segments">
            <div class="segment invisible" id="segment1"><p>Levi balkon</p></div>
            <div class="middle-hall">
                <div class="segment invisible" id="segment0"><p>VIP</p></div>
                <div class="segment invisible" id="segment3"><p>Parter</p></div>
            </div>
            <div class="segment invisible" id="segment2"><p>Desni balkon</p></div>
        </div>

        <table>
            <tr>
                <td>Segment:</td>
                <td><span id="segmentNameFormInput"></span></td>
            </tr>

            <tr>
                <td>Broj redova:</td>
                <td><input id="numberOfRowsFormInput" readonly type="text"></td>
            </tr>

            <tr>
                <td>Broj kolona:</td>
                <td><input id="numberOfColumnsFormInput" readonly type="text"></td>
            </tr>

            <tr>
                <td>Zatvoren:</td>
                <td><input id="isClosedFormInput" type="checkbox"></td>
            </tr>
        </table>

        <h3>Dodaj termin:</h3>
        <p>Dan:</p>
        <input type="date" id="periodDayInput">
        <p>Pocetak termina:</p>
        <input type="time" id="periodStartInput">
        <p>Kraj termina:</p>
        <input type="time" id="periodEndInput">
        <input type="submit" value="Dodaj" onclick="addPeriod()">
        <h3>Termini</h3>
        <div id="periods">
            <c:forEach var="period" items="${ hall.periods }">
                <p>
                    Od ${ period.date } do ${ period.dateEnd }
                    <c:if test="${ period.projection != null}">
                        (<a href="/projection/${ period.projection.id }">${ period.projection.name }</a>)
                    </c:if>
                </p>
            </c:forEach>
        </div>
    </body>

</html>