/**
 * @author Fernando Espino Iracheta 1446729
 * @name script.js
 * @description Funciones necesarios para validaciones de formularios,
 *              y funciones con envios a servidor para cargar de datos.
 */

$(document).ready(function () {

    $("#form").validate({
        rules:{
            usuario:{
                required: true,
                usuarioValid: true
            },
            name:{
                required: true,
                nombreValid: true
            },
            password:{
                required: true,
                passwordValid :true,
                minlength:4
            },
            password2:{
                required: true,
                equalTo: "#password"
            }
        },
        messages:{
            usuario:{
                required:"No puede estar vacío"
            },
            name:{
                required:"No puede estar vacío"
            },
            password:{
                required: "Requiere contraseña",
            },
            password2:"No coinciden las contraseñas"
        }
    });
    
    jQuery.validator.addMethod("usuarioValid", function(value,element){
        return this.optional(element) || /^([a-zA-Zñáéíóú 0-9\_]{4,60})$/i.test(value);
    },"No debe de contener numeros o caracteres especiales. Excepto: _ .Mayor a 4 caracteres");
    
    jQuery.validator.addMethod("nombreValid", function(value,element){
        return this.optional(element) || /^([a-zA-Z ñáéíóú]{10,60})$/i.test(value);
    },"No debe de contener numeros o caracteres especiales. Mayor a 10 Caracteres");
    
    jQuery.validator.addMethod("passwordValid", function(value,element){
        return this.optional(element) || /^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{5,60})$/.test(value);
    },"La contraseña debe de tener al menos 1 numero, letras mayusculas y minusculas. Mayor a 5 caracteres");


    $("#formArticulo").validate({
        rules:{
            clave:{
                required: true,
                stringValid: true
            },
            titulo:{
                required: true,
                stringValid: true
            },
            descripcion:{
                required: true,
                descripcionValid: true
            },
            categoria:{
                categoriaValid: true
            },
            url:{
                required: true,
                url: true
            }
        },
        messages:{
            clave:{
                required:"No puede estar vacío"
            },
            titulo:{
                required:"No puede estar vacío"
            },
            descripcion:{
                required:"No puede estar vacío"
            },
            url:{
                required:"No puede estar vacío",
                url:"La URL esta incorrecto"
            }
        }
    });
    
    jQuery.validator.addMethod("stringValid", function(value,element){
        return this.optional(element) || /^([a-zA-Z 0-9 ñáéíóú]{1,60})$/i.test(value);
    },"Solo numeros y letras");
    
    jQuery.validator.addMethod("descripcionValid", function(value,element){
        return this.optional(element) || /^([a-zA-Z 0-9 ñáéíóú \. \, \; \( \) \"]{1,500})$/i.test(value);
    },"No puede contener caracteres especiales. Excepto: . , ; ( ) \"");
    
    jQuery.validator.addMethod("categoriaValid", function(value,element){
        return this.optional(element) || /^([1-9])$/i.test(value);
    },"Debe seleccionar una categoría");
    
    $("#cerrar").click(function(){
     cerrar(); return false;
    });
    
    function cerrar(){
        $.post("UsuarioServlet",{
            accion:"cerrar",
        }, function(responseText){
            $(location).attr('href',"index.html"); 
        });
    }
    
    $("#eliminar").click(function(){
        eliminarArticulo(); return false;
    });
    
    function eliminarArticulo(){
        var clave1 = $("#clave1").text();
        
        $.post("ArticuloServlet",{
            accion:"detallesArticulo",
            accion2:"eliminar",
            clave: clave1
        }, function(responseText){
            $(location).attr('href',"principal.jsp"); 
        });
    }
    
    $("#modificar").click(function(){
        modificarArticulo(); return false;
    });
    
    function modificarArticulo(){
        var clave1 = $("#clave1").text();
        var titulo1 = $("#titulo").val();
        var descripcion1 = $("#descripcion").val();
        var categoria1 = $("#categoria").val();
        var url1 = $("#url").val();
    
        $.post("ArticuloServlet",{
            accion:"detallesArticulo",
            accion2:"modificar",
            clave: clave1,
            titulo: titulo1,
            descripcion: descripcion1,
            categoria: categoria1,
            url: url1
        }, function(responseText){
            $(location).attr('href',"principal.jsp"); 
        });  
    }
    
    $(".editarArt").click(function(){
        var id = $(this).attr('value');
        editarArticulo(id); return false;
    });
    
    function editarArticulo(id){
        var titulo1 = $(".titulo_"+id).text();
        var descripcion1 = $(".descripcion_"+id).text();
        var categoria1 = $(".idcategoria_"+id).val();
        var url1 = $(".url_img_"+id).attr('src');
        
        $.post("ArticuloServlet",{
            accion:"detallesArticulo",
            accion2:"editar",
            clave: id,
            titulo: titulo1,
            descripcion: descripcion1,
            categoria: categoria1,
            url: url1
        }, function(responseText){
            $(location).attr('href',"detalleArticulo.jsp?idArticulo="+id);
        }); 
    }
    
    $("#principal").click(function(){
        principal(); return false;
    });
    
    function principal(){
        $.post("UsuarioServlet",{
            accion:"principal",
        }, function(responseText){
            $(location).attr('href',"principal.jsp"); 
        });
    }
});