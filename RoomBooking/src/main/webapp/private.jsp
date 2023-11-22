
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
    </head>
    <body>
        <%
            if (session.getAttribute("ACCOUNT_USER") == null) {
                response.sendRedirect("home.jsp");
            }
        %>
        <%@include file="viewerComponent/header.jsp" %> 

        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <div class="title d-flex">
                            <button id="goBackButton" class="btn btn-dark my-auto">Back</button>
                            <h2 class="ml-5">Private</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="mt-3 col-md-12 row">
            <div class="card-body col-md-6" >
                <h4 class="text-center">Booking</h4>
                <table id="tableListHistory" class="table table-bordered table-hover text-center">
                    <thead>
                        <tr>
                            <th>Room</th>
                            <th>Booking Date</th>
                            <th>Cancel</th>
                            <th>More</th>
                        </tr>
                    </thead>
                    <tbody id="historyTableBody">

                    </tbody>
                </table>
            </div>

            <div class="card-body col-md-6">
                <h4 class="text-center">Report</h4>
                <table id="tableListReport" class="table table-bordered table-hover text-center">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Subject</th>
                            <th>Time</th>
                            <th>Reply</th>
                        </tr>
                    </thead>
                    <tbody id="reportTableBody">

                    </tbody>
                </table>
            </div>
        </div>

        <div class="container-lg mt-3">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <div class="card">
                        <div id="title_chat_box" class="card-header title text-center">
                            Chat Box
                        </div>
                        <div class="card-body" id="chat-box" style="max-height: 400px; overflow: auto">
                            
                        </div>
                        <div class="card-footer">
                            <div class="input-group">
                                <input type="text" id="message-input" class="form-control" placeholder="Type a message..." readonly>
                                <div class="input-group-append">
                                    <button id="buttonSendMessage" class="btn btn-primary" onclick="sendMessage()">Send</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

<%@include file="viewerComponent/js_link.jsp" %>
<script src="./asset/responseData/History.js"></script>
</body>
</html>
