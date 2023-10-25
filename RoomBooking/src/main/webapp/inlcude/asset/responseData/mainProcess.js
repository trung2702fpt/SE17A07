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
var check = true;
function setActiveNav(tabName){
    var tabs = $(".nav-item");
    tabs.each(function(index) {
        var tab = $(this);
        var linkText = tab.find("a").text();
        if(linkText === tabName){
            tab.addClass("active");
        }else{
            tab.removeClass("active");
        }
    });
}