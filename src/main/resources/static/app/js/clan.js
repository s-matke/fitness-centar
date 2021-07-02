$(document).ready(function() {
    let clan_id = sessionStorage.getItem('id');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clan/termini",
        data: {clan_id},
        dataType: "json",
        success: function(response) {
            console.log("Successfully retrieved data");
            for (let termin of response) {
                let row = "<tr>";
                row += "<td>" + termin.naziv + "</td>";
                row += "<td>" + termin.opis + "</td>";
                row += "<td>" + termin.cena + "</td>";
                row += "<td>" + termin.vreme + "</td>";
                row += "<td>" + termin.datum + "</td>";
                row += "<td>" + termin.ime_prezime + "</td>";
                let boxes = "<input type='radio' name='odjava' value=" + termin.id + "/>";
                row += "<td>" + boxes + "</td>";
                row += "</tr>";

                $('#content').append(row);
            }
        },
        error: function(error) {
            console.log("ERROR");
            console.log(error);
            alert("error");
        }
    });
});

