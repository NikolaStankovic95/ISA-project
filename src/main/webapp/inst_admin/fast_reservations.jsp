<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script>

            function init() {
                initListeners();
            }

            function initSegments(segments) {
                hideAllHallSegments();
                segments.forEach(function(segment) {
                    $("#segment" + segment.type).removeClass("invisible");
                    drawSeats(segment);
                    if (segment.closed) {
                        $("#segment" + segment.type).addClass("closed")
                    }
                });
            }

            function initListeners() {
                $("#hallSelect").change(function() {
                    hideAllHallSegments();
                    if($("#hallSelect").val() == -1) {
                        $("#periodSelect").empty();
                        return;
                   } 

                   $.ajax({
                        method: 'GET',
                        url: '/reservation/findHallById/' + $("#hallSelect").val(),
                        dataType: 'json',
                        success: function(data) {
                            initSegments(data.hallSegments);
                        },
                        error: function(data) {
                            console.log(data);
                        }
                    });

                    $.ajax({
                        method: 'GET',
                        url: '/projection/${ projection.id }/hall/' + $("#hallSelect").val() + '/periods',
                        dataType: 'json',
                        success: function(data) {
                            $("#periodSelect").empty();
                            $("#periodSelect").append('<option value="-1">Izaberite termin...</option>');
                            for(let periodJson of data) {
                                $("#periodSelect").append('<option value="' + periodJson.id + '">' + new Date(periodJson.date) + '</option>');
                            }
                        },
                        error: function(data) {
                            console.log(data);
                        }
                    });
                });

                $("#periodSelect").change(function () {
                    if($("periodSelect").val() == -1) {
                        hideSegmentContainer();
                        return;
                    }

                    $.ajax({
                        method: 'GET',
                        url: '/projection/${ projection.id }/hall/' + $("#hallSelect").val() + '/period/' + $("#periodSelect").val() + '/reserved_seats',
                        dataType: 'json',
                        success: function(data) {
                            enableAllSeats();
                            for(reservedSeat of data) {
                                $("#seat" + reservedSeat.id).prop("disabled", true);
                            }
                            showSegmentContainer();
                        },
                        error: function(data) {
                            console.log(data);
                        }
                    });

                });
            }

            function reserveSeat(id) {
                $.ajax({
                        method: 'POST',
                        url: '/inst_admin/fast_reservation/projection/${ projection.id }/hall/' + $("#hallSelect").val() + '/period/' + $("#periodSelect").val() + '/seat/' + id,
                        success: function(data) {
                            alert("Uspesno napravaljena brza rezervacija.");
                            $("#seat" + id).prop("checked", false);
                            $("#seat" + id).prop("disabled", true);
                        },
                        error: function(data) {
                            console.log(data);
                        }
                    });
            }

            function drawSeats(segment) {
                $("#segment" + segment.type + " :not(:first-child)").remove();
                let seatIndex = 0;
                for(let i = 0; i < segment.numberOfRows; i++) {
                    for(let j = 0; j < segment.numberOfColumns; j++) {
                        $("#segment" + segment.type).append("<input id='seat" + segment.seats[seatIndex].id + "' class='seat' type='checkbox' onclick='reserveSeat(" + segment.seats[seatIndex].id + ")'>");
                        seatIndex++;
                    }
                    $("#segment" + segment.type).append("<br>");
                }
            }

            function hideAllHallSegments() {
                $(".segment").addClass("invisible");
            }

            function enableAllSeats() {
                $(".seat").prop("disabled", false);
            }

            function hideSegmentContainer() {
                $(".segments").addClass("invisible");
            }

            function showSegmentContainer() {
                $(".segments").removeClass("invisible");
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

    <body onload="init()">
        <h3>Kreiranje brze rezervacije za projekciju ${ projection.name }</h3>

        <p>Izaberite halu u kojoj se projekcija prikazuje:</p>
        <select id="hallSelect">
            <option value="-1">Izaberi halu...</option>
            <c:forEach var="hall" items="${ projection.halls }">
                <option value="${ hall.id }">${ hall.name }</option>
            </c:forEach>
        </select>

        <p>Izaberite termin u hali:</p>
        <select id="periodSelect">

        </select>

         <div class="segments  invisible">
            <div class="segment invisible" id="segmentLEFT_BALCONY"><p>Levi balkon</p></div>
            <div class="middle-hall">
                <div class="segment invisible" id="segmentVIP"><p>VIP</p></div>
                <div class="segment invisible" id="segmentPARTER"><p>Parter</p></div>
            </div>
            <div class="segment invisible" id="segmentRIGHT_BALCONY"><p>Desni balkon</p></div>
        </div>
    </body>

</html>