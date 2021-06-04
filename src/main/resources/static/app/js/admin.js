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
})