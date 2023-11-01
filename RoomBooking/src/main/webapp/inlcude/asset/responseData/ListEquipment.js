
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/GetEquipments",
        method: "GET",
        success: function (data) {
            $("#bodyTableEquipments").html(data);
            SetDataTable();
            U.hideProcess();
        },
        error: function () {
            alert("ERROR to process call api!!");
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