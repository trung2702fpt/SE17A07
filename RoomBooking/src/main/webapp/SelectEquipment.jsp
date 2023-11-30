<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
        <link rel="stylesheet" href="./asset/css/SelectEquiment.css"/>
    </head>
    <body class="main-layout">
        <%@include file="viewerComponent/header.jsp" %>
        <c:if test="${!requestScope.isSelectDate}">
            <%response.sendRedirect("erroe500.jsp");%>
        </c:if>
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title">
                            <h2>Room ${requestScope.idRoom}</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-3">
            <div id="myCarousel" class="slide col-4" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="./asset/images/listroomItem.png" alt="Slide 1">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="./asset/images/equipmentItem.png" alt="Slide 2">
                    </div>
                    <!-- Thêm các slide khác nếu cần -->
                </div>
                <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <div class="col-7 row">
                <div class="col-12 row mb-5">
                    <div class="col-md-4">
                        <div class="title">
                            <h2>Room: ${requestScope.idRoom}</h2>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="title">
                            <h2>Date: ${requestScope.date}</h2>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="title">
                            <h2>Slot: ${requestScope.slotRequest} (${requestScope.slot})</h2>
                        </div>
                    </div>
                </div>
                <div class="col-12">
                    <table id="tableListEquipments" class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th class="">Name</th>
                                <th class="">Description</th>
                                <th class="">Money</th>
                                <th class="">Type</th>
                            </tr>
                        </thead>
                        <tbody id="bodyTableEquipments">
                            <tr>
                                <td colspan="6"><h2 class='text-center'>LOADING....</h2></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <button class="btn btn-primary btn-success" onclick="sendRequest()">Send Request</button>   
            </div>
        </div>
        <%@include file="viewerComponent/js_link.jsp" %>
        <script src="./asset/responseData/SelectEquipment.js"></script>
    </body>
</html>
