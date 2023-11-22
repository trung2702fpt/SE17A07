
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
    </head>
    <body>
        <%
//            if (session.getAttribute("ACCOUNT_USER") == null) {
//                response.sendRedirect("home.jsp");
//            }
%>
        <%@include file="viewerComponent/header.jsp" %> 

        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">

                        <div class="title d-flex">
                            <button id="goBackButton" class="btn btn-dark my-auto">Back</button>
                            <h2 class="ml-5">Detail <span id="RoomTitle"></span></h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="mt-3 col-md-12 row">
            <div class="card-body col-6" style="border-right: silver solid 2px ">
                <h4 class="text-center">Information Booked</h4>

            </div>

            <div class="card-body col-6">
                <h4 class="text-center">Equipments</h4>
                <table id="tableListEquipments" class="table table-bordered table-hover text-center">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quality</th>
                        </tr>
                    </thead>
                    <tbody id="equipmentsTableBody">
                        
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>


<%@include file="viewerComponent/footer.jsp" %>
<%@include file="viewerComponent/js_link.jsp" %>
<script src="./asset/responseData/detailBooked.js" type="text/javascript"></script>
</body>
</html>
