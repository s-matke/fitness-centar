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

    let newKorisnik = {
        userName,
        lozinka,
        ime,
        prezime,
        email,
        telefon,
        date: birthday,
        role
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/" + role.toLowerCase() + "/register",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newKorisnik),
        success: function(response) {
            console.log(response);

            if (role.equals("clan")) {
                alert("Uspesna registracija!\n Dobrodosli " + ime + " " + prezime);
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