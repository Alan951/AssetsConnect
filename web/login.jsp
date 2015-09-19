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
        <h1>Introduzca los datos huevooooon</h1>
        <form action="LoginServlet.java">
            Usuario: <input type="text" name="usuario">
            <br><br>
            Password: <input type="password" name="password">
            <br><br>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
