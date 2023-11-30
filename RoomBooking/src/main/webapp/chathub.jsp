
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
        <link rel="stylesheet" href="asset/vendor/fontawesome-free/css/all.min.css">
        <style>
            body{background-color: #ececec;}
            </style>
    </head>
    <body>
        <%
            if (session.getAttribute("ACCOUNT_USER") == null) {
                response.sendRedirect("home.jsp");
            }
        %>
        <%@include file="viewerComponent/header.jsp" %> 

        <div class="container border d-flex justify-content-center flex-column shadow mb-5 bg-white rounded mt-5 p-0">
            <div class="header row shadow-none p-3 bg-light rounded w-100 m-0">
                <i class="h4 fa-solid fa-chalkboard-user pt-2"></i>
                <h4 id="title_chat_box" class="col-md-2 my-auto">Chat Box</h4>
            </div>

            <div id="chat-box" class="row card-body p-0 mr-0" style="max-height: 50vh; overflow: auto;">
                
            </div>

            <div class="shadow-none p-4 bg-light rounded">
                <div class="input-group">
                    <input id="message-input" type="text" readonly class="form-control" placeholder="Enter your messages" aria-label="Recipient's username" aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="button" onclick="sendMessage()" id="buttonSendMessage">
                        <i class="fa-solid fa-paper-plane"></i>
                    </button>
                </div>
            </div>
        </div>
    </body>
</html>

<%@include file="viewerComponent/js_link.jsp" %>
<script src="./asset/responseData/chat.js"></script>
</body>
</html>
