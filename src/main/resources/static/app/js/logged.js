$(document).ready(function() {
    let role = sessionStorage.getItem('role');
    if (role != null) {
        if (role == "Clan") {
            $("#user").show();
        }
        else if (role == "Trener") {
            $("#trener").show();
        }
        else if (role == "Admin") {
            $("#admin").show();
        }
        $(".logout").hide();
        $(".login").show();
        $(".logged-navbar").show();
    } else {
        $("#user").hide();
        $("#trener").hide();
        $("#admin").hide();
        $(".logout").show();
        $(".login").hide();
        $(".logged-navbar").hide();
    }
});