$(document).ready(function() {
    let id = sessionStorage.getItem('id');
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trener/trening/lista",
        dataType: "json",
        data: {id},
        success: function(response) {
            console.log("SUCCESS:\n" +response);
            for (let trening of response) {
                let row = "<tr>";
                row += "<td>" + trening.naziv + "</td>";
                row += "<td>" + trening.opis + "</td>";
                // console.log("tip: " + trening.tip_treninga + "\n trajanje: " + trening.trajanje);
                row += "<td>" + trening.tip + "</td>";
                row += "<td>" + trening.trajanje + "</td>";
                let boxes = "<input type='radio' name='odjava' value='" + trening.id + "'/>";
                row += "<td>" + boxes + "</td>";
                let btn = "<button class='btnEditTrening' data-id='" + trening.id + "' data-naziv='" + trening.naziv + "' data-opis='" + trening.opis + "' data-tip='" + trening.tip + "' data-trajanje='" + trening.trajanje + "'>Edit</button>";
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

$(document).on('click', '.btnEditTrening', function(event) {

    event.preventDefault(); // da nam se ne bi reloadala stranica..
    //console.log("Usao u deo gde cuvam info");
    var tabela = $(".trening-block")
    var changeForm = $(".change-block")
    var dugme = $(".back-btn");

    tabela.hide();
    changeForm.show();
    dugme.show();
//let btn = "<button class='btnEditTrening' data-id=" + trening.id + " data-naziv='" + trening.naziv + "' data-opis='" + trening.opis + "' 
// data-tip='" + trening.tip_treninga + "' data-trajanje='" + trening.trajanje + "'>Edit Sala</button>";
    // Vrednosti
    let id = this.dataset.id;
    let naziv = this.dataset.naziv;
    let opis = this.dataset.opis;
    let tip = this.dataset.tip;
    let trajanje = this.dataset.trajanje;


    let id_input = "<input id='id' type='number' disabled value='" + id + "' />"; 
    let naziv_input = "<input id='naziv' type='text' value='"+naziv+"' />";
    let opis_input ="<input id='opis' type='text' value='"+opis+"' />";
    let tip_input ="<input id='tip_treninga' type='text' value='"+tip+"' />";
    let trajanje_input = "<input id='trajanje' type='number' value='"+trajanje+"' />";
       

    console.log("APPENDUJEM");
    $('#input-content').append(id_input); 
    $('#input-content').append(naziv_input);
    $('#input-content').append(opis_input);
    $('#input-content').append(tip_input);
    $('#input-content').append(trajanje_input);

});

function changeTrening() {
    $(document).on("submit", "#changeTermin", function(event) {
        event.preventDefault();

        let id = $("#id").val();
        let naziv = $("#naziv").val();
        let opis = $("#opis").val();
        let tip = $("#tip_treninga").val();
        let trajanje = $("#trajanje").val();
        //let centar_id = $("#centar_id").val();
        // let centar_id = document.getElementById('centar-select').value;
        let uid = sessionStorage.getItem('id');

        let podaci = {
            naziv,
            opis,
            tip,
            trajanje
        }

        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/api/trening/izmeni/" + uid + "/" + id,
            contentType: "application/json",
            data: JSON.stringify(podaci),
            success: function(response) {
                console.log("Success:\n" + response);
                alert("Uspesno izmenjen trening");
                window.location.reload();
            },
            error: function(error) {
                console.log("ERROR:\n" + error);
                alert("Greska!");
            }
        });
    });
}

function deleteTrening() {
    $(document).on("submit", "#treninzi", function(event) {
        event.preventDefault();
        
        let id;
        $("input:radio[name='odjava']:checked").each(function() {
            id = parseInt($(this).val());
        });
        if (id == null) {alert("Morate izabrati trening koji zelite obrisati"); return;}
        // if(!check(id)) {return;}

        // alert("Ipak je prosao da brise, id: " + id);
        
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api/trening/obrisi/" + id,
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
