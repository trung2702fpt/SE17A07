
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/GetHistory",
        method: "GET",
        success: function (data) {
            $("#historyTableBody").html(data);
            SetDataTable()
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
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
}
