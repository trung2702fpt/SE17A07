
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/GetRooms",
        method: "GET",
        success: function (data) {
            $("#bodyTableRoom").html(data);
            U.hideProcess();
        },
        error: function () {
            alert("ERROR to process call api!!");
        }
    });
});