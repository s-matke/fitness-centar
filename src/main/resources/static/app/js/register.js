
function visibility() {
    let role = $("#role").val();

    if ($("#role").val() == "Trener") {
        document.getElementById("centar").disabled = false;
    } else {
        document.getElementById("centar").disabled = true;
        document.getElementById("centar").value = "-1";
    }
}

$(document).ready(function() {
    // pribavljanje svih FC-a
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/centar/opcije",
        dataType: "json",
        success: function(response) {
            for (let fc of response) {
                let option = "<option value=" + fc.id + ">" + fc.naziv + "</otion>";
                $('#centar').append(option);
            }
        },
        error: function(error) {
            alert("Doslo je do greske prilikom pribavljanja informacija sa servera.")
            window.location.href = "../../index.html";
        }
    });
});

// Registracija
$(document).on("submit", "#addKorisnikForm", function(event) {
    event.preventDefault();

    let userName = $("#userName").val();
    let lozinka = $("#lozinka").val();
    let ime = $("#ime").val();
    let prezime = $("#prezime").val();
    let email = $("#email").val();
    let telefon = $("#telefon").val();
    let birthday = $("#birthday").val();
    let role = $("#role").val();
    let fitnessCentar_id = document.getElementById('centar').value;

    if (fitnessCentar_id == "-1" && role == "Trener") {
        alert("Niste uneli validan fitness centar");
        return;
    }

    let newKorisnik = {
        userName,
        lozinka,
        ime,
        prezime,
        email,
        telefon,
        date: birthday,
        role,
        fitnessCentar_id
    }

    if (role == "Clan") {
        delete newKorisnik.fitnessCentar_id;
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/" + role.toLowerCase() + "/register",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newKorisnik),
        success: function(response) {
            console.log(response);

            if (role == "Clan") {
                alert("Uspesna registracija!\nDobrodosli " + ime + " " + prezime);
            }
            else {
                alert("Uspesna registracija!\nVas zahtev je prosledjen na pregled.")
            }
            window.location.href = "../../index.html";
        },
        error: function(error) {
            console.log(error);
            alert("Greska");
        }
    });
});