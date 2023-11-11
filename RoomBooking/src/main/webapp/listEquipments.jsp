
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Equipment"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
    </head>
    <body class="main-layout">
        <%@include file="viewerComponent/header.jsp" %>
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <div class="title d-flex">
                            <!--<button id="goBackButton" class="btn btn-dark my-auto">Back</button>-->
                            <a href="home.jsp" class="btn btn-dark my-auto">Back</a>
                            <h2 class="ml-5">Equipments</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="container mb-4">
                <div class="col-lg-12 mt-lg-0">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <table id="tableListEquipments" class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Equipment</th>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="viewerComponent/footer.jsp" %>
        <%@include file="viewerComponent/js_link.jsp" %>
        <script src="./asset/responseData/ListEquipment.js"></script>
    </body>
</html>
