var U = {
    showProcess: function () {
        var loadingModal = $('<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog">');
        loadingModal.append('<div class="modal-dialog modal-dialog-centered" role="document">');
        var modalContent = $('<div class="modal-content">');

        var modalBody = $('<div class="modal-body">');
        modalBody.append('<div class="spinner-border" role="status">');
        modalBody.append('<span class="my-auto ml-5">Loading...</span>');

        modalContent.append(modalBody);

        loadingModal.find('.modal-dialog').append(modalContent);

        $('#loadingScreen').html(loadingModal);

        loadingModal.modal({
            backdrop: 'static',
            keyboard: false,
        });
    },
    hideProcess: function () {
        $('#loadingModal').modal('hide');
    },
    messageBox: function (title, message) {
        var modal = $('<div class="modal fade" tabindex="-1" role="dialog">');
        modal.append('<div class="modal-dialog modal-dialog-centered" role="document">');
        var modalContent = $('<div class="modal-content">');

        var modalHeader = $('<div class="modal-header">');
        modalHeader.append('<h5 class="modal-title">' + title + '</h5>');
        modalHeader.append('<button type="button" class="close" data-dismiss="modal" aria-label="Close">');
        modalHeader.find('.close').append('<span aria-hidden="true">&times;</span>');

        var modalBody = $('<div class="modal-body">' + message + '</div>');

        var modalFooter = $('<div class="modal-footer">');
        modalFooter.append('<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>');

        modalContent.append(modalHeader);
        modalContent.append(modalBody);
        modalContent.append(modalFooter);

        modal.find('.modal-dialog').append(modalContent);

        $('#messageBox').html(modal);

        modal.modal('show');
    }
};

$("#goBackButton").on("click", function () {
    window.history.back();
});
var check = true;
function setActiveNav(tabName) {
    var tabs = $(".nav-item");
    tabs.each(function (index) {
        var tab = $(this);
        var linkText = tab.find("a").text();
        if (linkText === tabName) {
            tab.addClass("active");
        } else {
            tab.removeClass("active");
        }
    });
}