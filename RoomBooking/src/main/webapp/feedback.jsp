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
                <div class="row container w-100 mt-5">
                    <div class="col-md-12">
                        <h2>User's Feedback</h2>
                        <div class="container">
                            <div class="row container">
                                <div class="form-group col-md-2">
                                    <label for="priceRoom">Id:</label>
                                    <input type="text" class="form-control" id="id" value="" readonly>
                                </div>
                                <div class="form-group col-md-7">
                                    <label for="priceRoom">Title:</label>
                                    <input type="text" class="form-control" id="title" value="" readonly>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="priceRoom">User:</label>
                                    <input type="text" class="form-control" id="userEmail" readonly>
                                </div>
                            </div>
                            <div class="col-md-8 offset-md-2">
                                <div class="card">
                                    <div class="card-body" id="chat-box" style="max-height: 500px; overflow: auto">

                                    </div>
                                    <div class="card-footer">
                                        <div class="input-group">
                                            <input type="text" id="message-input" class="form-control" placeholder="Type a message..." readonly>
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" onclick="sendMessage()">Send</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Content end -->
                <div class="col-md-4">
                    <div class="card-body">
                        <h4 class="text-center">Report</h4>
                        <table id="tableListReport" class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Time</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody id="reportTableBody">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="viewerComponent/js_link.jsp" %>
        <script src="./asset/admin/js/feedback.js"></script>
    </body>
</html>
