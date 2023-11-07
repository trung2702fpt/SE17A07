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
            <!-- Page Wrapper -->
            <div id="wrapper">
                <!-- Sidebar -->
                <%@include file="inlcude/linkStyle/admin/SidebarAdmin.jsp" %>
                <!-- End of Sidebar -->

                <!-- Content start -->
                <div class="container-fluid">
                    <div class="container mb-4">
                        <div class="col-lg-12 mt-lg-0">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card-body">
                                        <table id="tableListRooms" class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Room</th>
                                                    <th>Number Room</th>
                                                    <th>Money</th>
                                                </tr>
                                            </thead>
                                            <tbody id="bodyTableRoom">
                                                <tr>
                                                    <td colspan="4"><h2 class='text-center'>LOADING....</h2></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content end -->

            </div>
        </div>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
        <script src="inlcude/asset/admin/js/ListRoom.js"></script>
    </body>
</html>
