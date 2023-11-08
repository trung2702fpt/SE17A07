<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
        <link href="inlcude\asset\vendor\fontawesome-free\css\all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link href="inlcude/asset/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <%
            if (session.getAttribute("isAdmin") == null) {
                response.sendRedirect("admin.jsp");
            }
        %>

        <div id="page-top">

            <div id="wrapper">
                <%@include file="inlcude/linkStyle/admin/SidebarAdmin.jsp" %>
                <div class="row container mt-5">
                    <div class="col-md-4 container">
                        <h2>Edit Room</h2>
                        <form>
                            <div class="form-group">
                                <label for="roomSearch">Room:</label>
                                <input type="text" class="form-control" id="roomSearch">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="Search()">Search</button>
                        </form>

                        <div>
                            <div class="form-group">
                                <label for="priceRoom">Id Room:</label>
                                <input type="text" class="form-control" id="idRoom" value="" readonly>
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Number Room:</label>
                                <input type="text" class="form-control" id="numberRoom" value="" readonly>
                            </div>
                            <div class="form-group">
                                <label for="priceRoom">Price:</label>
                                <input type="text" class="form-control" id="priceRoom" value="">
                            </div>
                            <button type="button" class="btn btn-success" onclick="Update()">Update</button>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <img src="./inlcude/asset/images/gallery1.jpg" alt="alt" class="img-fluid w-100">
                    </div>
                </div>
                <!-- Content end -->
            </div>
        </div>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
        <script type="text/javascript">
        </script>
        <script src="inlcude/asset/admin/js/EditRoom.js"></script>
    </body>
</html>