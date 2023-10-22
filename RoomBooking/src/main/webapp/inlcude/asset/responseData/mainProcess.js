var U = {
    showProcess: function () {
        $('#loadingScreen').removeClass("hide");
    },
    hideProcess: function () {
        $('#loadingScreen').addClass("hide");
    }
};

$("#goBackButton").on("click", function () {
    window.history.back();
});