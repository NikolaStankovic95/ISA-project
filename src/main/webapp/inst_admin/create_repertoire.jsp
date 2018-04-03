<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="application/javascript">
        function addRepertoire() {
            var name = $("#nameInput").val().trim();
            $.ajax({
                method: 'POST',
                url: '/institution/${ institution.id }/repertoire',
                contentType : 'application/json',
                data: JSON.stringify({ name: name }),
                success: function(data) {
                    console.log(data);
                    alert("Uspesno kreirano.");
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
        <td>Ime:</td>
        <td><input id="nameInput" type="text"></td>
    </tr>
</table>
<input type="button" value="Sacuvaj" onclick="addRepertoire()">
</body>

</html>