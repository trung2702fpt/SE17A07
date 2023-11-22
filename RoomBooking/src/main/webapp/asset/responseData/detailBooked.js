
var urlParams = new URLSearchParams(window.location.search);
var ID_BOOKING = Number.parseInt(urlParams.get('IdBooking'));

$(document).ready(function () {
    $('#tableListEquipments').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
    
    $.ajax({
        url: "/RoomBooking/DetailBooked",
        method: "GET",
        dataType: 'JSON',
        data: {
            action: "getDetail",
            idBooking: ID_BOOKING,
        },
        beforeSend: function (xhr) {
            U.showProcess();
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
                            <td>${item.price}</td>
                            <td>${item.quanlity}</td>
                        </tr>`;
            } 
            $('#equipmentsTableBody').html(html);
            $('#RoomTitle').html(data.id);
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
});