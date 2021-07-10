// Izlistavanje centara
$(document).ready(function() {
    // if (!checkPrivileges()) return;
    // $("#centriBody").show();
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/centar/lista",
        dataType: "json",
        success: function(response) {
            console.log("SUCCESS:\n" +response);
            for (let centar of response) {
                let row = "<tr>";
                row += "<td>" + centar.id + "</td>";
                row += "<td>" + centar.naziv + "</td>";
                row += "<td>" + centar.adresa + "</td>";
                row += "<td>" + centar.sale + "</td>";
                row += "<td>" + centar.treneri + "</td>";
                let boxes = "<input type='radio' name='odjava' value='" + centar.id + "'/>";
                row += "<td>" + boxes + "</td>";
                let btn = "<button class='btnEditCentar' data-id=" + centar.id + " data-naziv='" + centar.naziv + "' data-adresa='" + centar.adresa + "' data-telefon='" + centar.telefon + "' data-email='" + centar.email + "'>Edit Centar</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";
                
                $("#content").append(row);
            }
        },
        error: function(error) {
            console.log("ERROR:\n" + error);
        }
    });
});

$(document).on('click', '.btnEditCentar', function(event) {

    event.preventDefault(); // da nam se ne bi reloadala stranica..
    //console.log("Usao u deo gde cuvam info");
    var tabela = $(".centar-block")
    var changeForm = $(".change-block")
    var dugme = $(".back-btn");

    tabela.hide();
    changeForm.show();
    dugme.show();

    // Vrednosti
    let id = this.dataset.id;
    let naziv = this.dataset.naziv;
    let adresa = this.dataset.adresa;
    let telefon = this.dataset.telefon;
    let email = this.dataset.email;

    let id_input = "<input id='id' type='number' disabled value='" + id + "' />"; 
    let naziv_input = "<input id='naziv' type='text' value='"+naziv+"' />";
    let adresa_input ="<input id='adresa' type='text' value='"+adresa+"' />";
    let telefon_input ="<input id='telefon' type='text' value='"+telefon+"' />";
    let email_input ="<input id='email' type='email' value='"+email+"' />";

    console.log("APPENDUJEM");
    $('#input-content').append(id_input); 
    $('#input-content').append(naziv_input);
    $('#input-content').append(adresa_input);
    $('#input-content').append(telefon_input);
    $('#input-content').append(email_input);
    console.log("Zavrsio s apendovanjem");
});


function changeC() {
    $(document).on("submit", "#changeTermin", function(event) {
        event.preventDefault();

        let id = $("#id").val();
        let naziv = $("#naziv").val();
        let adresa = $("#adresa").val();
        let telefon = $("#telefon").val();
        let email = $("#email").val();

        console.log("ID: " + id + "\nNaziv: " + naziv + "\nAdresa: " + adresa);
        alert("ID: " + id);

        let podaci = {
            naziv,
            adresa,
            telefon,
            email
        }
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/centar/izmeni/" + id,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(podaci),
            success: function(response) {
                console.log("Success:\n" + response);
                alert("Uspesno izmenjen centar");
                window.location.reload();
            },
            error: function(error) {
                console.log("ERROR:\n" + error);
                alert("Greska!");
            }
        });
    });
}