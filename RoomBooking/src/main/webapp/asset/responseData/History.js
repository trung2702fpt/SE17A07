
$(document).ready(function () {
    document.getElementById('chat-box').scrollTop = document.getElementById('chat-box').scrollHeight;
    SetDataTable();
    getHistory();
    getReport();
    U.hideProcess();
});

function getReport() {
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        data: {
            type: "user",
            action: "getList"
        },
        success: function (data) {
            $("#reportTableBody").html(data);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function getHistory() {
    $("#historyTableBody").empty();
    $.ajax({
        url: "/RoomBooking/GetHistory",
        method: "GET",
        beforeSend: function (xhr) {
            U.showProcess();
        },
        success: function (data) {
            $("#historyTableBody").html(data);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function SetDataTable() {
    $('#tableListHistory').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });

    $('#tableListReport').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}

function callCencalBooking(d, slot, roomId) {
    U.boxConfirm("Message Box", "Are you sure to cencel this room", function () {
        $.ajax({
            url: "/RoomBooking/RoomBooking",
            method: "GET",
            beforeSend: function (xhr) {
                U.showProcess();
            },
            data: {
                date: d,
                action: "cancel",
                slot: slot,
                roomId: roomId
            },
            success: function (data) {
                getHistory();
            },
            error: function () {
                U.messageBox("ERROR", "ERROR to process call api!!");
                U.hideProcess();
            }
        });
    })
}

function viewChat(idReport) {
    $("#message-input").attr("readonly", false);
    var title = $(`#report_${idReport}`).html().trim();
    $('#title_chat_box').html(title);
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
            if (!data || data == "fail") {
                U.messageBox("ERROR", "ERROR to process call api!!");
                return;
            }
            var html = '';
            data.forEach((item) => {
                const style = item.isReply ? "" : "justify-content-end";
                html += `<div class="message-container mt-3 d-flex ${style}">
                                <div class="alert alert-info my-2">
                                    <div style="top: -20px; ">${item.time} :</div>
                                    <span> ${item.content}</span>
                                </div>
                            </div>`;
            })
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
                getReport();
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