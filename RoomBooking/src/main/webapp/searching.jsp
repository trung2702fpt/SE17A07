
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <h2 class="ml-5">Search room</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="mt-3 d-flex justify-content-center">
                    <div class="input-group w-50">
                        <input type="date" class="form-control" id="dateSelected" onchange="setSlotByDate()" />
                    </div>
                    <div class="input-group w-50 ml-5 mr-5">
                        <select class="form-select" id="slotSelected">
                            <option value="1">Slot 1 (7-9 AM)</option>
                            <option value="2">Slot 2 (9-11 AM)</option>
                            <option value="3">Slot 3 (11 AM - 1 PM)</option>
                            <option value="4">Slot 4 (1-3 PM)</option>
                            <option value="5">Slot 5 (3-5 PM)</option>
                        </select>
                    </div>
                    <a href="#" class="btn btn-dark my-auto" onclick="searchByName(); return false;">Search</a>
                </div>

                <div class="mt-3 col-md-12">
                    <div class="user-dashboard-info-box table-responsive mb-0 bg-white p-4 shadow-sm">
                        <table class="table manage-candidates-top mb-0">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Room</th>
                                    <th>Number Room</th>
                                    <th>Money</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody id="contentSearchroom">
                                <tr>
                                    <td colspan="5"><h2 class='text-center'>EMPTY....</h2></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="inlcude/linkStyle/footer.jsp" %>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
        <script type="text/javascript" src="inlcude/asset/responseData/searchRoom.js"></script>
    </body>
</html>