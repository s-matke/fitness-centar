// Lista termina 
$(document).ready(function() {
    let id = sessionStorage.getItem('id');  // id trenera

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/termin/lista/" + id,
        dataType: "json",
        success: function(response) {
            console.log("Success:\n" + response);
            for (let termin of response) {
                let row = "<tr>";
                row += "<td>" + termin.naziv + "</td>";
                row += "<td>" + termin.trajanje + "</td>";
                row += "<td>" + termin.cena + "</td>";
                row += "<td>" + termin.vreme + "</td>";
                row += "<td>" + termin.datum + "</td>";
                row += "<td>" + termin.oznaka + "</td>";
                let boxes = "<input type='radio' name='odjava' value='" + termin.id + "'/>";
                row += "<td>" + boxes + "</td>";
                let btn = "<button class='btnEditTermin' data-id='" + termin.id + "' data-cena='" + termin.cena + "' data-sala_id='" + termin.sala_id + "' data-oznaka='" + termin.oznaka + "'>Edit</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";

                $('#content').append(row);
            }
        }, 
        error: function(error) {
            console.log("Error:\n" + error);
            alert("Greska");
        }
    }); 
});


$(document).on('click', '.btnEditTermin', function(event) {

    event.preventDefault(); // da nam se ne bi reloadala stranica..
    //console.log("Usao u deo gde cuvam info");
    var tabela = $(".termin-block")
    var changeForm = $(".change-block")
    var dugme = $(".back-btn");

    tabela.hide();
    changeForm.show();
    dugme.show();

    // Vrednosti
    let id = this.dataset.id;
    let cena = this.dataset.cena;
    let sala_id = this.dataset.sala_id;
    let oznaka = this.dataset.oznaka;

    let id_input = "<input id='id' type='number' disabled value='" + id + "' />"; 
    let cena_input = "<input id='cena' type='number' value='"+cena+"' />";
    let datum_input = "<input id='datum' type='date'/>";
    let vreme_input = "<input id='vreme' type='time'/>";
    let sala_input = "<option selected value='" + sala_id + "'>" + oznaka + "</option>";

       

    console.log("APPENDUJEM");
    $('#input-content').append(id_input); 
    $('#input-content').append(cena_input);
    $('#input-content').append(datum_input);
    $('#input-content').append(vreme_input);
    $('#sala-select').append(sala_input);

    id = sessionStorage.getItem('id');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/sala/lista/" + id,
        dataType: "json",
        success: function(response) {
            for (let sale of response) {
                if (sale.id == sala_id) { continue; }
                let option = "<option value=" + sale.id + ">" + sale.oznaka + "</option>";
                
                $('#sala-select').append(option);
            }
        },
        error: function(error) {
            alert("Doslo je do greske prilikom pribavljanja sala");
        }
    });


});

function changeTermin() {
    $(document).on("submit", "#changeTermin", function(event) {
        event.preventDefault();

        let id = $("#id").val();
        let cena = $("#cena").val();
        let datum = $("#datum").val();
        let vreme = $("#vreme").val();
        let sala_id = document.getElementById('sala-select').value;

        //let trajanje = $("#trajanje").val();
        //let centar_id = $("#centar_id").val();
        // let centar_id = document.getElementById('centar-select').value;
        let uid = sessionStorage.getItem('id');

        console.log("DATUM:" + datum + "\nTIME: " + vreme);
        let podaci;
        if (datum == 0 || vreme == 0) {
            podaci = {
                cena,
                trening_id:sala_id
            }
        }
        else {
            let dejt = datum + " " + vreme;
            let pocetak = Date.parse(dejt) / 1000;
            podaci = {
                epoha:pocetak,
                cena,
                trening_id:sala_id
            }
        }

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/termin/izmeni/" + id,
            contentType: "application/json",
            data: JSON.stringify(podaci),
            success: function(response) {
                console.log("Success:\n" + response);
                alert("Uspesno izmenjen termin");
                window.location.reload();
            },
            error: function(error) {
                console.log("ERROR:\n" + error);
                if (error.status == 400) {
                    // alert("Greska!");
                    alert("Ne ispravan datum");
                    return;
                }
            }
        });
    });
}

function deleteTermin() {
    $(document).on("submit", "#termini", function(event) {
        event.preventDefault();
        
        let id;
        $("input:radio[name='odjava']:checked").each(function() {
            id = parseInt($(this).val());
        });
        if (id == null) {alert("Morate izabrati salu koju zelite obrisati"); return;}
        // if(!check(id)) {return;}

        // alert("Ipak je prosao da brise, id: " + id);
        
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/termin/obrisi/" + id,
            // dataType: "json",
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