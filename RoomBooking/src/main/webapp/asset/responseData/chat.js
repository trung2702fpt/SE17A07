var urlParams = new URLSearchParams(window.location.search);
var ID_report = Number.parseInt(urlParams.get('id'));
var TITLE = urlParams.get('title');

$(document).ready(()=>{
    setInterval(()=>viewChat(ID_report), 1000);
});

function viewChat(idReport) {
    $("#chat-box").html("");
    $("#message-input").attr("readonly", false);
    $('#title_chat_box').html(TITLE);
    
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        dataType: 'JSON',
        beforeSend: function (xhr) {
            U.showProcess();
        },
        data: {
            action: "getComment",
            idReport: idReport,
        },
        success: function (data) {
            console.log(data);
            if (!data || data == "fail") {
                U.messageBox("ERROR", "ERROR to process call api!!");
                return;
            }
            var html = '';
            data.forEach((item) => {
                const style = item.isReply ? "" : "justify-content-end";
                
                html += `<div class="col-md-12 d-flex ${style}">
                    <div class="w-25 m-2">
                        <p class="p-3 mb-2 bg-info text-dark rounded">
                            ${item.time} 
                            <br/>
                            <span> ${item.content}</span>
                        </p>
                    </div>
                </div>`;
            });
            $('#buttonSendMessage').attr('onclick', `sendMessage(${idReport})`);
            $("#chat-box").html(html);
            setTimeout(()=>{
                U.scrollToEdd('#chat-box');
            }, 300);            
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function sendMessage(id) {
    var repContent = $("#message-input").val();
    if (repContent.trim() === "") {
        U.messageBox("ERROR", "PLS text something before send");
        return;
    }
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        data: {
            idReport: id,
            repContent: repContent,
            action: "edit",
            user: true
        },
        success: function (data) {
            if (data == "fail") {
                U.messageBox("ERROR", "fail to update");
                return;
            } else {
                viewChat(id);
                setTimeout(()=>{
                    U.scrollToEdd('#chat-box');
                    U.scrollToEdd('body');
                }, 1000);
            }
            $("#message-input").val("")
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}