$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/korisnik/korisnici",
        dataType: "json",
        success: function(response) {
            console.log("Success:\n", response);
            for (let korisnik of response) {
                let row = "<tr>";
                row += "<td>" + korisnik.ime + "</td>";
                row += "<td>" + korisnik.prezime + "</td>";
                row += "<td>" + korisnik.role + "</td>";
                row += "<td>" + korisnik.status + "</td>";
                row += "</tr>";

                $('#korisnici').append(row);
            }
        },
        error: function(response) {
            console.log("ERROR:\n", response);
            alert("Doslo je do greske");
        }
    });
});

function prikazKorisnika() {
    if (sessionStorage.getItem('ime') === null || sessionStorage.getItem('prezime') === null || sessionStorage.getItem('role') === null) {
        alert("Trenutno niko nije ulogovan");
        console.log("Nema ulogovanog korisnika");
    }
    else {
        let ime = sessionStorage.getItem('ime');
        let prezime = sessionStorage.getItem('prezime');
        let uloga = sessionStorage.getItem('role');
        var korisnikInfo = {
            ime,
            prezime,
            uloga
        }
        alert("Info: " + ime + " " + prezime + "\n" + "Uloga: " + uloga);
        console.log(korisnikInfo);
    }
}