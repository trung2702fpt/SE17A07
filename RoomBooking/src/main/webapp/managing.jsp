
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if(session.getAttribute("isAdmin") == null){
                response.sendRedirect("admin.jsp");
            }
        %>
        <h1>ADMIN PAGE</h1>
    </body>
</html>
