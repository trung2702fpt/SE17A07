
$(document).ready(function () {
    
    SetDataTable();
    getHistory();

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

function getHistory(){
    $("#historyTableBody").empty();
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
            data: {
                date: d,
                action: "cancel",
                slot: slot,
                roomId: roomId
            },
            success: function (data) {
                console.log(123);
                getHistory();
            },
            error: function () {
                U.messageBox("ERROR", "ERROR to process call api!!");
                U.hideProcess();
            }
        });
    })
}