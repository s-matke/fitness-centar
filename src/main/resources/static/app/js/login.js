$(document).on("submit", "#loginForm", function(event) {
    event.preventDefault();

    let email = $("#email").val().toLowerCase();
    let password = $("#password").val();

    let loginInfo = {
        email,
        lozinka: password
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/korisnik/login",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(loginInfo),
        success: function(data) {
            console.log(data);
            alert("Uspesno ste se ulogovali");

            // TODO - logged user
            // Napraviti da sessionStorage ili localStorage cuva ID a JAVA vrsi vracanje
            // ostalih informacija vezanih za korisnik-a sa datim ID-om
            // || auth tokens
            sessionStorage.setItem('ime', data['ime']);
            sessionStorage.setItem('prezime', data['prezime']);
            sessionStorage.setItem('role', data['role']);
            sessionStorage.setItem('id', data['id']);

            var ulogaKorisnika = sessionStorage.getItem('role');

            console.log("Uloga korisnika: " + ulogaKorisnika);
            alert("Dobrodosli: " + sessionStorage.getItem('ime') + " " + sessionStorage.getItem('prezime'));

            window.location.href = "../../index.html";
        },
        error: function(data) {
            console.log(data);
            alert("Pogresna email/lozinka");
        }  
    });
});