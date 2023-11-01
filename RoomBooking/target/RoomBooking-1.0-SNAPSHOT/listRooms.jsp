<%@page import="model.Room"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    </head>
    <body class="main-layout">
        <%@include file="inlcude/linkStyle/header.jsp" %>
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <div class="title d-flex">
                            <!--<button id="goBackButton" class="btn btn-dark my-auto">Back</button>-->
                            <a href="home.jsp" class="btn btn-dark my-auto">Back</a>
                            <h2 class="ml-5">Rooms</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- History -->
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
        <!-- end gallery -->

        <!--  footer -->
        <%@include file="inlcude/linkStyle/footer.jsp" %>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
        <script src="inlcude/asset/responseData/ListRoom.js"></script>
    </body>
</html>