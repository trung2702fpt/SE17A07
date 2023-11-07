
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/Equipment",
        method: "GET",
        data: {
            admin: "admin",
            action: "getList"
        },
        success: function (data) {
            $("#bodyTableEquipments").html(data);
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
    $('#tableListEquipments').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}