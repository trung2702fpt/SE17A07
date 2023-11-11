
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
        <title>ADMIN</title>
    </head>
    <body>

        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title d-flex">
                            <a href="home.jsp" class="btn btn-dark my-auto">Back to User</a>
                            <h2 class="ml-5">Admin login page</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <section class="container">
            <div class="row justify-content-center">
                <div class="col-md-4">

                </div>
            </div>
        </section>

        <section class=" container">
            <div class="login_main row">
                <div class="login_body col-md-4">
                    <div class="card-body">
                        <h1 class="card-title">LOGIN</h1>
                        <form class="form-group" action="loginAdmin" method="POST">
                            <div class="mb-3">
                                <label for="email" class="form-label">Email:</label>
                                <input type="email" class="form-control" id="email" name="email" value="${requestScope.email}" required >
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">ID:</label>
                                <input type="password" class="form-control" id="password" name="password" value="${requestScope.pass}" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Đăng nhập</button>
                        </form>
                                <span style="color: red">${requestScope.ErrorMessage}</span>
                    </div>
                    <img src="./asset/images/logo.webp" alt="#" />
                </div>
            </div>
        </section>
        <%@include file="viewerComponent/js_link.jsp" %>
    </body>
</html>
