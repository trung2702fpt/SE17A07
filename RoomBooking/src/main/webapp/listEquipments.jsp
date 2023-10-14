
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Equipment"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
    </head>
    <body class="main-layout">
        <%
            List<Equipment> rooms = (List<Equipment>) session.getAttribute("ListEquipments");
            if (rooms == null) {
                response.sendRedirect("GetEquipments");
            }
        %>
        <%@include file="inlcude/linkStyle/header.jsp" %>
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title">
                            <h2>Equipments</h2>
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
                            <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                                <table class="table manage-candidates-top mb-0">
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
                                    <tbody>
                                    <c:forEach items="${sessionScope.ListEquipments}" var="equipment">
                                        <tr class="candidates-list">
                                            <td>${equipment.id}</td>
                                            <td class="title">
                                                <div class="thumb">
                                                    <img class="img-fluid w-25" src="inlcude/asset/images/equipment/${equipment.type.name}.png" alt="">
                                                </div>
                                            </td>
                                            <td>${equipment.name}</td>
                                            <td>${equipment.des}</td>
                                            <td>${equipment.price}</td>
                                            <td>${equipment.type.name}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <!--                                            <div class="text-center mt-3 mt-sm-3">
                                                                                <ul class="pagination justify-content-center mb-0">
                                                                                    <li class="page-item disabled"> <span class="page-link">Prev</span> </li>
                                                                                    <li class="page-item active" aria-current="page"><span class="page-link">1 </span> <span class="sr-only">(current)</span></li>
                                                                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                                                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                                                    <li class="page-item"><a class="page-link" href="#">...</a></li>
                                                                                    <li class="page-item"><a class="page-link" href="#">25</a></li>
                                                                                    <li class="page-item"> <a class="page-link" href="#">Next</a> </li>
                                                                                </ul>
                                                                            </div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="inlcude/linkStyle/footer.jsp" %>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
    </body>
</html>
