<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="application/javascript">
            function editInstitution() {
                var name = $("#nameInput").val().trim();
                var address = $("#addressInput").val().trim();
                var description = $("#descriptionInput").val().trim();
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

    </head>

    <body>
        <h4>Osnovne informacije:</h4>
        <table>
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
        <h4>Repertoar:</h4>
        <a href="/inst_admin/repertoire/${ institution.repertoire.id }"><p>${ institution.repertoire.name }</p></a>
        <h4>Sale:</h4>
        <ul>
            <c:forEach var="hall" items="${ institution.halls }">
                <li><a href="/inst_admin/hall/${ hall.id }">${ hall.name }</a></li>
            </c:forEach>
        </ul>
        <a href="/inst_admin/institution/${ institution.id }/create_hall">Dodaj salu</a>
    </body>

</html>