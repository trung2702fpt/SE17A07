
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header inner -->
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
                <div class="full">
                    <div class="center-desk">
                        <div class="logo">
                            <a href="home.jsp"><img src="./asset/images/logo.webp" alt="#" /></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
                <nav class="navigation navbar navbar-expand-md navbar-dark ">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarsExample04">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link" href="home.jsp">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="listRooms.jsp">Room</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="report.jsp">Feedback</a>
                            </li>
                            <c:if test="${sessionScope.ACCOUNT_USER != null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="history.jsp">History</a>
                                </li>
                                <li style="margin-top: 2px;">
                                    <p class="bg-success p-1" style="color: white; border-radius: 2px">${sessionScope.user.getEmail()}</p>
                                </li>
                                <li class="nav-item">
                                    <a class="btn-login btn bg-success" style="color: white" href="logout">Log Out</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.ACCOUNT_USER == null}">
                                <li class="nav-item">
                                    <a class="btn-login btn bg-success" style="color: white" href="javascript:void(0);" onclick="openGoogleLoginPopup()">Login</a>	
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</div>
<div id="messageBox"></div>
<div id="confirmBox"></div>
<div id="loadingScreen"></div>
<script src="./asset/responseData/loginWithGoogle.js"></script>