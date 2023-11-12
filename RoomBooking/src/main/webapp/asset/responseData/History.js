
$(document).ready(function () {
    SetDataTable();
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

    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        beforeSend: function (xhr) {
            U.showProcess();
        },
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
        beforeSend: function (xhr) {
            U.showProcess();
        },
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