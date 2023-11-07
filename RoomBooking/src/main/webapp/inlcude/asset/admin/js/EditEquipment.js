function SearchForEdit(id) {
    $.ajax({
        url: "/RoomBooking/Equipment",
        method: "GET",
        data: {
            idEquipment: id,
            action:"search"
        },
        success: function (data) {
            data = JSON.parse(data);
            if (!data) {
                U.messageBox("Not Found", `not found id room : ${idSearchRoom}`);
                return;
            }

            $('#id').val(data.id);
            $('#name').val(data.name);
            $('#des').val(data.des);
            $('#price').val(data.price);
            $('#typeEquipment').val(data.type.id);
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function Update() {
    var id = $('#id').val();
    var name = $('#name').val();
    var des = $('#des').val();
    var price = $('#price').val();
    var type = $('#typeEquipment').val();
    if (!name || name.trim() == "" || !des || des.trim() == "" || !price || price.trim() == "" || !type || type.trim() == "") {
        U.messageBox("ERROR","Pls input all fild before edit!!");
        return;
    }
    $.ajax({
        url: "/RoomBooking/Equipment",
        method: "GET",
        data: {
            id: id,
            name: name,
            des: des,
            type: type,
            price: price,
            action: "edit"
        },
        success: function (data) {
            if (data == "fail") {
                U.messageBox("ERROR","fail to update");
                return;
            } else {
                $("#bodyTableEquipments").empty();
                U.messageBox("Message Box","update success");
                U.hideProcess();
                setDataEquipment();
            }
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

$(document).ready(function () {
    U.showProcess();
    setDataEquipment();
    $.ajax({
        url: "/RoomBooking/getTypeEquipment",
        method: "GET",
        data: {
            admin: "admin"
        },
        success: function (data) {
            data = JSON.parse(data);
            var html = '';
            data.forEach((elemetn) => html += `<option value="${elemetn.id}">${elemetn.name}</option>`);
            $('#typeEquipment').html(html);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
    SetDataTable();
});

function setDataEquipment() {
    $.ajax({
        url: "/RoomBooking/Equipment",
        method: "GET",
        data: {
            admin: "admin",
            action: "getList"
        },
        success: function (data) {
            $("#bodyTableEquipments").html(data);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

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
