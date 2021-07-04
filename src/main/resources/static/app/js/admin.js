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

$(document).on("submit", "#allTreneri", function(event) {
    
    event.preventDefault();

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

// Dodavanje fitness centra

$(document).on("submit", "#addFitnessCentar", function(event) {
    if (!checkPrivileges()) return;

    event.preventDefault();

    // preuzimanje vrednost
    let naziv = $("#naziv").val();
    let adresa = $("#adresa").val();
    let telefon = $("#telefon").val();
    let email = $("#email").val();

    let newCentar = {
        naziv,
        adresa,
        email,
        telefon
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
            window.location.href = "../../index.html";
        },
        error: function(error) {
            alert(error);
        }
    });
});

$(document).on("submit", "#centri", function(event) {

    
    event.preventDefault();
    
    let id;
    $("input:radio[name='odjava']:checked").each(function() {
        id = parseInt($(this).val());
    });
    if (id == null) {alert("Morate izabrati centar koji zelite obrisati"); return;}
    if(!check(id)) {return;}

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
