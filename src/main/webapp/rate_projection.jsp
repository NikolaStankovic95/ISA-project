<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <script>
            function rate() {
                rateProjection();
            }
            
            function rateProjection() {
                let rating = $("#projectionRatingSelect").val();
                console.log(rating);

                $.ajax({
                    method: 'POST',
                    url: '/projection/${ projection.id }/rate',
                    contentType : 'application/json',
                    data: JSON.stringify({
                        rating: rating
                    }),
                    success: function(data) {
                        rateInstitution();
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if(jqXHR.status == 400) {
                            alert("Vec ste jednom ocenili ovu projekciju i instituciju.");
                        }
                    }
                });
            }
            
            function rateInstitution() {
                let rating = $("#institutionRatingSelect").val();

                $.ajax({
                    method: 'POST',
                    url: '/institution/${ institution.id }/rate',
                    contentType : 'application/json',
                    data: JSON.stringify({
                        rating: rating
                    }),
                    success: function(data) {
                        console.log(data);
                        alert("Uspesno ocenjena institucija.");
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        if(jqXHR.status == 400) {
                            alert("Vec ste jednom ocenili ovu projekciju i instituciju.");
                        }
                    }
                });
            }
        </script>
    </head>

    <body>
        <table>
            <tr>
                <td>Institucija:</td>
                <td>${ institution.name }</td>
            </tr>
            <tr>
                <td>Projekcija:</td>
                <td>${ projection.name }</td>
            </tr>
            <tr>
                <td>Ocena institucije:</td>
                <td>
                    <select id="institutionRatingSelect">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Ocena projekcije:</td>
                <td>
                    <select id="projectionRatingSelect">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="button" value="Oceni" onclick="rate()">
    </body>

</html>