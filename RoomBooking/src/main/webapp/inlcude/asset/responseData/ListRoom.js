
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        data:{
            action: "getList",
        },
        success: function (data) {
            $("#bodyTableRoom").html(data);
            SetDataTable();
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
});

function SetDataTable() {
    $('#tableListRooms').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}