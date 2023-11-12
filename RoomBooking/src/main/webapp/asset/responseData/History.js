
$(document).ready(function () {
    U.showProcess();
    SetDataTable();
    $.ajax({
        url: "/RoomBooking/GetHistory",
        method: "GET",
        success: function (data) {
            $("#historyTableBody").html(data);
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
});

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

function callCencalBooking(d) {
    $.ajax({
        url: "/RoomBooking/RoomBooking",
        method: "GET",
        data: {
            date: d,
            action: "cancel"
        },
        success: function (data) {
            location.reload();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}