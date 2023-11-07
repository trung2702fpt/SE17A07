
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
    </head>
    <body>
        <%
            if (session.getAttribute("ACCOUNT_USER") == null) {
                response.sendRedirect("home.jsp");
            }
        %>
        <%@include file="inlcude/linkStyle/header.jsp" %> 

        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <div class="title d-flex">
                            <button id="goBackButton" class="btn btn-dark my-auto">Back</button>
                            <h2 class="ml-5">History</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="mt-3 col-md-12 row">
            <div class="card-body col-md-6" >
                <h4 class="text-center">Booking</h4>
                <table id="tableListHistory" class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Room ID</th>
                            <th>Booking Date</th>
                            <th>Slot</th>
                            <th>Cancel</th>
                        </tr>
                    </thead>
                    <tbody id="historyTableBody">

                    </tbody>
                </table>
            </div>
            
            <div class="card-body col-md-6">
                <h4 class="text-center">Report</h4>
                <table id="tableListReport" class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Subject</th>
                            <th>Content</th>
                            <th>Time</th>
                            <th>Reply</th>
                        </tr>
                    </thead>
                    <tbody id="reportTableBody">

                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>


<%@include file="inlcude/linkStyle/footer.jsp" %>
<%@include file="inlcude/linkStyle/js_link.jsp" %>
<script src="inlcude/asset/responseData/History.js"></script>
</body>
</html>
