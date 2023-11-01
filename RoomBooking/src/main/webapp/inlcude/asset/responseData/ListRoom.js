
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/GetRooms",
        method: "GET",
        success: function (data) {
            $("#bodyTableRoom").html(data);
            SetDataTable();
            U.hideProcess();
        },
        error: function () {
            alert("ERROR to process call api!!");
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