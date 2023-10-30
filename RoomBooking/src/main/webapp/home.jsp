<%@page import="model.Room"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
    </head>
    <body class="main-layout">
        <%@include file="inlcude/linkStyle/header.jsp" %>
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title">
                            <h2>Welcome to FPT booking class room system</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="container pt-5">
            <div class="row justify-content-around align-items-center">
                <div class="card containerOverlay" style="width: 18rem;">
                    <img class="card-img-top imageOverlay" src="inlcude/asset/images/equipmentItem.png" alt="Card image cap">
                    <div class="middleOverlay">
                        <div class="textOverlay"><a href="listEquipments.jsp">Equipment</a></div>
                    </div>
                </div>  
                <div class="card containerOverlay" style="width: 18rem;">
                    <img class="card-img-top imageOverlay" src="inlcude/asset/images/bgitems.png" alt="Card image cap">
                    <div class="middleOverlay">
                        <div class="textOverlay"><a href="listRooms.jsp">List Room</a></div>
                    </div>
                </div> 
            </div>    
            <div class="row justify-content-around align-items-center pt-5">
                <div class="card containerOverlay" style="width: 18rem;">
                    <img class="card-img-top imageOverlay w-100 h-100" src="inlcude/asset/images/listroomItem.png" alt="Card image cap">
                    <div class="middleOverlay">
                        <div class="textOverlay"><a href="searching.jsp">Booking Room</a></div>
                    </div>
                </div>  
                <div class="card containerOverlay" style="width: 18rem;">
                    <img class="card-img-top imageOverlay w-100 h-100" src="inlcude/asset/images/reportsItems.png" alt="Card image cap">
                    <div class="middleOverlay">
                        <div class="textOverlay"><a href="report.jsp">Report</a></div>
                    </div>
                </div> 
            </div>  
        </div>

        <script type="text/javascript">
            var newURL = "home.jsp";
            history.pushState({}, '', newURL);
        </script>
        <%@include file="inlcude/linkStyle/footer.jsp" %>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
    </body>
</html>