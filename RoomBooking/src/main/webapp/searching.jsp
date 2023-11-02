
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
        
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous" />
        <script
            src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="inlcude/linkStyle/header.jsp" %> 

        <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
            aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="card m-3">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img
                                    src="https://www.atriumhotelandsuites.com/gallery-images/properties/7/6/1/1680846167_guestroom-mob_2.jpg"
                                    class="card-img" alt="...">
                            </div>
                            <div class="col-md-8">
                                <div
                                    class="card-body d-flex align-items-start flex-column">
                                    <div class="p-2"><h2 class="card-title">Room
                                            111</h2></div>
                                    <div class="p-2"><p class="card-text">Date:
                                            <input type="date"></p></div>
                                    <div class="p-2"><p class="card-text">
                                            Slot:
                                            <select name="cars" id="cars">
                                                <option value="volvo">Volvo</option>
                                                <option value="saab">Saab</option>
                                                <option value="mercedes">Mercedes</option>
                                                <option value="audi">Audi</option>
                                            </select>
                                        </p></div>
                                    <div class="mt-auto p-2"><div>
                                            <button class="btn btn btn-danger">Booking</button>
                                            <button class="btn btn btn-secondary">Cancel</button>
                                        </div></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="back_re">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="title d-flex">
                            <!--<button id="goBackButton" class="btn btn-dark my-auto">Back</button>-->
                            <a href="home.jsp" class="btn btn-dark my-auto">Back</a>
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
                    <div class="card-body">
                        <table id="tableListSearchrooms" class="table table-bordered table-hover">
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
