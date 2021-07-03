// Provera prava pristupa
function checkPrivileges() {
    let role = sessionStorage.getItem('role');

    console.log("Uloga:" + role);
    if (sessionStorage.getItem('role') != "Admin") {
        alert("Nemate pravo pristupa!");
        window.location.href = "../../index.html";
    }
}

// izlistavanje trenera
$(document).ready(function () {
    checkPrivileges();

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
    checkPrivileges();

    event.preventDefault();

    // preuzimanje vrednost
    let naziv = $("#naziv").val();
    let adresa = $("#adresa").val();
    let telefon = $("#telefon").val();
    let email = $("#email").val();

    let cid = 1;

    let newCentar = {
        naziv,
        adresa,
        email,
        telefon
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/admin/addCentar/" + cid,
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
