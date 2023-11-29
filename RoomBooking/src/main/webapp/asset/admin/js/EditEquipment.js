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
                U.messageBox("ERROR","Fail to update");
                return;
            } else {
                $("#bodyTableEquipments").empty();
                U.messageBox("Message Box","Update success");
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
    setDataEquipment();
    getTypeEquipment()
    SetDataTable();
});

function getTypeEquipment(){
    $.ajax({
        url: "/RoomBooking/getTypeEquipment",
        method: "GET",
        dataType: 'JSON',
        data: {
            admin: "admin"
        },
        success: function (data) {
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
}

function setDataEquipment() {
    $.ajax({
        url: "/RoomBooking/Equipment",
        method: "GET",
        dataType: 'JSON',
        beforeSend: function (xhr) {
            U.showProcess();
        },
        data:{
            action: "getList",
        },
        success: function (data) {
            console.log(data);
            var html = "";
            if(data.length <=0){
                html = "<tr><td colspan=\"6\"><h2 class='text-center'>EMPTY EQUIPMETN!!</h2></td></tr>";
                $("#bodyTableEquipments").html(html);
                U.hideProcess();
                return;
            }
            data.forEach((equipment)=>{
                html += `<tr class="candidates-list" onclick='SearchForEdit(${equipment.id})'>
                            <td>${equipment.id}</td>
                            <td class="title">
                                <div class="thumb">
                                    <img class="img-fluid w-50" src="./asset/images/equipment/${equipment.type.name}.png" alt="">
                                </div>
                            </td>
                            <td>${equipment.name}</td>
                            <td>${equipment.des}</td>
                            <td>${equipment.price}</td>
                            <td>${equipment.type.name}</td>
                        </tr>`;
            })
            
            $("#bodyTableEquipments").html(html);
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
