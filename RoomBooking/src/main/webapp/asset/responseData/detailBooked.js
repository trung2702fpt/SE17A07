
var urlParams = new URLSearchParams(window.location.search);
var ID_BOOKING = Number.parseInt(urlParams.get('IdBooking'));

$(document).ready(function () {
    $('#tableListEquipments').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "responsive": true
    });
    
    $.ajax({
        url: "/RoomBooking/DetailBooked",
        method: "GET",
        dataType: 'JSON',
        data: {
            action: "getDetail",
            idBooking: ID_BOOKING,
        },
        success: function (data) {
            console.log(data);
            U.hideProcess();
            if(data == null){
                U.messageBox("ERROR", "Can't find the data of room, please try again!");
                return;
            }
            var html = '';
            for (var i = 0; i < data.equipments.length; i++) {
                var item = data.equipments[i];
                html += `<tr>
                            <td>${item.name}</td>
                            <td>${item.des}</td>
                            <td>${item.price}<span>.000 VND</span></td>
                            <td>${item.quanlity}</td>
                        </tr>`;
            } 
            $('#equipmentsTableBody').html(html);
            setDetail(data);
            $('#RoomTitle').html(data.id);
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
});

function isOverTimeBooking(dateString) {
    var givenDate = new Date(dateString);
    var now = new Date();

    if (givenDate > now) {
        return true;
    }
    return false;
}

function setDetail(data){
    $('#idRoom').html(data.roomName);
    $('#dateBooking').html(data.dateBook);
    $('#slot').html(data.slotId);
    $('#amount').html(data.amount);
    $('#datePaied').html(data.datePaied);
}