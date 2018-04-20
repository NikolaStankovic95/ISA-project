<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>

        <script type="application/javascript">
            let weeklyReservations = [];
            let dailyReservations = [];
            let monthlyReservations = [];

            let weeklyIncomes = [];
            let dailyIncomes = [];
            let monthlyIncomes = [];
 
            function init() {
                initGraphData();
                initListeners();
            }

            function initListeners() {
                $("#visitedGraphPeriodSelect").change(function() {
                    if(this.value == -1) {
                        $("#visitedChartContainer").empty();
                        return;
                    }

                    if(this.value == 0) {
                        drawVisitedGraph(dailyReservations);
                    } else if (this.value == 1) {
                        drawVisitedGraph(weeklyReservations);
                    }
                    else {
                        drawVisitedGraph(monthlyReservations);
                    }
                });

                $("#incomeGraphPeriodSelect").change(function() {
                    if(this.value == -1) {
                        $("#incomeChartContainer").empty();
                        return;
                    }

                    if(this.value == 0) {
                        drawIncomeGraph(dailyIncomes);
                    } else if (this.value == 1) {
                        drawIncomeGraph(weeklyIncomes);
                    }
                    else {
                        drawIncomeGraph(monthlyIncomes);
                    }
                });
            }

            function makeGraphData(array) {
                let result = { };
                for(var i = 0; i < array.length; ++i) {
                    if(!result[array[i]])
                        result[array[i]] = 0;
                    ++result[array[i]];
                 }
                 let resultArray = []
                 Object.keys(result).forEach(key => {
                     resultArray.push({ x_value: key, y_value: result[key] })
                 });
                return resultArray;
            }

            function insertIncomesData(array, date, value) {
                let index = array.findIndex(income => income.x_value == date);
                if(index == -1) {
                    array.push({ x_value: date, y_value: value});
                } else {
                    array[index].y_value += value;
                }
            }

            function initGraphData() {
                <c:forEach var="reservation" items = "${ dailyReservations }">
                    dailyReservations.push(new Date('${ reservation.period.date }').toLocaleTimeString());
                    insertIncomesData(dailyIncomes, new Date('${ reservation.period.date }').toLocaleTimeString(), ${ reservation.getDiscountedPrice() });
                </c:forEach>

                <c:forEach var="reservation" items = "${ monthlyReservations }">
                    monthlyReservations.push(new Date('${ reservation.period.date }').getDate() + '/' + (new Date('${ reservation.period.date }').getMonth() + 1));
                    insertIncomesData(monthlyIncomes, new Date('${ reservation.period.date }').getDate() + '/' + (new Date('${ reservation.period.date }').getMonth() + 1), ${ reservation.getDiscountedPrice() });
                </c:forEach>


                <c:forEach var="reservation" items = "${ weeklyReservations }">
                    weeklyReservations.push(new Date('${ reservation.period.date }').getDate() + '/' + (new Date('${ reservation.period.date }').getMonth() + 1));
                    insertIncomesData(weeklyIncomes, new Date('${ reservation.period.date }').getDate() + '/' + (new Date('${ reservation.period.date }').getMonth() + 1), ${ reservation.getDiscountedPrice() });
                </c:forEach>

                dailyReservations = makeGraphData(dailyReservations);
                weeklyReservations = makeGraphData(weeklyReservations);
                monthlyReservations = makeGraphData(monthlyReservations);

                console.log(dailyReservations)
                console.log(weeklyReservations);
            
            }

             function drawVisitedGraph(data) {
                $("#visitedChartContainer").empty();
                if(data.length == 0) return;
                new Morris.Bar({
                    // ID of the element in which to draw the chart.
                    element: 'visitedChartContainer',
                    // Chart data records -- each entry in this array corresponds to a point on
                    // the chart.
                    data: data,
                    // The name of the data record attribute that contains x-values.
                    xkey: 'x_value',
                    // A list of names of data record attributes that contain y-values.
                    ykeys: ['y_value'],
                    // Labels for the ykeys -- will be displayed when you hover over the
                    // chart.
                    labels: ['Broj poseta'],
                });
            };

            function drawIncomeGraph(data) {
                $("#incomeChartContainer").empty();
                if(data.length == 0) return;
                new Morris.Bar({
                    // ID of the element in which to draw the chart.
                    element: 'incomeChartContainer',
                    // Chart data records -- each entry in this array corresponds to a point on
                    // the chart.
                    data: data,
                    // The name of the data record attribute that contains x-values.
                    xkey: 'x_value',
                    // A list of names of data record attributes that contain y-values.
                    ykeys: ['y_value'],
                    // Labels for the ykeys -- will be displayed when you hover over the
                    // chart.
                    labels: ['Prihod(DIN)'],
                });
            };

            function editInstitution() {
                let name = $("#nameInput").val().trim();
                let address = $("#addressInput").val().trim();
                let description = $("#descriptionInput").val().trim();
                $.ajax({
                    method: 'PATCH',
                    url: '/institution/${ institution.id }',
                    contentType : 'application/json',
                    data: JSON.stringify({ name: name, address: address, description: description }),
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
            .page-layout {
                margin: 35px;
            }

            .repertoire-container {
                margin-top: 30px;
                margin-bottom: 30px;
            }

            .halls-container {
                margin-bottom: 30px;
            }
        </style>
    </head>

    <body onload="init()" style="margin: 15px;">
        <c:import url="../_navbar.jsp"></c:import>

        <div class="page-layout">

            <h4>Osnovne informacije:</h4>
            <table class="table">
                <tr>
                    <td>Ime: </td>
                    <td><input id="nameInput" type="text" value="${ institution.name }"></td>
                </tr>
                <tr>
                    <td>Opis: </td>
                    <td><input id="descriptionInput" type="text" value="${ institution.description }"></td>
                </tr>
                <tr>
                    <td>Adresa: </td>
                    <td><input id="addressInput" type="text" value="${ institution.address }"></td>
                </tr>
            </table>
            <input type="button" value="Sacuvaj" onclick="editInstitution()">

            <div class="repertoire-container">
                <h4>Repertoar:</h4>
                <c:if test="${ institution.repertoire != null }">
                    <a href="/inst_admin/institution/${ institution.id }/repertoire/${ institution.repertoire.id }"><p>${ institution.repertoire.name }</p></a>
                </c:if>
                <c:if test="${ institution.repertoire == null }">
                    <a href="/inst_admin/institution/${ institution.id }/create_repertoire">Dodaj repertoar</a>
                </c:if>
            </div>

            <div class="halls-container">
                <h4>Sale:</h4>
                <ul>
                    <c:forEach var="hall" items="${ institution.halls }">
                        <li><a href="/inst_admin/hall/${ hall.id }">${ hall.name }</a></li>
                    </c:forEach>
                </ul>
                <a href="/inst_admin/institution/${ institution.id }/create_hall">Dodaj salu</a>
            </div>
            <h4>Posecenost</h4>

            <select id="visitedGraphPeriodSelect">
                <option value="-1">Izaberite period...</option>
                <option value="0">Danas</option>
                <option value="1">Proteklih nedelju dana</option>
                <option value="2">Proteklih mesec dana</option>
            </select>


            <div id="visitedChartContainer" style="height: 250px;"></div>

            <h4>Prihodi</h4>

            <select id="incomeGraphPeriodSelect">
                <option value="-1">Izaberite period...</option>
                <option value="0">Danas</option>
                <option value="1">Proteklih nedelju dana</option>
                <option value="2">Proteklih mesec dana</option>
            </select>

            <div id="incomeChartContainer" style="height: 250px;"></div>
        </div>
    </body>

</html>