
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
                <div class="mt-3 col-md-10 row">
                    <div class="card-body col-6" style="border-right: silver solid 2px ">
                        <h4 class="text-center">Information Booked</h4>
                        <div class="pl-5 shadow p-3 mb-5 bg-white rounded">
                            <div class="form-group">
                                <label for="priceRoom">Room:</label>
                                <input type="text" class="form-control" id="id" value="201" readonly>
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Date:</label>
                                <input type="text" class="form-control" id="id" value="21/12/2023" readonly>
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Slot:</label>
                                <input type="text" class="form-control" id="id" value="3 (11PM-13PM)" readonly>
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Money:</label>
                                <input type="text" class="form-control" id="id" value="200.000 VND" readonly>
                            </div>
                            <div class="d-flex justify-content-around">
                                <button type="button" class="btn btn-success">Check</button>
                                <button type="button" class="btn btn-success">Accept</button>
                            </div>
                        </div>
                    </div>

                    <div class="card-body col-6">
                        <h4 class="text-center">Equipments</h4>
                        <table id="tableListEquipments" class="table table-bordered table-hover text-center">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                    <th>Quality</th>
                                </tr>
                            </thead>
                            <tbody id="equipmentsTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>



        </div>
    </body>
</html>

<%@include file="viewerComponent/js_link.jsp" %>
</body>
</html>

