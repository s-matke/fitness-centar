$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trening/lista",
        dataType: "json",
        success: function(response) {
            console.log("Success:\n", response);
            for (let trening of response) {
                let row = "<tr>";
                row += "<td>" + trening.naziv + "</td>";
                row += "<td>" + trening.opis + "</td>";
                row += "<td>" + trening.tip_treninga + "</td>";
                row += "<td>" + trening.trajanje + "</td>";
                row += "</tr>";
                
                $('#content').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR:\n", response);
            alert("Greska");
        }
    });
});