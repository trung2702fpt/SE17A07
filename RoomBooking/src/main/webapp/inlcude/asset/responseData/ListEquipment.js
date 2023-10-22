
$(document).ready(function () {
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/GetEquipments",
        method: "GET",
        success: function (data) {
            $("#bodyTableEquipments").html(data);
            U.hideProcess();
        },
        error: function () {
            alert("ERROR to process call api!!");
        }
    });
});
