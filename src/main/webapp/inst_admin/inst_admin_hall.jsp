<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script type="application/javascript">
        var segments = [
            { type: 0, numberOfRows: 0, numberOfColumns: 0, isClosed: false },
            { type: 1, numberOfRows: 0, numberOfColumns: 0, isClosed: false },
            { type: 2, numberOfRows: 0, numberOfColumns: 0, isClosed: false },
            { type: 3, numberOfRows: 0, numberOfColumns: 0, isClosed: false }
        ];


        function init() {
            <c:forEach var="segment" items="${ hall.hallSegments }">
                segments[${segment.type.ordinal()}].numberOfRows = ${ segment.numberOfRows };
                segments[${segment.type.ordinal()}].numberOfColumns = ${ segment.numberOfColumns };
            </c:forEach>

            initSegments();

            initListeners()

        }

        function initSegments() {
            segments.forEach(function(segment, i) {
                if(segment.numberOfColumns == 0 || segment.numberOfRows == 0) {
                    $("#segment" + i).addClass("invisible");
                } else {
                    drawSeats(i)
                }
            })
        }

        function initListeners() {

            $(".segment").click(function() {
                let id = this.id[this.id.length - 1];
                $("#segmentNameFormInput").text($(this).text());
                $("#numberOfRowsFormInput").val(segments[id].numberOfRows);
                $("#numberOfColumnsFormInput").val(segments[id].numberOfColumns);
                $("#isClosedFormInput").prop("checked", segments[id].isClosed);
                $("#numberOfRowsFormInput, #numberOfColumnsFormInput, #isClosedFormInput").attr("name", id);
            });

            /*$("#numberOfRowsFormInput").change(function() {
                segments[this.name].numberOfRows = this.value;
                drawSeats(this.name);
            });

            $("#numberOfColumnsFormInput").change(function() {
                segments[this.name].numberOfColumns = this.value;
                drawSeats(this.name);
            });*/

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
                method: 'PATCH',
                url: '/hall/${ hall.id }',
                contentType : 'application/json',
                data: JSON.stringify({
                    name: name,
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
    </style>

</head>

<body onload="init()">
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
    <div class="segment" id="segment1"><p>Levi balkon</p></div>
    <div class="middle-hall">
        <div class="segment" id="segment0"><p>VIP</p></div>
        <div class="segment" id="segment3"><p>Parter</p></div>
    </div>
    <div class="segment" id="segment2"><p>Desni balkon</p></div>
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

</table>

</body>

</html>