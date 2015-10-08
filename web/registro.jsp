<%-- 
    Document   : registro
    Created on : 30/09/2015, 01:19:06 PM
    Author     : fernandoei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Basic Page Needs
    ================================================== -->
    <meta charset="UTF-8">
    <title>AssetsConnect</title>
    <meta name="description" content="Creamos y actualizamos paginas web.">
    <meta name="author" content="AssetsConnect">
    <meta name="keywords" content="software, app, development, app development, developers, desarrollo de software, desarrollo de apps, desarrollo de sistemas, diseño de apps, ux, user interface, diseño y código, diseño de información">

    <!-- Fonts
    ================================================== -->
    <!--link href="http://fonts.googleapis.com/css?family=Lato:900" rel="stylesheet" type="text/css"-->
	
    <!-- Mobile Specific Metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	
    <!-- Favicon
    ================================================== -->
    <!--link rel="shortcut icon" type="image/x-icon" href="http://127.0.0.1/Pyme/images/favicon.ico"-->

    <!-- CSS
    ================================================== -->
    <link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/style.css">

    <!--script type="text/javascript" async="" src="http://www.google-analytics.com/ga.js"></script-->
</head>
<body>

    <% 
        String errorUsuario = request.getParameter("errorUsuario");
        String errorPass = request.getParameter("errorPass");
    %>
    <!--Menu-->
    <div class="navbar navbar-inverse navbar-static-top" style="margin-bottom:0px">
        <div class="container">
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navHeader">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">
                    <img src="images/c_0.png" alt="AssetsConnect" width="30px" heigth="30px" style="display:inline-block">
                    AssetsConnect
                </a>
            </div>
            <div class="collapse navbar-collapse" id="navHeader">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="registro.jsp">Registro</a></li>
                </ul>
            </div>
        </div>
    </div> 

    <!--Login-->
    <div class="container">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="register">
            <form action="RegistroServlet" method="POST" class="form-signin" role="form">
                <div class="text-center">
                    <img id="avatar" src="./images/nadie.png" alt="avatar">
                </div>
                <input type="text" name="name" id="name" class="form-control" placeholder="Name">
                <input type="text" name="usuario" id="usuario" class="form-control" placeholder="Usuario">
                <%if(errorUsuario != null){%>
                <span><%=errorUsuario%></span>
                <%}%>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password">
                <%if(errorPass != null){%>
                <span><%=errorPass%></span>
                <%}%>
                <input type="password" name="password2" id="password2" class="form-control" placeholder="Repetir Password">
                <button class="btn btn-lg btn-success btn-block" type="submit">Registrar</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>

    <!-- JS  -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/jquery.md5.min.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>

</body>
</html>
