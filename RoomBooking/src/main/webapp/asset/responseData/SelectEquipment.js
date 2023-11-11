$(document).ready(function () {
    $.ajax({
        url: "/RoomBooking/Equipment",
        method: "GET",
        data: {
            admin: "admin",
            action: "getList",
            select: "true"
        },
        beforeSend: function (xhr) {
            U.showProcess();
        },
        success: function (data) {
            $("#bodyTableEquipments").html(data);
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