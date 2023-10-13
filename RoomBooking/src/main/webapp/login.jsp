<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
    </head>
    <body>

        <%@include file="inlcude/linkStyle/header.jsp" %>

        <section class=" container">
            <div class="login_main row">
                <div class="login_body col-md-4">
                    <h1>LOGIN</h1>
                    <span>${requestScope.ErrorMessage}</span>
                    <form>
                        <div clas="btn-control" style="padding: 20px 0;">
                            <button class="btn-login">
                                <i class="fa fa-google-plus" aria-hidden="true"></i>
                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/RoomBooking/Login&response_type=code&client_id=89306432316-rs2mce69g2ta88splene875bpvihem12.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>	
                            </button>
                        </div>

                        <div clas="btn-control">
                            <button class="btn-login">
                                <i class="fa fa-envelope" aria-hidden="true"></i>
                                Login with FEID
                            </button>
                        </div>
                        <p>With user from K19 login with FEID</p>
                        <img src="inlcude/asset/images/logo.webp" alt="#" />
                    </form>

                </div>
            </div>
        </section>
        <%@include file="inlcude/linkStyle/footer.jsp" %>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
    </body>
</html>
