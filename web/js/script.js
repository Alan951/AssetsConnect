$(document).ready(function () {

    $("#form").validate({
        rules:{
            name:{
                required: true,
                nombreValid: true,
                minlength: 10
            },
            usuario:{
                required: true,
                minlength: 4
            },
            password:{
                required: true,
                passwordValid :true
            },
            password2:{
                required: true,
                equalTo: "#password"
            }
        },
        messages:{
            name:{
                minlength:"Requiere un minimo de 10 caracteres"
            },
            usuario:"Requiere un minimo de 4 caracteres",
            password:{
                required: "Requiere contraseña"
            },
            password2:"No coinciden las contraseñas"
        }
    });
    
    jQuery.validator.addMethod("nombreValid", function(value,element){
        return this.optional(element) || /^(([a-zA-Z]+) ([a-zA-Z]+))*$/.test(value);
    },"No debe de contener numeros o caracteres especiales");
    
    jQuery.validator.addMethod("passwordValid", function(value,element){
        return this.optional(element) || /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]+$/.test(value);
    },"La contraseña debe de tener al menos 1 numero, letras mayusculas y minusculas.mayor a 5 caracteres");
});