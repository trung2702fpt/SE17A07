function SendReport(){
    var title = $("#title").val();
    var content = $("#report").val();
    var time = new Date().toLocaleString();
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        data:{
            title: title,
            content: content,
            time: time,
            action: "sendReport"
        },
        success: function (data) {
            if (data == "fail") {
                U.messageBox("Message", "fail to update");
                return;
            } else {
                U.messageBox("Message", "Thank for your reported");
                $("#title").val("");
                $("#report").val("");
            }
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}