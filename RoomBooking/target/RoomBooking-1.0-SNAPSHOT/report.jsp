
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
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
        <%@include file="inlcude/linkStyle/header.jsp" %> 
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title d-flex">
                            <!--<button id="goBackButton" class="btn btn-dark my-auto">Back</button>-->
                            <a href="home.jsp" class="btn btn-dark my-auto">Back</a>
                            <h2 class="ml-5">Report</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <section class="report_main container">
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
                        <div class="report_message col-md-4">
                            <p>Email</p>
                            <input type="text" class="report_input_title">
                            <p>Title</p>
                            <input type="text" class="report_input_title">
                            <p>Message</p>
                            <textarea id="comment" rows="3" cols="60">
                            </textarea>
                            <button class="btn-sendfeedback">Send Feedback</button>
                        </div>
                    </div>
                </section>
        </div>
        <%@include file="inlcude/linkStyle/footer.jsp" %>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
    </body>
</html>
