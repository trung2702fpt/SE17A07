
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
        <style>
            .report_main{
                height: 300px;
            }
            .report_title{
                display: flex;
                justify-content: center;
                text-transform: uppercase;
            }
            .report_message input{
                width:126%;
            }
            .report_message .btn-sendfeedback {
                background-color: var(--primary-color);
                color: var(--secondary-color);
                padding: 10px;
                border-radius: 5px;
                margin-top: 10px;
            }
            .report_message .btn-sendfeedback:hover{
                background-color: #ef6312;
            }
        </style>
    </head>
    <body>
        <%@include file="viewerComponent/header.jsp" %> 
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title d-flex">
                            <a href="home.jsp" class="btn btn-dark my-auto">Back</a>
                            <h2 class="ml-5">FEEDBACK</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <section class="report_main container pt-5">
                <div class="report_title">
                    <h1>Give me feedback</h4>
                </div>
                <div class="row">
                    <div class="report_contact col-md-4">
                        <h4>Contact Information</h4>
                        <p>Address: FPT University, Ngu Hanh Son Distrist, Da Nang City </p>
                        <p>Phone:  +01 1234569540</p>
                        <p>Email: bookingroom@fpt.edu.vn</p>
                    </div>
                    <form>
                        <div class="report_message col-md-4">
                            <p>Title</p>
                            <input type="text" id="title" class="report_input_title" name="title">
                            <p>Message</p>
                            <textarea id="report" rows="3" cols="60" name="message"></textarea>
                            <c:if test="${sessionScope.ACCOUNT_USER != null}">
                                <button class="btn-sendfeedback" onclick="SendReport()" type="button">Send Feedback</button>
                            </c:if>
                            <c:if test="${sessionScope.ACCOUNT_USER == null}">
                                <a class="btn-login btn bg-success" style="color: white" href="javascript:void(0);" onclick="openGoogleLoginPopup()">Login</a>
                            </c:if>
                        </div>
                    </form>
                </div>
            </section>
        </div>
        <%@include file="viewerComponent/footer.jsp" %>
        <%@include file="viewerComponent/js_link.jsp" %>
        <script src="./asset/responseData/sendReport.js"></script>
    </body>
</html>
