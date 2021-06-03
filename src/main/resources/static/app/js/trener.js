$(document).ready(function () {
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