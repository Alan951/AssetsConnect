<%-- 
    Document   : login.jsp
    Created on : 19/09/2015, 12:47:47 AM
    Author     : fernandoei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <% 
            String errorUsuario = request.getParameter("errorUsuario");
            String errorPass = request.getParameter("errorPass");
        %>
        
        <h1>Introduzca los datos huevooooon</h1>
        <form action="LoginServlet">
            Usuario: <input type="text" name="usuario">
            <%if(errorUsuario != null){%>
            <span><%=errorUsuario%></span>
            <%}%>
            <br><br>
            Password: <input type="password" name="password">
            <%if(errorPass != null){%>
            <span><%=errorPass%></span>
            <%}%>
            <br><br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
