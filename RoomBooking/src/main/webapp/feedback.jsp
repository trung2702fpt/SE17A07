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
                            <div class="form-group container">
                                <label for="priceRoom">Content: </label>
                                <textarea type="text" class="form-control" rows="10" id="content" value="" readonly></textarea>
                            </div>
                            <div class="form-group container">
                                <label for="priceRoom">Reply:</label>
                                <textarea type="text" class="form-control" rows="10" id="reply" value=""></textarea>
                            </div>

                            <button type="button" class="btn btn-success" onclick="RepReport()">Send</button>
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
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
        <script src="inlcude/asset/admin/js/feedback.js"></script>
    </body>
</html>
