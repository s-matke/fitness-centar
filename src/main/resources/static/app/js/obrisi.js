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

$(document).on('click', '.btnPutFC', function() {
    localStorage.setItem("idFC", this.dataset.id);
    console.log("ID RAW: " + this.dataset.id);
    console.log("\nID LS: " + localStorage.getItem('idFC'));
});

function check(id) {
    let r = confirm("Da li ste sigurni da zelite da obrisete centar s ID-om: " + id + "?");
    return r;
}