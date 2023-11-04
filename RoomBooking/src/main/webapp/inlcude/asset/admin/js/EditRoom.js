function Search() {
    var idSearchRoom = $('#roomSearch').val();
    if(!idSearchRoom || idSearchRoom.trim() == ""){
        alert("Pls input id room");
        return;
    }
    
    $.ajax({
        url: "/RoomBooking/SearchRoom",
        method: "GET",
        data:{
            idRoom: idSearchRoom
        },
        success: function (data) {
            data = JSON.parse(data);
            if(!data){
                alert(`not found id room : ${idSearchRoom}`);
                return;
            }
            console.log(typeof  data)
            $('#idRoom').html(data.id);
            $('#numberRoom').html(data.roomNumber);
            $('#priceRoom').val(data.price);
        },
        error: function () {
            alert("ERROR to process call api!!");
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
        url: "/RoomBooking/EditRoom",
        method: "GET",
        data:{
            idRoom: idSearchRoom,
            price: price,
        },
        success: function (data) {
            if(data == "fail"){
                alert("fail to update");
                return;
            }else{
                alert("update success");
            }
            
        },
        error: function () {
            alert("ERROR to process call api!!");
        }
    });
}