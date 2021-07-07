$(document).ready(function() {
    let id = sessionStorage.getItem('id');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/termin/lista",
        dataType: "json",
        data: {id},
        success: function(response) {
            console.log("Success:\n");
            console.log(response);
            for (let termin of response) {
                let row = "<tr>";
                row += "<td>" + termin.naziv + "</td>";
                row += "<td>" + termin.opis + "</td>";
                row += "<td>" + termin.tip + "</td>";
                row += "<td>" + termin.trajanje + "</td>";
                row += "<td>" + termin.vreme + "</td>";
                row += "<td>" + termin.datum + "</td>";
                row += "<td>" + termin.cena + "</td>";
                row += "<td>" + termin.ime_prezime + "</td>";
                row += "<td>" + termin.centar + "<br/>Sala: " + termin.oznaka + "</td>";
                console.log("Termin.id = " + termin.id);
                let boxes;
                if (id == null) {
                    boxes = "<input type='radio' name='radiobox' class='prijavi' disabled value=" + termin.id + "/>";
                }
                else {
                    boxes = "<input type='radio' name='radiobox' class='prijavi' value=" + termin.id + "/>";
                }
                row += "<td>" + boxes + "</td>";
                row += "</tr>";

                $('#content').append(row);
            }
        },
        error: function(response) {
            let terminiDiv = $('#allTermini');
            let praznoDiv = $('#prazno');
            terminiDiv.hide();
            praznoDiv.show();
            console.log("ERROR: ", response);
        }
    });
});

$(document).on("submit", "#allTermini", function(event) {
    event.preventDefault();
    if (!checkLogin()) {
        alert("Morate biti ulogovani da biste se prijavili na termin!");         
        return; 
    }
    let idKorisnika = sessionStorage.getItem('id');
    var choice;
    //var choice = $("input:radio[name=radiobox]:checked").val();   // koji radio je selektovan, sadrzi id termina
    //var choice = document.getElementsByClassName("prijavi").value;
    // var array1 = [];
    $("input:radio[name=radiobox]:checked").each(function() {
        choice = parseInt($(this).val());
    });
    if (choice == undefined) {
        alert("Nije izabran nijedan termin.")
        return;
    }
    console.log("Id korisnika: " + idKorisnika);
    console.log("Id termina: " + choice);

    let novaPrijava = {
        clan_id: idKorisnika,
        termin_id: choice
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/termin/prijava",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(novaPrijava),
        success: function(response) {
            console.log(response);

            alert("Uspesna prijava");
            window.location.reload();
        },
        error: function(error) {
            console.log(error);
            alert("Request status: " + error.status + " Status Text: " + error.statusText + " " + error.responseText);
        }
    });
});

function checkLogin() {
    if (sessionStorage.getItem('id') == null) return false;
    return true;
}
