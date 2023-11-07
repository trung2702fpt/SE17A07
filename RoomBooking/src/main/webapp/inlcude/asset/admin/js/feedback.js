
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
        "ordering": true,
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
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        data: {
            idReport: id,
            action: "search"
        },
        success: function (data) {
            data = JSON.parse(data);
            $("#id").val(data.reportID);
            $("#title").val(data.title);
            $("#content").val(data.content);
            $("#userEmail").val(data.emailUser.split("@")[0]);
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function RepReport() {
    var id = $("#id").val();
    var repContent = $("#reply").val();
    var emailUser = $("#userEmail").val() + "@fpt.edu.vn";
    if(repContent.trim()===""){
        U.messageBox("ERROR", "PLS text something before send");
        return;
    }
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        data: {
            idReport: id,
            emailUser: emailUser,
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
            }
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}