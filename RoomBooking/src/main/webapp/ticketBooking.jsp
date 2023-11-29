
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
                                <label for="User">User:</label>
                                <input type="text" class="form-control" id="User" value="..." disabled="true">
                            </div>
                            <div class="form-group">
                                <label for="idRoom">Room:</label>
                                <input type="text" class="form-control" id="idRoom" value="..." disabled="true">
                            </div>
                            <div class="form-group">
                                <label for="dateBooking">Date:</label>
                                <input type="date" class="form-control" id="dateBooking" onchange="setDisableAccept()" />
                            </div>
                            <div class="form-group">
                                <label for="slot">Slot:</label>
                                <select class="form-select" id="slot" onchange="setDisableAccept()">
                                    <option value="1">Slot 1 (7-9 AM)</option>
                                    <option value="2">Slot 2 (9-11 AM)</option>
                                    <option value="3">Slot 3 (11 AM - 1 PM)</option>
                                    <option value="4">Slot 4 (1-3 PM)</option>
                                    <option value="5">Slot 5 (3-5 PM)</option>
                                    <option value="6">Slot 6 (5-7 PM)</option>
                                    <option value="7">Slot 7 (7-9 PM)</option>
                                </select>
                            </div>
                            <div class="form-group">
                                Money: <span id="amount" ></span>.000 VND
                            </div>
                            <div class="d-flex justify-content-around">
                                <input id="btnCheckToUpdate" type="button" class="btn btn-success" onclick="checkForUpdate()" value="Check"/>
                                <input id="btnAccept" type="button" class="btn btn-success" disabled="true" value="Accept">
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
<script src="./asset/admin/js/ticketBooking.js"></script>
</body>
</html>

