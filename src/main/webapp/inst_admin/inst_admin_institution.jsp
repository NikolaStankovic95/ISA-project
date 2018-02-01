<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script type="application/javascript">
            function chooseInstitution() {
                var institutionId = $("#institutionSelect").val();
                window.location.href = "/inst_admin/institution/" + institutionId;
            }
        </script>

    </head>

    <body>
        <h4>Osnovne informacije:</h4>
        <table>
            <tr>
                <td>Ime: </td>
                <td><input type="text" value="${ institution.name }"></td>
            </tr>
            <tr>
                <td>Opis: </td>
                <td><input type="text" value="${ institution.description }"></td>
            </tr>
            <tr>
                <td>Adresa: </td>
                <td><input type="text" value="${ institution.address }"></td>
            </tr>
        </table>
        <input type="button" value="Sacuvaj">
        <h4>Repertoar:</h4>
    </body>

</html>