
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
    </head>
    <body>
        <%@include file="inlcude/linkStyle/header.jsp" %> 

        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        
                        <div class="title d-flex">
                            <button id="goBackButton" class="btn btn-dark my-auto">Back</button>
                            <h2 class="ml-5">History</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div  class="our_room">
            <div class="container-fluid">
                <div class="container mt-3 mb-4">
                    <div class="col-lg-9 mt-4 mt-lg-0">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                                    <table border="1" class="table manage-candidates-top mb-0">
                                        <thead>
                                            <tr>
                                                <th>Room ID</th>
                                                <th>Booking Date</th>
                                                <th>Slot</th>
                                                <th>Cancel</th>
                                            </tr>
                                        </thead>
                                        <tbody id="historyTableBody">

                                        </tbody>
                                    </table>
                                    <div class="text-center mt-3 mt-sm-3">
                                        <ul class="pagination justify-content-center mb-0">
                                            <li class="page-item disabled"> <span class="page-link">Prev</span> </li>
                                            <li class="page-item active" aria-current="page"><span class="page-link">1 </span> <span class="sr-only">(current)</span></li>
                                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                                            <li class="page-item"><a class="page-link" href="#">...</a></li>
                                            <li class="page-item"><a class="page-link" href="#">25</a></li>
                                            <li class="page-item"> <a class="page-link" href="#">Next</a> </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


<%@include file="inlcude/linkStyle/footer.jsp" %>
<%@include file="inlcude/linkStyle/js_link.jsp" %>
<script src="inlcude/asset/responseData/History.js"></script>
</body>
</html>
