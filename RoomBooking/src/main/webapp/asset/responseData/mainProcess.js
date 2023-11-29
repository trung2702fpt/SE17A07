var U = {
    scrollToEdd: function (idElement) {
        const $chatContainer = $(idElement);
        $chatContainer.scrollTop($chatContainer.prop('scrollHeight'));
    },
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
        $('#messageBox').empty().append('<div class=\"message-box\">' + message + '</div>').css('display', '');
        $('#messageBox').dialog(
                {
                    modal: true,
                    resizable: false,
                    title: title,
                    width: 400,
                    overlay: {
                        opacity: 0.5,
                        background: '#999'
                    },
                    buttons:
                            [
                                {
                                    text: "OK",
                                    "class": 'btn btn-cancel d-inline-block',
                                    click: function () {
                                        $(this).dialog('close');
                                        $('#messageBox').empty();
                                    }
                                }
                            ],
                    close: function () {
                        $(this).dialog('destroy');
                        $('#messageBox').empty();
                    }
                }
        );
    },
    boxConfirm: function (title, message, handleAction) {
        $('#confirmBox').empty().append('<div class=\"confirm-box\">' + message + '</div>').css('display', '');
        $('#confirmBox').dialog(
                {
                    modal: true,
                    resizable: false,
                    title: title,
                    width: 400,
                    overlay: {
                        opacity: 0.5,
                        background: '#999'
                    },
                    buttons:
                            [
                                {
                                    text: "No",
                                    "class": 'btn btn-cancel d-inline-block',
                                    click: function () {
                                        $(this).dialog('close');
                                        $('#confirmBox').empty();
                                    }
                                },
                                {
                                    text: "Yes",
                                    "class": 'btn btn-dark-blue d-inline-block',
                                    click: function () {
                                        handleAction();
                                        $(this).dialog('close');
                                        $('#confirmBox').empty();
                                    }
                                }
                            ],
                    close: function () {
                        $(this).dialog('destroy');
                        $('#confirmBox').empty();
                    }
                }
        )
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

function compareDates(inputDateString) {
  const inputDate = new Date(inputDateString.replace(/-/g, '/'));

  if (isNaN(inputDate)) {
    return false;
  }

  const currentDate = new Date();
  
  if (inputDate > currentDate) {
    return true; // chưa vượt quá ngày hiện tại
  } else{
    return false; // đã vượt quá ngày hiện tại
  }
}