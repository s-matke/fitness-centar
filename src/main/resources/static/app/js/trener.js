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

    // ajax grab sale
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/sala/lista",
        dataType: "json",
        success: function(response) {
            for (let sale of response) {
                let option = "<option value=" + sale.id + ">" + sale.oznaka + "</option>";
                
                $('#sala').append(option);
            }
        }
    });
});

$(document).on('submit', '#addTermin', function(event) {
    event.preventDefault();

    let treningDiv = $("#allTreninzi");
    let terminDiv = $('#divTermin');

    let cena = $("#cena").val();
    let datum = $("#datum").val();
    let vreme = $("#vreme").val();
    let trening_id = parseInt($("#id").val());
    //let sala_id = $("")
    let sala_id = document.getElementById('sala').value;
    let dejt = datum + " " + vreme;
    let epoha = Date.parse(dejt) / 1000;
    
    let podaci = {
        epoha,
        cena,
        trening_id,
        sala_id
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

