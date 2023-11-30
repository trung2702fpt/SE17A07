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
    var price = Number.parseInt($('#priceRoom').val());
    if(!idSearchRoom || idSearchRoom.trim() == ""){
        U.messageBox('Message',"Pls input id room or price to update");
        return;
    }
    if(price <= 0){
        U.messageBox('Message',"Price do not zero or negative");
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
                U.messageBox("ERROR", "Fail to update");
                return;
            }else{
                U.messageBox("Message Box", "Update success");
            }
            
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}
function Delete() {
    var idSearchRoom = $('#roomSearch').val();
    if(!idSearchRoom || idSearchRoom.trim() == ""){
        U.messageBox('Message',"Pls input id room or price to update");
        return;
    }
    
    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        data:{
            roomId: idSearchRoom,
            action: "Delete"
        },
        success: function (data) {
            if(data == "fail"){
                U.messageBox("ERROR", "Fail to Delete");
                return;
            }else{
                U.messageBox("Message Box", "Delete success");
            }
            
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}


function AddNew(){
    var id = $('#idRoom').val();
    var number = $('#numberRoom').val();
    var price = $('#priceRoom').val();
    
    if (!id || id.trim() == "" || !number || number.trim() == "" || !price || price.trim() == "") {
        U.messageBox("ERROR","Pls input all fild before edit!!");
        return;
    }
    
    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        data: {
            id: id,
            number: number,
            price: price,
            action: "AddNew"
        },
        success: function (data) {
            if (data == "fail") {
                U.messageBox("ERROR","Fail to add");
                return;
            } else {
                U.messageBox("Message", "Add new room successfully");
                U.hideProcess();
            }
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}
