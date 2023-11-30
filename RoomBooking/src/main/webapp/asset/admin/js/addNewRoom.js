
function Validation() {
    var id = $('#numberRoom').val();
    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        dataType: 'JSON',
        data: {
            id: id,
            action: "validateRoomID"
        },
        success: function (data) {
            if (data === "existed") {
                U.messageBox("MESSAGE", "THIS ROOM WAS EXISTED");
                return;
            }
            
            if(data === "ok"){
                addnew();
            }
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function addnew() {
    var id = $('#numberRoom').val();
    var price = $('#priceRoom').val();

    if (!id || id.trim() == "" || !price || price.trim() == "") {
        U.messageBox("ERROR", "Pls input all fild before edit!!");
        return;
    }

    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        beforeSend: function (xhr) {
            U.showProcess();
        },
        data: {
            id: id,
            price: price,
            action: "AddNew"
        },
        success: function (data) {
            if (data == "fail") {
                U.messageBox("ERROR", "Fail to add");
                return;
            } else {
                U.messageBox("Message", "Add new room successfully");
                $('#numberRoom').val("");
                $('#priceRoom').val("");
            }
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}