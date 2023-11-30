function SendReport(){
    var title = $("#title").val();
    var content = $("#report").val();
    
    // Tạo mới feedback
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        beforeSend: function (xhr) {
            U.showProcess();
        },
        data:{
            title: title,
            content: content,
            action: "createReport"
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
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}