$(document).ready(function() {
    if (sessionStorage.getItem('role') != null) {
        $(".logout").hide();
        $(".login").show();
        $(".logged-navbar").show();
    } else {
        $(".logout").show();
        $(".login").hide();
        $(".logged-navbar").hide();
    }
});