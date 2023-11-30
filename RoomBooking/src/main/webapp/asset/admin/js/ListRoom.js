$(document).ready(function () {
    $.ajax({
        url: "/RoomBooking/Room",
        method: "GET",
        dataType: 'JSON',
        data:{
            action: "getList",
        },
        success: function (data) {
            console.log(data);
            var html = '';
            if(data.length <= 0){
                html = "<tr><td colspan=\"4\"><h2 class='text-center'>EMPTY ROOM!!</h2></td></tr>";
                $("#bodyTableRoom").html(html);
                U.hideProcess();
                return;
            }
            
            data.forEach((room)=>{
                html += `<tr class="candidates-list">
                            <td>${room.id}</td>
                            <td class="title">
                                <div class="thumb">
                                    <img class="img-fluid w-25" src="./asset/images/gallery1.jpg" alt="">
                                </div>\n"
                            </td>
                            <td>${room.roomNumber}</td>
                            <td>${room.price}<span>.000 VND</span>
                            </td>
                        </tr>`;
            });
            $("#bodyTableRoom").html(html);
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
    $('#tableListRooms').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}