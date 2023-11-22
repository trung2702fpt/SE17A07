
$(document).ready(function () {
    U.showProcess();
    SetDataTable();
    getReports();
});

function SetDataTable() {
    $('#tableListReport').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}

function getReports() {
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        data: {
            type: "admin",
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

function searchReport(id) {
    $("#message-input").attr("readonly", false);
    
    $.ajax({
        url: "/RoomBooking/Report",
        method: "POST",
        dataType: 'JSON',
        data: {
            idReport: id,
            action: "search",
        },
        success: function (data) {
            $("#id").val(data.reportID);
            $("#title").val(data.title);
            $("#userEmail").val(data.email.split("@")[0]);
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
    
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        dataType: 'JSON',
        data: {
            action: "getComment",
            idReport: id,
        },
        success: function (data) {
            if (!data || data == "fail") {
                U.messageBox("ERROR", "ERROR to process call api!!");
                return;
            }
            var html = '';
            data.forEach((item) => {
                const style = !item.isReply ? "" : "justify-content-end";
                html += `<div class="message-container mt-3 d-flex ${style}">
                                <div class="alert alert-info my-2">
                                    <div style="top: -20px; ">${item.time} :</div>
                                    <span> ${item.content}</span>
                                </div>
                            </div>`;
            })
            $("#chat-box").html(html);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function sendMessage() {
    var id = $("#id").val();
    var repContent = $("#message-input").val();
    if(repContent.trim()===""){
        U.messageBox("ERROR", "PLS text something before send");
        return;
    }
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        data: {
            idReport: id,
            repContent: repContent,
            action: "edit"
        },
        success: function (data) {
            if (data == "fail") {
                U.messageBox("ERROR", "fail to update");
                return;
            } else {
                U.messageBox("Message Box", "update success");
                getReports();
                searchReport(id);
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