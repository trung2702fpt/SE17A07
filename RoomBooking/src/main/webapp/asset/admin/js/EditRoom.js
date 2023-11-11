function Search() {
    var idSearchRoom = $('#roomSearch').val();
    if(!idSearchRoom || idSearchRoom.trim() == ""){
        U.messageBox("ERROR", "Pls input id room");
        return;
    }
    
    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        data:{
            idRoom: idSearchRoom,
            action: "search",
        },
        success: function (data) {
            data = JSON.parse(data);
            if(!data){
                U.messageBox("ERROR", `not found id room : ${idSearchRoom}`);
                return;
            }
            $('#idRoom').val(data.id);
            $('#numberRoom').val(data.roomNumber);
            $('#priceRoom').val(data.price);
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function Update() {
    var idSearchRoom = $('#roomSearch').val();
    var price = $('#priceRoom').val();
    if(!idSearchRoom || idSearchRoom.trim() == "" || !price || price.trim() == ""){
        alert("Pls input id room or price to update");
        return;
    }
    
    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        data:{
            idRoom: $('#idRoom').val(),
            price: price,
            action: "edit"
        },
        success: function (data) {
            if(data == "fail"){
                U.messageBox("ERROR", "fail to update");
                return;
            }else{
                U.messageBox("Message Box", "update success");
            }
            
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}