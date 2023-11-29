<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
        <link href="./asset/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link href="./asset/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <%
            if (session.getAttribute("isAdmin") == null) {
                response.sendRedirect("admin.jsp");
            }
        %>

        <div id="page-top">

            <div id="wrapper">
                <%@include file="viewerComponent/admin/SidebarAdmin.jsp" %>
                <div class="row container mt-5">
                    <div class="col-md-4 container">
                        <h2>Edit Equipment</h2>
                        <div>
                            <div class="form-group">
                                <label for="priceRoom">Id Equipment:</label>
                                <input type="text" class="form-control" id="id" value="" readonly>
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Name:</label>
                                <input type="text" class="form-control" id="name" value="">
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Description: </label>
                                <input type="text" class="form-control" id="des" value="" >
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Price:</label>
                                <input type="text" class="form-control" id="price" value="">
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Type:</label>
                                <select id="typeEquipment" class="form-select">
                                    <option value="">text</option>
                                </select>
                            </div>
                            <button type="button" class="btn btn-success" onclick="Update()">Update</button>
                            <button type="button" class="btn btn-dark ml-5" onclick="addNew()">Add New</button>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="container-fluid">
                            <div class="container mb-4">
                                <div class="col-lg-12 mt-lg-0">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card-body">
                                                <table id="tableListEquipments" class="table table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Equipment</th>
                                                            <th class="">Name</th>
                                                            <th class="">Description</th>
                                                            <th class="">Money</th>
                                                            <th class="">Type</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody id="bodyTableEquipments">
                                                        <tr>
                                                            <td colspan="6"><h2 class='text-center'>LOADING....</h2></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content end -->
            </div>
        </div>
        <div id="messageBox"></div>
        <div id="confirmBox"></div>
        <div id="loadingScreen"></div>
        <%@include file="viewerComponent/js_link.jsp" %>
        <script type="text/javascript">
        </script>
        <script src="./asset/admin/js/EditEquipment.js"></script>
    </body>
</html>
