var dateSelect;
var slotSelect;
var currentTime = new Date();
var slotOptions;
$(document).ready(function () {
    slotOptions = ['<option value="1">Slot 1 (7-9 AM)</option>', '<option value="2">Slot 2 (9-11 AM)</option>'
                , '<option value="3">Slot 3 (11-13 PM)</option>', '<option value="4">Slot 4 (13-15 PM)</option>'];
    dateSelect = $("#dateSelected");
    slotSelect = $("#slotSelected");
    var currentDate = new Date().toISOString().slice(0, 10);
    dateSelect.attr("min", currentDate);
});

function setSlotByDate() {
    
    var formattedDate = currentTime.getFullYear() + '-' + (currentTime.getMonth() + 1).toString().padStart(2, '0') + '-' + currentTime.getDate().toString().padStart(2, '0');
    if (dateSelect.val() === formattedDate) {
        var currentHours = currentTime.getHours();

        var timeRanges = ["7-9", "9-11", "11-13", "13-15"];

        var currentRange = null;
        var html = '';
        for (var i = timeRanges.length -1 ; i >= 0 ; i--) {
            var range = timeRanges[i].split("-");
            var startHour = parseInt(range[0], 10);
            var endHour = parseInt(range[1], 10);
            
            if (currentHours >= startHour && currentHours < endHour) {
                currentRange = timeRanges[i];
                break;
            }
            html = slotOptions[i] + html;
        }
        if(html ==='') {
            html = '<option value="">Overtime to book room</option>';
        }
        slotSelect.html(html);
    }
}

function searchByName() {
    if(slotSelect.val() === ''){
        alert("Overtime to book room!");
        return ;
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