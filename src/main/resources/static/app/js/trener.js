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


// Lista treninga
$(document).ready(function() {
    let id = sessionStorage.getItem('id');

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trener/trening/lista",
        dataType: "json",
        data: {id},
        success: function(response) {
            console.log("Success:\n" + response);
            for (let trening of response) {
                let row = "<tr>";
                row += "<td>" + trening.naziv + "</td>";
                row += "<td>" + trening.opis + "</td>";
                row += "<td>" + trening.tip_treninga + "</td>";
                row += "<td>" + trening.trajanje + "</td>";
                let btn = "<button class='btnAddTermin' data-id=" + trening.id + ">Add Termin</button>";
                row += "<td> " + btn + "</td>"
                row += "</tr>";

                $('#content').append(row);
            }
        }, 
        error: function(error) {
            console.log("Error:\n" + error);
            alert("Greska");
        }
    }); 
});

$(document).on('click', '.btnAddTermin', function() {
    var treningDiv = $("#allTreninzi");
    treningDiv.hide();
    var terminDiv = $('#divTermin');
    terminDiv.show();

    let trening_id = this.dataset.id;

    console.log("ID: " + trening_id);
    let text = "<input id='id' type='number' disabled='disabled' value=" + trening_id + " />";
    $('#kontent').append(text);
});

$(document).on('submit', '#addTermin', function(event) {
    event.preventDefault();

    let treningDiv = $("#allTreninzi");
    let terminDiv = $('#divTermin');

    let cena = $("#cena").val();
    let datum = $("#datum").val();
    let vreme = $("#vreme").val();
    let trening_id = parseInt($("#id").val());
    console.log("CENA: " + cena + "\nDatum: " + datum + "\nVreme: " + vreme + "\nID: " + trening_id);
    let dejt = datum + " " + vreme;
    let epoha = Date.parse(dejt) / 1000;
    
    let podaci = {
        epoha,
        cena,
        trening_id
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/trener/termin/dodaj",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(podaci),
        success: function(response) {
            console.log("Success:\n" + response);
            alert("Uspesno dodat termin");
            // Resetuju se vrednosti
            $('#id').remove();
            $("#cena").val('');
            $("#datum").val('');
            $("#vreme").val('');
            terminDiv.hide();
            treningDiv.show();
        },
        error: function(error) {
            console.log("ERROR:\n" + error);
            alert("GRESKA");
        }
        
    })
});

