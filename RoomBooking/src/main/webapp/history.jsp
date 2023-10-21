
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
    </head>
    <body>
        <%@include file="inlcude/linkStyle/header.jsp" %> 

        <!DOCTYPE html>
    <html>
        <head>
            <title>Booking History</title>
        </head>
        <body>



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
                                                    <th>Slot ID</th>
                                                    <th>Price</th>
                                                </tr>
                                            </thead>
                                            <tbody id="historyTableBody">
                                                <!-- Dữ liệu sẽ được thêm vào đây bằng JavaScript -->
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


            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script>
                // Function to format a date string into a human-readable format
                function formatDate(dateString) {
                    var options = {year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit'};
                    return new Date(dateString).toLocaleString(undefined, options);
                }

                $(document).ready(function () {
                    $.ajax({
                        url: "GetHistory", 
                        method: "GET",
                        dataType: "json",
                        success: function (data) {
                            // Xử lý dữ liệu JSON và hiển thị nó trong bảng
                            var historyTable = $("#historyTableBody");

                            $.each(data, function (index, history) {
                                var formattedDate = formatDate(history.bookingDate); 
                                historyTable.append(
                                        "<tr>" +
                                        "<td>" + history.roomID + "</td>" +
                                        "<td>" + formattedDate + "</td>" + 
                                        "<td>" + history.slotID + "</td>" +
                                        "<td>" + history.price + "</td>" +
                                        "</tr>"
                                        );
                            });
                        },
                        error: function () {
                            console.log("Lỗi khi lấy dữ liệu từ servlet.");
                        }
                    });
                });
            </script>
        </body>
    </html>


    <%@include file="inlcude/linkStyle/footer.jsp" %>
    <%@include file="inlcude/linkStyle/js_link.jsp" %>
</body>
</html>
