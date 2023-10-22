
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/GetHistory",
        method: "GET",
        success: function (data) {
            $("#historyTableBody").html(data);
            U.hideProcess();
        },
        error: function () {
            alert("ERROR to process call api!!");
        }
    });
});

