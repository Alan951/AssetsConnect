<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html lang="es">
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

    <!--Menu-->
    <div class="navbar navbar-inverse navbar-static-top" style="margin-bottom:0px">
        <div class="container">
            <div class="navbar-header">
                <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navHeader">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href=principal.jsp>
                    <img src="images/c_0.png" alt="AssetsConnect" width="30px" heigth="30px" style="display:inline-block">
                    AssetsConnect
                </a>
            </div>
            <div class="collapse navbar-collapse" id="navHeader">
                <ul class="nav navbar-nav navbar-right">
                <%
                    String usuario = (String)session.getAttribute("usuario");
                    
                    if(usuario == null){
                        response.sendRedirect("index.html");
                    }else{
                %>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> <%=usuario%> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><span class="glyphicon glyphicon-cog icongrey" aria-hidden="true"></span>&nbsp;Información</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="nuevoArticulo.jsp"><span class="glyphicon glyphicon-folder-open iconyellow" aria-hidden="true"></span>&nbsp;Nuevo Articulo</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#" id="cerrar"><span class="glyphicon glyphicon-remove iconred"></span>&nbsp;Cerrar</a></li>
                        </ul>
                    </li>
                <%
                    }
                %>
                </ul>
            </div>
        </div>
    </div> 

    <!--Articulos-->
    <section class="articulos">
        <div class="container">
            <%
                    Cookie allCookie[] = request.getCookies();
                    Cookie articulos = null;
                    for(Cookie temp: allCookie){
                        if(temp.getName().equals("Articulos")){
                            articulos = temp;
                        }
                    }
                    
            %>
        </div>
    </section>
	<!-- JS  -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/script.js"></script>
</body>
</html>