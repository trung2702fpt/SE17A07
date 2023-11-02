

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>

    </head>
    <body>
        <%@include file="inlcude/linkStyle/header.jsp" %> 

         <!-- Large modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal"
            data-target=".bd-example-modal-lg">Large modal</button>

        <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
            aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="card m-3">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="https://www.atriumhotelandsuites.com/gallery-images/properties/7/6/1/1680846167_guestroom-mob_2.jpg" class="card-img" alt="...">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">Card title</h5>
                                    <p class="card-text">This is a wider card
                                        with supporting text below as a natural
                                        lead-in to additional content. This
                                        content is a little bit longer.</p>
                                    <p class="card-text"><small
                                            class="text-muted">Last updated 3
                                            mins ago</small></p>
                                </div>
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
