
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
                        <h2>Add Room</h2>
                        <div>
                            <form>
                            <div class="form-group">
                                <label for="priceRoom">Number Room:</label>
                                <input type="text" class="form-control" id="numberRoom" value="">
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Price:</label>
                                <input type="text" class="form-control" id="priceRoom" value="">
                            </div>
                            <button type="button" class="btn btn-success" onclick="Validation()">Add room</button>
                            </form>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <img src="./asset/images/gallery1.jpg" alt="alt" class="img-fluid w-100">
                    </div>
                </div>
                <!-- Content end -->
            </div>
        </div>
        <div id="messageBox"></div>
        <div id="confirmBox"></div>
        <div id="loadingScreen"></div>
        <%@include file="viewerComponent/js_link.jsp" %>
        <script src="./asset/admin/js/addNewRoom.js"></script>
    </body>
</html>