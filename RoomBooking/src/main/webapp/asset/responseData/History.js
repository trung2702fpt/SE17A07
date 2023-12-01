
// Sẽ chạy khi viewer được load thành công
$(document).ready(function () {
    SetDataTable();
    getHistory();
    getReport();
    U.hideProcess();
});

function getReport() {
    $.ajax({
        url: "/RoomBooking/Report",
        method: "GET",
        dataType: 'JSON',
        data: {
            type: "user",
            action: "getList"
        },
        success: function (data) {
            console.log(data);
            var html = '';
            if (data.length <= 0) {
                html = "<tr><td colspan='4'><h2 class='text-center'>EMPTY HISTORY!!</h2></td></tr>";
                $("#reportTableBody").html(html);
                U.hideProcess();
                return;
            }

            data.forEach((report, index) => {
                if (report.userID === idUser) {
                    var show = report.isReaded ? "none" : "block";
                    html += `<tr class="candidates-list">   
                                <td>${report.reportID}</td>
                                <td id='report_${report.reportID}'>${report.title}</td>\n"
                                <td>${report.time}</td>\n"
                                <td> <a href="chathub.jsp?id=${report.reportID}&title=${report.title}" class="btn btn-dark my-auto text-light nav-link position-relative">View
                                    <span class="badge badge-danger badge-pill position-absolute" style="top: 0; right: 0; display: ${show};" id="notification-dot">!</span></a> </td>"
                            </tr>`;
                }
            });

            // nó tương tự như thằng innerHTML bên DOM
            $("#reportTableBody").html(html);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function getHistory() {
    $("#historyTableBody").empty();
    $.ajax({
        url: "/RoomBooking/GetHistory",
        method: "GET",
        success: function (data) {
            $("#historyTableBody").html(data);
            U.hideProcess();
        },
        error: function () {
            U.messageBox("ERROR", "ERROR to process call api!!");
            U.hideProcess();
        }
    });
}

function SetDataTable() {
    $('#tableListHistory').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "responsive": true,
    });

    $('#tableListReport').DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": false,
        "ordering": false,
        "info": false,
        "autoWidth": false,
        "responsive": true,
    });
}

function callCencalBooking(d, slot, roomId) {
    U.boxConfirm("Message Box", "Are you sure to cancel this room",function(){
        $.ajax({
            url: "/RoomBooking/RoomBooking",
            method: "POST",
            data: {
                date: d,
                action: "cancel",
                slot: slot,
                roomId: roomId
            },
            success: function () {
                getHistory();
            },
            error: function () {
                U.messageBox("ERROR", "ERROR to process call api!!");
            }
        });
    });
}