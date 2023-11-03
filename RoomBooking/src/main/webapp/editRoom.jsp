<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="inlcude/linkStyle/head_link.jsp" %>
        <link href="inlcude\asset\vendor\fontawesome-free\css\all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link href="inlcude/asset/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <%
            if (session.getAttribute("isAdmin") == null) {
                response.sendRedirect("admin.jsp");
            }
        %>
        <div id="page-top">
            <div id="wrapper">
                <%@include file="inlcude/linkStyle/admin/SidebarAdmin.jsp" %>
                <!-- Content start -->
                
                <!-- Content end -->
            </div>
        </div>
        <%@include file="inlcude/linkStyle/js_link.jsp" %>
        <script type="text/javascript">
            var newURL = "managing.jsp";
            history.pushState({}, '', newURL);
        </script>
        <script src="inlcude/asset/admin/js/EditRoom.js"></script>
    </body>
</html>
