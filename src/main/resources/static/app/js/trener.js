$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trener/treneri",
        dataType: "json",
        success: function(response) {
            console.log("Success:\n", response);
            var tmp = response;
            for (let trener of response) {
                let row = "<tr>";
                row += "<td>" + trener.id + "</td>";
                row += "<td>" + trener.ime + "</td>";
                row += "<td>" + trener.prezime + "</td>";
                row += "<td>" + trener.status + "</td>";
                //let input = "<input type='checkbox' class='dejt' data-id=" + trener.id + "/>";
                //let input = "<button class='btnSend' data-id=" + trener.id + ">Accept</button>";
                let boxes = "<input type='checkbox' name='checkboxes' class='dejt' value=" + trener.id + "/>";
                //row += "<td>" + input + "</td>";
                row += "<td>" + boxes + "</td>";
                row += "</tr>";
                
                $('#treneri').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR:\n", response);
        }
    });
});

// Dodavanje novog treninga
$(document).on("submit", "#addTrening", function(event) {
    event.preventDefault();
    console.log("Pravim trening");
    // preuzimanje vrednosti
    let naziv = $("#naziv").val();
    let opis = $("#opis").val();
    let tip_treninga = $("#tip_treninga").val();
    let trajanje = $("#trajanje").val();
    let id = sessionStorage.getItem('id');

    let newTrening = {
        id,
        naziv,
        opis,
        tip_treninga,
        trajanje
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/trening/dodaj",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTrening),
        success: function(response) {
            console.log(response);
            alert("Uspesno dodat novi trening");
            window.location.href = "../../index.html";
        },
        error: function(error) {
            console.log(error);
            alert("Error");
        }
    });
});