<%-- 
    Document   : principal
    Created on : 30/09/2015, 01:19:06 PM
    Author     : fernando espino iracheta 1446729
    Description: Página con contenido de busqueda de todos los articulos registrados del usuario.
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
                <a class="navbar-brand" href="#" id="principal">
                    <img src="images/c_0.png" alt="AssetsConnect" width="30px" heigth="30px" style="display:inline-block">
                    AssetsConnect
                </a>
            </div>
            <div class="collapse navbar-collapse" id="navHeader">
                <form action="ArticuloServlet" method="POST" class="navbar-form navbar-left"  id="busqueda" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Buscar" name="buscador">
                    </div>
                    <button type="submit" class="btn btn-group-sm">Buscar</button>
                    <input hidden name="accion3" value="buscar"/>
                </form>
                <ul class="nav navbar-nav navbar-right">
                <%
                    String usuario = (String)session.getAttribute("usuario");
                    String articulos = "";
                    
                    if(usuario == null){
                        response.sendRedirect("index.html");
                    }else{
                %>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> <%=usuario%> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="infoUsuario.jsp"><span class="glyphicon glyphicon-cog icongrey" aria-hidden="true"></span>&nbsp;Información</a></li>
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
                if(usuario != null){
                    articulos = (String)session.getAttribute("articulos");
                }
            %>
            <div class="row"></div>
        </div>
    </section>
	<!-- JS  -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script>
            $(document).ready(function () {
                var articulos = <%=articulos%>;
                var id,titulo,categoria,idcat,descripcion,url_imagen,usuario;
                $.each(articulos,function(index,value){
                    $.each(value,function(index,val){
                        if(index == "idArticulo")
                            id = val;
                        if(index == "titulo")
                            titulo = val;
                        if(index == "categoria")
                            categoria = val;
                        if(index == "idcategoria")
                            idcat = val;
                        if(index == "descripcion")
                            descripcion = val;
                        if(index == "url_imagen")
                            url_imagen = val;
                        if(index == "usuario")
                            usuario = val;
                    });
                    $('.row').append('  <div class="col-sm-6 col-md-4">'+
                                            '<div class="thumbnail">'+
                                                '<img class="url_img_'+id+'" src="'+url_imagen+'" alt="'+titulo+'">'+
                                                '<div class="caption">'+
                                                    '<h3 class="titulo_'+id+'">'+titulo+'</h3>'+
                                                    '<h6>ID: '+id+'</h6>'+
                                                    '<p><span>Categoría: '+categoria+'</span></p>'+
                                                    '<input hidden class="idcategoria_'+id+'" value="'+idcat+'">'+
                                                    '<p><h5>Descripción:</h5><pre class="descripcion_'+id+'">'+descripcion+'</pre></p>'+
                                                    '<p><small><b>Autor: '+usuario+'</b></small></p>'+
                                                    '<p style="text-align:right;"><a href="#" class="btn btn-primary editarArt" role="button" value="'+id+'">Editar</a></p>'+
                                                '</div>'+
                                            '</div>'+
                                        '</div>');
                });
            });
        </script>
        <script src="js/script.js"></script>
</body>
</html>
