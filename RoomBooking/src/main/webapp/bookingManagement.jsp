
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
        <link href=".\asset\vendor\fontawesome-free\css\all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link href="./asset/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <div id="page-top">
            <div id="wrapper">
                <%@include file="viewerComponent/admin/SidebarAdmin.jsp" %>

                <div class="card-body col-12">
                    <h4 class="text-center">Equipments</h4>
                    <table id="tableListBooking" class="table table-bordered table-hover text-center">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Room</th>
                                <th>Date Booked</th>
                                <th>Date Cancel</th>
                                <th>User</th>
                                <th>action</th>
                            </tr>
                        </thead>
                        <tbody id="BookingListTableBody">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>

<%@include file="viewerComponent/js_link.jsp" %>
<script src="./asset/admin/js/ListBooking.js" type="text/javascript"></script>
</body>
</html>

