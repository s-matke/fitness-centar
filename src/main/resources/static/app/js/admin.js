// Provera prava pristupa
function checkPrivileges() {
    if (sessionStorage.getItem('role') != "Admin") {
        alert("Nemate pravo pristupa!");
        window.location.replace("../../index.html");
        return false;
    }
    return true;
}

function check(id) {
    let r = confirm("Da li ste sigurni da zelite da obrisete centar s ID-om: " + id + "?");
    return r;
}


// Izmena statusa trenera
function changeT(){
$(document).on("submit", "#allTreneri", function(event) {
    event.preventDefault();
    console.log("Usao u change");

    var array = [];
    $("input:checkbox[name=checkboxes]:checked").each(function() {
        array.push(parseInt($(this).val()));
    });

    //alert("Duzina array-a: " + array.length);
    //console.log("Duzina ar: " + array.length);
    if (array.length > 0)
    {
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/admin/odobri",
            //contentType: "application/json",
            data: JSON.stringify(array),
            contentType: "application/json",
            dataType: "json",
            success: function() {
                if (array.length > 1) {
                    let txt = "Uspesno izmenjeni treneri sa id-om: ";
                    for (let i = 0; i < array.length; i++) {
                        if (i == array.length - 1) {
                            txt += array[i];
                        }else {
                            txt += array[i] + ", ";
                        }
                    }
                    alert(txt);
                }
                else {
                    alert("Uspesno izmenjen trener: " + array);
                }
                window.location.href = "odobriTrenera.html";
            },
            error: function(errMsg) {
                alert(errMsg);
            }
        });
    }
    else {
        alert("Niste selektovali ni jednog trenera");
    }
});
}

// Brisanje trenera
function deleteT() {


$(document).on("submit", "#allTreneri", function(event) {

    event.preventDefault();

    
    //console.log("Usao u delete");
    
    var array = [];
    $("input:checkbox[name=checkboxes]:checked").each(function() {
        array.push(parseInt($(this).val()));
    });
    
    // console.log("Duzina ar: " + array.length);
    // console.log(array);
    // alert("Ispis: " + array.length);

    //alert("Duzina array-a: " + array.length);
    //console.log("Duzina ar: " + array.length);

    if (array.length == 0) {
        alert("Niste izabrali ni jednog trenera");
        return;
    }

    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/trener/obrisi",
        //contentType: "application/json",
        data: JSON.stringify(array),
        contentType: "application/json",
        dataType: "json",
        success: function() {
            alert("Uspesno izbrisani treneri");
            window.location.reload();
        },
        error: function(errMsg) {
            alert("Greska: " + errMsg);
        }
    });
});
}

// Dodavanje novog trenera

// Registracija
function registerT() {
    $(document).on("submit", "#registerForm", function(event) {
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
            url: "http://localhost:8080/api/trener/admin-register",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(newKorisnik),
            success: function(response) {
                console.log(response);

                if (role == "Clan") {
                    alert("Uspesna registracija!\nDobrodosli " + ime + " " + prezime);
                }
                else {
                    alert("Uspesna registracija trenera!")
                }
                window.location.href = "./odobriTrenera.html";
            },
            error: function(error) {
                console.log(error);
                alert("Greska");
            }
        });
    });
}

// Dodavanje fitness centra

$(document).on("submit", "#addFitnessCentar", function(event) {
    event.preventDefault();
    // alert("OUPS");
    if (!checkPrivileges()) return;


    // preuzimanje vrednost
    let naziv = $("#naziv").val();
    let adresa = $("#adresa").val();
    let telefon = $("#telefon").val();
    let email = $("#email").val();

    let newCentar = {
        naziv,
        adresa,
        telefon,
        email
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/admin/addCentar",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newCentar),
        success: function(response) {
            console.log(response);
            alert("Uspesno dodat novi fitness centar (" + response.id + ")\nNaziv: " + response.naziv + "\nAdresa: " + response.adresa);
            window.location.href = "./obrisiCentar.html";
        },
        error: function(error) {
            alert(error);
        }
    });
});

function deleteC() {

    $(document).on("submit", "#centri", function(event) {
        event.preventDefault();
        
        let id;
        $("input:radio[name='odjava']:checked").each(function() {
            id = parseInt($(this).val());
        });
        if (id == null) {alert("Morate izabrati centar koji zelite obrisati"); return;}
        if(!check(id)) {return;}

        // alert("Ipak je prosao da brise, id: " + id);
        
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/centar/obrisi/" + id,
            dataType: "json",
            success: function(response) {
                console.log("SUCCESS:\n" + response);
                window.location.reload();
            },
            error: function(response) {
                console.log("ERROR:\n" + response);
            }
        });
    });
}

// function changeC() {


