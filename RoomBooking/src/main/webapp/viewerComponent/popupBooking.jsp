<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="card m-3">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <img src="./asset/images/gallery1.jpg" class="card-img" alt="...">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body d-flex align-items-start flex-column">
                            <div id="contentPopupBooking">

                            </div>
                            <div class="mt-auto p-2">
                                <c:if test="${sessionScope.ACCOUNT_USER != null}">
                                    <a href="#" class="btn btn-danger" onclick="submitForm()">Booking</a>
                                </c:if>
                                <c:if test="${sessionScope.ACCOUNT_USER == null}">
                                    <a class="btn bg-danger" style="color: white" href="javascript:void(0);" onclick="openGoogleLoginPopup()">Booking</a>
                                </c:if>

                                <a href="#" onclick="$('#contentPopupBooking').html('')" class="btn btn-secondary" data-dismiss="modal">Cancel</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>