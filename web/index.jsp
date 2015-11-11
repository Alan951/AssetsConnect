<%-- 
    Document   : index
    Created on : 30/09/2015, 01:19:06 PM
    Author     : fernando espino iracheta 1446729
    Description: Página principal de la aplicación, dando intoducción de su contenido.
--%>
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
                <a class="navbar-brand" href="index.html">
                    <img src="images/c_0.png" alt="AssetsConnect" width="30px" heigth="30px" style="display:inline-block">
                    AssetsConnect
                </a>
            </div>
            <div class="collapse navbar-collapse" id="navHeader">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-user"></span> Login</a></li>
                    <li><a href="registro.jsp"><span class="glyphicon glyphicon-pencil"></span> Registro</a></li>
                </ul>
            </div>
        </div>
    </div> 

    <!--Mensaje de Bienvenida-->
    <section class="intro">
        <div class="container">
            <h1>Bienvenido</h1>
            <p>
                La principal función de esta página es contener información detalla de cualquier articulo.
                Estos articulos pueden ser, sipnosis de peliculas, libros, revistas, periodicos, etc. Para poder
                ver los articulos es neceserio registrarse. De igual manera es posible que usted pueda subir 
                articulos, siempre y cuando se ha registrado.
            </p>
        </div>
    </section>
	<!-- JS  -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>

</body>
</html>
