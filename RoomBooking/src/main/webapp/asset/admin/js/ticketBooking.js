var urlParams = new URLSearchParams(window.location.search);
var ID_BOOKING = Number.parseInt(urlParams.get('id'));

$(document).ready(function () {

    var currentDate = new Date().toISOString().slice(0, 10);
    $("#dateBooking").attr("min", currentDate);
    
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
                            <td>${item.price}<span>.000 VND</span></td>
                            <td>${item.quanlity}</td>
                        </tr>`;
            } 
            $('#equipmentsTableBody').html(html);
            setDetail(data);
            $('#RoomTitle').html(data.id);
            U.hideProcess();
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
    const dateWithoutTime = data.dateBook.split(' ')[0].replaceAll('/', '-');
    $('#User').val(data.email);
    $('#idRoom').val(data.roomName);
    $('#dateBooking').val(dateWithoutTime);
    $('#slot').val(data.slotId);
    $('#amount').html(data.amount);
    if(data.isCancel || !compareDates(data.dateBook)){
        var tabInputs = $('input');
        for (var i = 0; i < tabInputs.length; i++) {
            tabInputs[i].setAttribute('disabled', true);
        }
        $('#slot').attr('disabled', true);
    }
}

function checkForUpdate(){
    const slot = $('#slot').val();
    const date = $('#dateBooking').val();
    $.ajax({
        url: "/RoomBooking/DetailBooked",
        method: "GET",
        dataType: 'JSON',
        data: {
            action: "check",
            idBooking: ID_BOOKING,
            slot: slot,
            date: date
        },
        beforeSend: function (xhr) {
            U.showProcess();
        },
        success: function (data) {
            console.log(data);
            if(data === "false"){
                U.messageBox("MESSAGE","In this time had booked by orther");
                U.hideProcess();
                return;
            }
            
            U.messageBox("MESSAGE","You can change in this time");
            $('#btnAccept').attr('disabled', false);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    }); 
}

function setDisableAccept(){
    $('#btnAccept').attr('disabled', true);
}


function setSlotByDate() {
    setDisableAccept();
    var selectedDate = new Date(event.target.value);
    if (selectedDate.getDay() === 0) {
        U.messageBox("ERROR", 'Không thể chọn Chủ Nhật.');
        event.target.value = '';
        return;
    }
    var currentTime = new Date();
    var dateInput = document.getElementById("dateBooking");
    var slotSelect = document.getElementById("slot");

    slotSelect.innerHTML = "";

    var selectedDate = new Date(dateInput.value);

    if (selectedDate.toDateString() === currentTime.toDateString()) {
        var time = currentTime.getHours();

        if (time < 7) {
            addOption("Slot 1 (7-9 AM)", 1);
        }

        if (time < 9) {
            addOption("Slot 2 (9-11 AM)", 2);
        }

        if (time < 11) {
            addOption("Slot 3 (11 AM - 1 PM)", 3);
        }

        if (time < 13) {
            addOption("Slot 4 (1-3 PM)", 4);
        }

        if (time < 15) {
            addOption("Slot 5 (3-5 PM)", 5);
        }

        if (time < 17) {
            addOption("Slot 6 (5-7 PM)", 6);
        }

        if (time < 19) {
            addOption("Slot 7 (7-9 PM)", 7);
        } else {
            addOption("It overtime to order room", '');
        }

    } else {
        addOption("Slot 1 (7-9 AM)", 1);
        addOption("Slot 2 (9-11 AM)", 2);
        addOption("Slot 3 (11 AM - 1 PM)", 3);
        addOption("Slot 4 (1-3 PM)", 4);
        addOption("Slot 5 (3-5 PM)", 5);
        addOption("Slot 6 (5-7 PM)", 6);
        addOption("Slot 7 (7-9 PM)", 7);
    }
}

function addOption(text, value) {
    var option = document.createElement("option");
    var slotSelect = document.getElementById("slot");

    option.text = text;
    option.value = value;

    slotSelect.appendChild(option);
}