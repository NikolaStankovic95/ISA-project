<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script type="application/javascript">
            var segments = [
                { type: 0, numberOfRows: 0, numberOfColumns: 0, isClosed: false },
                { type: 1, numberOfRows: 0, numberOfColumns: 0, isClosed: false },
                { type: 2, numberOfRows: 0, numberOfColumns: 0, isClosed: false },
                { type: 3, numberOfRows: 0, numberOfColumns: 0, isClosed: false }
            ];


            function init() {
                initListeners()

            }

            function initListeners() {
                $("[type = checkbox]").change(function() {
                    if(this.checked) {
                        $("#segment" + this.value).removeClass("invisible");
                    }
                    else {
                        $("#segment" + this.value).addClass("invisible");
                    }
                });

                $(".segment").click(function() {
                    let id = this.id[this.id.length - 1];
                    $("#segmentNameFormInput").text($(this).text());
                    $("#numberOfRowsFormInput").val(segments[id].numberOfRows);
                    $("#numberOfColumnsFormInput").val(segments[id].numberOfColumns);
                    $("#isClosedFormInput").prop("checked", segments[id].isClosed);
                    $("#numberOfRowsFormInput, #numberOfColumnsFormInput, #isClosedFormInput").attr("name", id);
                });

                $("#numberOfRowsFormInput").change(function() {
                    segments[this.name].numberOfRows = this.value;
                    drawSeats(this.name);
                });

                $("#numberOfColumnsFormInput").change(function() {
                    segments[this.name].numberOfColumns = this.value;
                    drawSeats(this.name);
                });

               /* $("#isClosedFormInput").change(function() {
                   segments[this.name].isClosed = this.checked;
                   if(this.checked) {
                       $("#segment" + this.name).addClass("closed");
                   } else {
                       $("#segment" + this.name).removeClass("closed");
                   }
                });*/
            }

            function drawSeats(segmentId) {
                let segment = segments[segmentId];
                if(segment.numberOfColumns === 0 || segment.numberOfRows === 0) {
                     return;
                }
                $("#segment" + segmentId + " :not(:first-child)").remove();
                for(let i = 0; i < segment.numberOfRows; i++) {
                    for(let j = 0; j < segment.numberOfColumns; j++) {
                        $("#segment" + segmentId).append("<input class='seat' type='checkbox'>");
                    }
                    $("#segment" + segmentId).append("<br>");
                }
                $(".seat").on("click", false);
            }

            function saveHall() {
                let name = $("#nameInput").val().trim();

                let activeSegments = [];

                $("#chooseSegment [type = checkbox]").each(function() {
                    if(this.checked) {
                        activeSegments.push(segments[this.value])
                    }
                });

                $.ajax({
                    method: 'POST',
                    url: '/institution/${ institution.id }/hall',
                    contentType : 'application/json',
                    dataType: 'json',
                    data: JSON.stringify({
                        name: name,
                        hallSegments: activeSegments
                    }),
                    success: function(data) {
                        console.log(data);
                        alert("Uspesno dodata hala.");
                    },
                    error: function(data) {
                        console.log(data);
                        alert("Neuspesno dodavanje.");
                    }
                });
            }
        </script>

        <style>
            .segments {
                display: flex;
                flex-direction: row;
            }

            .closed {
                background: gray;
            }

            .middle-hall {
                display: flex;
                flex-direction: column;
                border: none !important;
            }

            .segments div {
                border: 1px solid black;
            }

            .invisible {
                display: none;
            }
        </style>

    </head>

    <body onload="init()" style="margin: 15px;">
        <c:import url="../_navbar.jsp"></c:import>

        <h3>Dodaj halu za ${ institution.name }</h3>
        <h4>Osnovne informacije:</h4>
        <table>
            <tr>
                <td>Ime: </td>
                <td><input id="nameInput" type="text" value="${ hall.name }"></td>
            </tr>
        </table>

        <h4>Segmenti:</h4>

        <div id="chooseSegment">
            <input type="checkbox" id="vipCheckbox" name="vipCheckbox" value="0">
            <label for="vipCheckbox">VIP</label>

            <input type="checkbox" id="leftBalconyCheckbox" name="leftBalconyCheckbox" value="1">
            <label for="leftBalconyCheckbox">Levi balkon</label>

            <input type="checkbox" id="rightBalconyCheckbox" name="rightBalconyCheckbox" value="2">
            <label for="rightBalconyCheckbox">Desni balkon</label>

            <input type="checkbox" id="parterCheckbox" name="parterCheckbox" value="3">
            <label for="parterCheckbox">Parter</label>
        </div>

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
                <td><input id="numberOfRowsFormInput" type="text"></td>
            </tr>

            <tr>
                <td>Broj kolona:</td>
                <td><input id="numberOfColumnsFormInput" type="text"></td>
            </tr>

        </table>
        <input type="button" value="Sacuvaj" onclick="saveHall()">
    </body>

</html>