$(document).ready(function () {
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
                html += `<tr class="candidates-list" id="${equipment.id}" onclick='selectEquiment(this)'  style="cursor: pointer">
                            <td>${equipment.id}</td>
                            <td>${equipment.name}</td>
                            <td>${equipment.des}</td>
                            <td>${equipment.price}</td>
                            <td>${equipment.type.name}</td>
                        </tr>`;
            })
            
            $("#bodyTableEquipments").html(html);
            SetDataTable();
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
});

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
function selectEquiment(element) {
    $(element).hasClass("select-success") ? $(element).removeClass("select-success") : $(element).addClass("select-success");
}

function sendRequest() {
    var equipmentSelected = document.getElementsByClassName('select-success');
    var idEquipments = Array.from(equipmentSelected).map(element => element.getAttribute("id"));

    $.ajax({
        url: "/RoomBooking/ajaxPayment",
        method: "POST",
        dataType: 'JSON',
        data: JSON.stringify({equipmentSelected: idEquipments}),
        beforeSend: function (x) {
            U.showProcess();
        },
        success: function (x) {
            console.log(x);
            if (x.code === '00') {
                location.href = x.data;
                return false;
            } else {
                U.messageBox("Message Box", x.Message);
            }
            U.hideProcess();
        },
        error: function (error) {
            U.messageBox("ERROR", error);
            U.hideProcess();
        }
    });
}