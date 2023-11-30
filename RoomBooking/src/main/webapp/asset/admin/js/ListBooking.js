$(document).ready(function () {
    $.ajax({
        url: "/RoomBooking/ManagementBooking",
        method: "POST",
        data:{
            action: 'getList'
        },
        dataType: 'JSON',
        beforeSend: function (xhr) {
        },
        data:{
            action: "getList",
        },
        success: function (data) {
            console.log(data);
            var html = "";
            if(data.length <=0){
                html = "<tr><td colspan=\"6\"><h2 class='text-center'>EMPTY DATA!!</h2></td></tr>";
                $("#BookingListTableBody").html(html);
                U.hideProcess();
                return;
            }
            data.forEach((booking)=>{
                var cancel = '';
                if(!compareDates(booking.bookingDate)){
                    cancel = "It over Time date";
                }else{
                    cancel = booking.cancelDate !== null ? booking.cancelDate : "Not yet";
                }
                html += `<tr class="candidates-list">
                            <td>${booking.bookingId}</td>
                            <td>${booking.roomID}</td>
                            <td>${booking.bookingDate}</td>
                            <td>${cancel}</td>
                            <td>${booking.email.split('@')[0]}</td>
                            <td><a href='ticketBooking.jsp?id=${booking.bookingId}' class='btn btn-dark my-auto text-light nav-link'>Detail</a></td>
                        </tr>`;
            })        
            $("#BookingListTableBody").html(html);
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
    $('#tableListBooking').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": false,
        "autoWidth": false,
        "responsive": true,
    });
}