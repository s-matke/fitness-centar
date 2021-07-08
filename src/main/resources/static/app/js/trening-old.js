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
            alert("Greska - BACA PRVA");
        }
    });
});

$(document).on("submit", "#searchForm", function(event) {
    event.preventDefault();
    
    let contentContainer = $("#content");
    contentContainer.empty();

    let keyword = $("#filter").val().toLowerCase();

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trening/lista",
        data: {keyword},
        //dataType: "json",
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
            console.log("Error:\n", response);
            alert("Greska - DRUGA");
        }
    });
});

$(document).on("submit", "#searchByCategory", function(event) {
    event.preventDefault();

    let contentContainer = $("#content");
    contentContainer.empty();
    let parametar;
    let naziv = $("#naziv").val();
    let opis = $("#opis").val();
    let tip = $("#tip").val();
    let trajanje = $("#trajanje").val();

    console.log("naziv: " + naziv + "\ntrajanje: " + trajanje);
    // if (naziv != null) {
    //     parametar = naziv;
    //     alert("Naziv: " + naziv + "\nParam: " + parametar);
    // } else if (trajanje != null) {
    //     parametar = trajanje;
    //     alert("Trajanje: " + trajanje + "\nParam: " + parametar);
    // }

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trening/lista/pretraga",
        data: {naziv, opis, tip, trajanje},
        //dataType: "json",
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
            console.log("Error:\n", response);
            alert("Greska - DRUGA");
        }
    }); 
});

function sortTable(n) {
    var table = document.getElementById("treninzi");
    var rows, i, x, y, count = 0;
    var switching = true;

    var direction = "ascending";

    while (switching) {
        switching = false;
        var rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            var Switch = false;

            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i+1].getElementsByTagName("TD")[n];

            if (direction == "ascending") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()){
                    Switch = true;
                    break;
                }
            } else if (direction == "descending") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    Switch = true;
                    break;
                }
            }
        }
        if (Switch) {
            rows[i].parentNode.insertBefore(rows[i+1], rows[i]);
            switching = true;

            count++;
        } else {
            if (count == 0 && direction == "ascending") {
                direction = "descending";
                switching = true;
            }
        }
    }
}
