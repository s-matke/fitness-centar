$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/korisnik/korisnici",
        dataType: "json",
        success: function(response) {
            console.log("Success:\n", response);
            for (let korisnik of response) {
                let row = "<tr>";
                row += "<td>" + korisnik.ime + "</td>";
                row += "<td>" + korisnik.prezime + "</td>";
                row += "<td>" + korisnik.status + "</td>";
                row += "</tr>";

                $('#korisnici').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR:\n", response);
            alert("Doslo je do greske");
        }
    });
});