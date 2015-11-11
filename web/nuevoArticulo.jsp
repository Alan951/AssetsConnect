<%-- 
    Document   : nuevoArticulo
    Created on : 30/09/2015, 01:19:06 PM
    Author     : fernando espino iracheta 1446729
    Description: Formulario con validaciones correctas para dar de alta a un nuevo artículo.
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
                <a class="navbar-brand" href=principal.jsp>
                    <img src="images/c_0.png" alt="AssetsConnect" width="30px" heigth="30px" style="display:inline-block">
                    AssetsConnect
                </a>
            </div>
            <div class="collapse navbar-collapse" id="navHeader">
                <ul class="nav navbar-nav navbar-right">
                <%
                    String usuario = (String)session.getAttribute("usuario");
                    String error = request.getParameter("error");
                    String titulo = "";
                    String descripcion = "";
                    int categoria = 0;
                    String url_imagen = "";
                            
                    if(usuario == null){
                        response.sendRedirect("index.html");
                    }else{
                        
                        if(error != null){
                            titulo = (String)session.getAttribute("titulo");
                            descripcion = (String)session.getAttribute("descripcion");
                            categoria = (Integer)session.getAttribute("categoria");
                            url_imagen = (String)session.getAttribute("url_imagen");
                        }
                %>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> <%=usuario%> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><span class="glyphicon glyphicon-cog icongrey" aria-hidden="true"></span>&nbsp;Información</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="registrarArticulo.jsp"><span class="glyphicon glyphicon-folder-open iconyellow" aria-hidden="true"></span>&nbsp;Nuevo Articulo</a></li>
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

    <!--Nuevo Articulo-->
    <div class="container">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="newArticulo">
            <form action="ArticuloServlet" method="POST" class="form-signin" id="formArticulo" role="formArticulo">
                <%if(error != null){
                    error = "El id del articulo ya ha sido registrado";
                %>
                <span><%=error%></span>
                <%}%>
                <input type="text" name="clave" id="Clave" class="form-control" placeholder="Clave">
                <input type="text" name="titulo" id="titulo" class="form-control" placeholder="Titulo" value="<%=titulo%>">
                <textarea class="form-control" rows="5" name="descripcion" id="descripcion" placeholder="Descripción"><%=descripcion%></textarea> 
                <select class="form-control" name="categoria" id="categoria">
                    <option value="0">Categoria</option>
                    <%
                        Cookie allCookie[] = request.getCookies();
                        String categorias = null;
                        for(Cookie temp: allCookie){
                            if(temp.getName().equals("Categorias")){
                                categorias = temp.getValue();
                            }
                        }
                    %>
                </select>
                <input type="text" name="url" id="url" class="form-control" placeholder="URL" value="<%=url_imagen%>">
                <button class="btn btn-lg btn-success btn-block" type="submit">Guardar</button>
                <input hidden name="accion" value="newArticulo"/>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
	<!-- JS  -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/jquery.validate.js"></script>
        <script src="js/script.js"></script>
        <script>
            $(document).ready(function () {
                var categorias = <%=categorias%>;
                var id,name;
                $.each(categorias,function(index,value){
                    $.each(value,function(index,val){
                        if(index == "idCategoria")
                            id = val;
                        if(index == "categoria")
                            name = val;
                    });
                    $('#categoria').append('<option value="'+id+'">'+name+'</option>');
                });
                
                $('#categoria').val(<%=categoria%>);
            });
        </script>
</body>
</html>