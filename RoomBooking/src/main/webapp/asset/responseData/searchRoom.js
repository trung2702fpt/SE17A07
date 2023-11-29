var dateSelect;
var slotSelect;

$(document).ready(function () {
    dateSelect = $("#dateSelect");
    slotSelect = $("#slotSelect");

    var currentDate = new Date().toISOString().slice(0, 10);
    dateSelect.attr("min", currentDate);
    SetDataTable();
});


function setSlotByDate() {
    var selectedDate = new Date(event.target.value);
    if (selectedDate.getDay() === 0) {
        U.messageBox("ERROR", 'Không thể chọn Chủ Nhật.');
        event.target.value = '';
        return;
    }
    var currentTime = new Date();
    var dateInput = document.getElementById("dateSelect");
    var slotSelect = document.getElementById("slotSelect");

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
    var slotSelect = document.getElementById("slotSelect");

    option.text = text;
    option.value = value;

    slotSelect.appendChild(option);
}

function searchByName() {
    if (slotSelect.val() === '') {
        U.messageBox("Message", "Overtime to book room for today!");
        return;
    }

    if (dateSelect.val() === "") {
        U.messageBox("Message", "Please select your date or slot!!");
        return;
    }
    U.showProcess();
    $.ajax({
        url: "/RoomBooking/Room",
        type: "get",
        dataType: 'JSON',
        data: {
            dateSelect: dateSelect.val(),
            slotSelect: slotSelect.val(),
            action: "filterRoom",
        },
        success: function (data) {
            console.log(data);
            var html = '';
            if(data.length <= 0){
                html = "<tr><td colspan='4'><h2 class='text-center'>EMPTY ROOM!!</h2></td></tr>";
                $("#bodyTableRoom").html(html);
                U.hideProcess();
                return;
            }
            
            data.forEach((room, index)=>{
                html += `<tr class="candidates-list">
                            <td>${index}</td>
                            <td class="title">
                                <div class="thumb">
                                    <img class="img-fluid w-25" src="./asset/images/gallery1.jpg" alt="">
                                </div>
                            </td>
                            <td>${room.roomNumber}</td>
                            <td>${room.price}</td>
                            <td>
                                <a type="button" href='#' onclick='openDialog(${room.id},${room.price})' class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Booking</a>
                            </td>
                        </tr>`;
            });
            $("#contentSearchroom").html(html);
            U.hideProcess();
        },
        error: function (e) {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function SetDataTable() {
    $('#tableListSearchrooms').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}

function booking() {
    $("#bookingDialog").dialog({
        autoOpen: false,
        width: 400,
        modal: true,
        buttons: {
            "Đặt phòng": function () {
                var selectedDate = "2";
                var bookingInfo = "2";

                U.messageBox("Message", "Bạn đã đặt phòng vào ngày " + selectedDate + " với thông tin: " + bookingInfo);
                $(this).dialog("close");
            },
            Hủy: function () {
                $(this).dialog("close");
            }
        }
    });

    $("#openDialog").on("click", function () {
        $("#bookingDialog").dialog("open");
    });
}

function openDialog(idRoom, price) {
    var idSlot = $("#slotSelect").val();
    var date = $("#dateSelect").val();
    $('#contentPopupBooking').html(`<form action="RoomBooking" id="formBooking" methos="POST">
        <input type="hidden" name="action" value="Booking">
            <div class="p-2">
                                <h2 id="titleRoom" class="card-title"> Room ${idRoom}</h2>
                                <input type="hidden" name="idRoom" value="${idRoom}">
                            </div>
                            <div class="p-2">
                                <p id="dateSelected" class="card-text"> Date: ${date}</p>
    <input type="hidden" name="date" value="${date}">
                            </div>
                            <div class="p-2">
                                <p id="slotSelected" class="card-text"> Slot: ${idSlot}</p>
    <p class="p-2">Each room has <b>30 sets</b> of tables and chairs</p>
    <input type="hidden" name="slotSelected" value="${idSlot}">
    <input type="hidden" name="price" value="${price}">
                            </div>
        </form>`);
}

function submitForm() {
    var formBooking = $('#formBooking');

    if (!formBooking) {
        return;
    }
    formBooking.submit();
}