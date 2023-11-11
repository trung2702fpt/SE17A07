<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="viewerComponent/head_link.jsp" %>
        
    </head>
    <body>
        <div id="notfound">
		<div class="notfound-bg"></div>
		<div class="notfound">
			<div class="notfound-404">
                            <h1>OOps!</h1>
			</div>
			<h2>Internal Server Error</h2>
			<div class="bg-dark">
                            <span>${requestScope.MESSAGE_ERROR}</span>
			</div>
		</div>
	</div>
    </body>
</html>


