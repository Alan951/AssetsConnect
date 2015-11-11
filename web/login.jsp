<%-- 
    Document   : login
    Created on : 19/09/2015, 12:47:47 AM
    Author     : fernando espino iracheta 1446729
    Description: Formulario simple de usario y password con validaciones correctas para inicio de sesión.
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
        String errorLogin = request.getParameter("errorLogin");
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
                    <li><a href="registro.jsp"><span class="glyphicon glyphicon-pencil"></span> Registro</a></li>
                </ul>
            </div>
        </div>
    </div> 

    <!--Login-->
    <div class="container">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="login">
            <form action="UsuarioServlet" method="POST" class="form-signin" id="form" role="form">
                <%if(errorLogin != null){
                    errorLogin = "Usuario o Contraseña incorrecto";
                %>
                <span><%=errorLogin%></span>
                <%}%>
                <input type="text" name="usuario" id="usuario" class="form-control" placeholder="Usuario">
                <%if(errorUsuario != null){
                        errorUsuario = "No debe de contener numeros o caracteres especiales. Excepto: _ .Mayor a 4 caracteres";if(errorUsuario.equals("101")){
                    }
                %>
                <span><%=errorUsuario%></span>
                <%}%>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password">
                <%if(errorPass != null){
                    errorPass = "La contraseña debe de tener al menos 1 numero, letras mayusculas y minusculas. Mayor a 5 caracteres";
                %>
                <span><%=errorPass%></span>
                <%}%>
                <button class="btn btn-lg btn-success btn-block" type="submit">Iniciar sesión</button>
                <input hidden name="accion" value="loggin"/>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
                
    <!-- JS  -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/jquery.validate.js"></script>
    <script src="js/script.js"></script>
    <script src="js/bootstrap/bootstrap.min.js"></script>

</body>
</html>