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
                            <td>
                                <img class="img-fluid w-25" src="./asset/images/equipment/${equipment.type.name}.png" alt="">
                            </td>
                            <td>${equipment.name}</td>
                            <td>${equipment.des}</td>
                            <td>${equipment.price}<span>.000 VND</span></td>
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
        "ordering": false,
        "info": true,
        "autoWidth": false,
        "responsive": true,
    });
}