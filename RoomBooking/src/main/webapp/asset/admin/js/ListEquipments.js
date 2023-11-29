$(document).ready(function () {
    $.ajax({
        url: "/RoomBooking/Equipment",
        method: "GET",
        dataType: 'JSON',
        beforeSend: function (xhr) {
            U.showProcess();
        },
        data:{
            action: "getList",
<<<<<<< Updated upstream
=======
            select: "true"
>>>>>>> Stashed changes
        },
        success: function (data) {
            console.log(data);
            var html = "";
            if(data.length <=0){
                html = "<tr><td colspan=\"6\"><h2 class='text-center'>EMPTY EQUIPMETN!!</h2></td></tr>";
                $("#bodyTableEquipments").html(html);
                U.hideProcess();
                return;
            }
            data.forEach((equipment)=>{
                html += `<tr class="candidates-list">
                            <td>${equipment.id}</td>
                            <td class="title">
                                <div class="thumb">
                                    <img class="img-fluid w-50" src="./asset/images/equipment/${equipment.type.name}.png" alt="">
                                </div>
                            </td>
                            <td>${equipment.name}</td>
                            <td>${equipment.des}</td>
                            <td>${equipment.price}</td>
                            <td>${equipment.type.name}</td>
                        </tr>`;
            })
            
            $("#bodyTableEquipments").html(html);
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
    $('#tableListEquipments').DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": false,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}

