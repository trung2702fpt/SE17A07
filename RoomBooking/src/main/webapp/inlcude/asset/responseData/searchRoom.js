var dateSelect;
var slotSelect;

$(document).ready(function () {
    dateSelect = $("#dateSelected");
    slotSelect = $("#slotSelected");
    
    var currentDate = new Date().toISOString().slice(0, 10);
    dateSelect.attr("min", currentDate);
});

function setSlotByDate() {
    var currentTime = new Date();
    var dateInput = document.getElementById("dateSelected");
    var slotSelect = document.getElementById("slotSelected");

    slotSelect.innerHTML = "";

    var selectedDate = new Date(dateInput.value);

    if (selectedDate.toDateString() === currentTime.toDateString()) {
        var time = currentTime.getHours();
        
        if(time < 7){
            addOption("Slot 1 (7-9 AM)", 1);
        }
        
        if(time < 9){
            addOption("Slot 2 (9-11 AM)", 2);
        }
        
        if(time < 11){
            addOption("Slot 3 (11 AM - 1 PM)", 3);
        }
        
        if(time < 13){
            addOption("Slot 4 (1-3 PM)", 4);
        }
        
        if(time < 15){
            addOption("Slot 5 (3-5 PM)", 5);
        }else{
            addOption("It overtime to order room", '');
        }
        
    } else {
        addOption("Slot 1 (7-9 AM)", 1);
        addOption("Slot 2 (9-11 AM)", 2);
        addOption("Slot 3 (11 AM - 1 PM)", 3);
        addOption("Slot 4 (1-3 PM)", 4);
        addOption("Slot 5 (3-5 PM)", 5);
    }
}

function addOption(text, value) {
    var option = document.createElement("option");
    var slotSelect = document.getElementById("slotSelected");
    
    option.text = text;
    option.value = value;
    
    slotSelect.appendChild(option);
}

function searchByName() {
    if (slotSelect.val() === '') {
        alert("Overtime to book room for today!");
        return;
    }

    if (dateSelect.val() === "") {
        alert("Please select your date or slot!!");
        return;
    }

    $.ajax({
        url: "/RoomBooking/searchApi",
        type: "get",
        data: {
            dateSelect: dateSelect.val(),
            slotSelect: slotSelect.val()
        },
        success: function (rop) {
            $("#contentSearchroom").html(rop);
        },
        error: function (e) {

            alert("ERROR to process call api!!");
        }
    });
}