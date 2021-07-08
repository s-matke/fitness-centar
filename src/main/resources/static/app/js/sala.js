// Izlistavanje sala
$(document).ready(function() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/sala/lista",
        dataType: "json",
        success: function(response) {
            console.log("SUCCESS:\n" +response);
            for (let sala of response) {
                let row = "<tr>";
                row += "<td>" + sala.id + "</td>";
                row += "<td>" + sala.oznaka + "</td>";
                row += "<td>" + sala.kapacitet + "</td>";
                row += "<td>" + sala.centar_id + "</td>";  
                let boxes = "<input type='radio' name='odjava' value='" + sala.id + "'/>";
                row += "<td>" + boxes + "</td>";
                let btn = "<button class='btnEditSala' data-id=" + sala.id + " data-oznaka='" + sala.oznaka + "' data-kapacitet='" + sala.kapacitet + "' data-centar_id='" + sala.centar_id + "' data-naziv='" + sala.naziv + "'>Edit Sala</button>";
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

$(document).on('click', '.btnEditSala', function(event) {

    event.preventDefault(); // da nam se ne bi reloadala stranica..
    //console.log("Usao u deo gde cuvam info");
    var tabela = $(".sala-block")
    var changeForm = $(".change-block")
    var dugme = $(".back-btn");

    tabela.hide();
    changeForm.show();
    dugme.show();

    // Vrednosti
    let id = this.dataset.id;
    let oznaka = this.dataset.oznaka;
    let kapacitet = this.dataset.kapacitet;
    let centar_id = this.dataset.centar_id;
    let naziv = this.dataset.naziv;

    let id_input = "<input id='id' type='number' disabled value='" + id + "' />"; 
    let oznaka_input = "<input id='oznaka' type='text' value='"+oznaka+"' />";
    let kapacitet_input ="<input id='kapacitet' type='number' value='"+kapacitet+"' />";
    let centar_id_input ="<option selected value=" + centar_id + "'>" + naziv + "</option>";

    

    console.log("APPENDUJEM");
    $('#input-content').append(id_input); 
    $('#input-content').append(oznaka_input);
    $('#input-content').append(kapacitet_input);
    $('#centar-select').append(centar_id_input);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/centar/lista",
        dataType: "json",
        success: function(response) {
            for (let centar of response) {
                if (centar.id == centar_id) { continue; }
                let option = "<option value=" + centar.id + ">" + centar.naziv + "</option>";
                $('#centar-select').append(option);
            }
        },
        error: function(error) {
            alert("doslo do greske prilikom pribavljanja fitnes centara");
        }
    })
    console.log("Zavrsio s apendovanjem");
});

function changeS() {
    $(document).on("submit", "#changeTermin", function(event) {
        event.preventDefault();

        let id = $("#id").val();
        let oznaka = $("#oznaka").val();
        let kapacitet = $("#kapacitet").val();
        //let centar_id = $("#centar_id").val();
        let centar_id = document.getElementById('centar-select').value;


        let podaci = {
            oznaka,
            kapacitet,
            centar_id
        }

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/sala/izmeni/" + id,
            contentType: "application/json",
            data: JSON.stringify(podaci),
            success: function(response) {
                console.log("Success:\n" + response);
                alert("Uspesno izmenjena sala ");
                window.location.reload();
            },
            error: function(error) {
                console.log("ERROR:\n" + error);
                alert("Greska!");
            }
        });
    });
}

function deleteS() {
    $(document).on("submit", "#sale", function(event) {
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
            url: "http://localhost:8080/api/sala/obrisi/" + id,
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

function addSala() {
    $(document).on("submit", "#addSala", function(event){ 
        event.preventDefault();

        let oznaka = $("#oznaka").val();
        let kapacitet = $("#kapacitet").val();
        let centar_id = document.getElementById('centar-select').value;

        let newSala = {
            oznaka,
            kapacitet,
            centar_id
        }

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/sala/add",
            contentType: "application/json",
            data: JSON.stringify(newSala),
            success: function(response) {
                console.log(response);
                alert("Uspesno dodata nova sala");
                window.location.href = "./listaSala.html";
            },
            error: function(error) {
                console.log(error);
                alert("Greska");
            }
        });

    })
}
