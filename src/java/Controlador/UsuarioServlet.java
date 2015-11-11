package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modelo.Usuario;
import SQLdb.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Alan Villalón Pérez 1588765
 * @name UsuarioServlet
 * @description Recibe parametros las cuales son validados por seguridad, 
 *              procesa la informacion segun la accion tomada / enviada,
 *              registrar, modificar e iniciar sesion, son los precesos principales.
 */
public class UsuarioServlet extends HttpServlet {
    //Creamos sesion
    private HttpSession sesion;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //Recibimos la accion tomada
            String accion = request.getParameter("accion");
            
            //si es 'cerrar'
            if(accion.equals("cerrar")){
                //se cierra sesion
                sesion.invalidate();
                
                //si es 'principal'
            }else if(accion.equals("principal")){
                
                //se obtiene los arituculos registrados por el usuario
                sesion.setAttribute("articulos",Utilidades.Utilidades.recargarArticulos((String)sesion.getAttribute("usuario")));                       
            }//fin de if
            
            //si la accion es 'loggin' o 'registrar' o 'modificar'
            if(accion.equals("loggin") || accion.equals("registrar") || accion.equals("modificar")){  
                
                //declaracion de variables
                String usuario = request.getParameter("usuario");
                String password = request.getParameter("password");
                
                //declaracion de variables de error
                boolean errorFlag = false;
                String errorUsuario = "";
                String errorPass = "";
                String errorLogin = "";

                //Comprobacion de datos
                if(!usuario.matches("^([a-zA-Zñáéíóú 0-9\\_]{4,60})$")){
                    errorUsuario = "100";
                    errorFlag = true;
                }if(!password.matches("^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{5,60})$")){ 
                    errorPass = "120";
                    errorFlag = true;
                }

                //Si un dato no es correcto, devuelve pagian actual con su respectivo error 
                if(errorFlag){
                    response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario+"&errorPass="+errorPass);
                
                }else{//sino, continua los procesos

                    //instanciamos el modelo del usuario
                    UsuarioDAO dao = new UsuarioDAO();

                    //addicionamos las categorias, para despues ser usada en el registro de articulos
                    response.addCookie(Utilidades.Utilidades.recargarCookieCategorias());

                    //si la accion es 'loggin'
                    if(accion.equals("loggin")){

                        //Si existe el usuario
                        if(dao.verificarLogin(new Usuario(usuario, password))){

                            //obtenemos el nombre del usuario
                            String nombre = dao.nombreUser(usuario);

                            //se guardan los datos en la sesion, para su futura modificacion
                            sesion = request.getSession();
                            sesion.setAttribute("usuario", usuario);
                            sesion.setAttribute("nombre", nombre);
                            
                            //se obtiene los articulos registrados por el usuario
                            sesion.setAttribute("articulos",Utilidades.Utilidades.recargarArticulos(usuario));

                            //reenvia a la página principal
                            response.sendRedirect("principal.jsp");
                        
                        }else{//sino
                            //reenvia a la página actual con error de login
                            errorLogin = "102";
                            response.sendRedirect("login.jsp?errorLogin="+errorLogin);
                        }//fin de if/else
                    
                    }else{//sino

                        //obtenemos el parametro de nombre
                        String nombre = request.getParameter("name");

                        //Delcaramos variable de error
                        String errorNombre = "";

                        //Comprobamos dato
                        if(!nombre.matches("^([a-zA-Z ñáéíóú]{10,60})$")){ 
                            errorNombre = "110";
                            errorFlag = true;
                        }

                        //Si un dato no es correcto, devuelve pagian actual con su respectivo error 
                        if(errorFlag){
                            response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario+"&errorPass="+errorPass+"&errorNombre="+errorNombre);
                        }else{//sino, continua el siguiente proceso
                            
                                //si la accion es 'registrar'
                            if(accion.equals("registrar")){

                                //se comprueba si ya existe el usuario
                                if(dao.comprobarUsuario(usuario)){
                                    //reenvia a la página actual con error de usuario
                                    errorUsuario = "101";
                                    response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario);
                                
                                }else{//sino
                                    
                                    //registramos al usuario
                                    dao.registrarUsuario(new Usuario(usuario, password, nombre));

                                    //guardamos los datos en sesion
                                    sesion = request.getSession();
                                    sesion.setAttribute("usuario", usuario);
                                    sesion.setAttribute("nombre", nombre);

                                    //reenvia a la página de infoUsuario por si se desea modificar
                                    response.sendRedirect("infoUsuario.jsp");
                                }//fin de if/else
                                
                                //si la accion es 'modificar'
                            }else if(accion.equals("modificar")){

                                    //se actualizan los datos del usuario
                                    dao.actualizarDatos(new Usuario(usuario, password, nombre));

                                    //guardamos los datos en sesion
                                    sesion = request.getSession();
                                    sesion.setAttribute("usuario", usuario);
                                    sesion.setAttribute("nombre", nombre);

                                    //reenvia a la página principal
                                    response.sendRedirect("principal.jsp");
                            }//fin de if
                        }//fin de if/else
                    }//fin de if/else
                }//fin de if/else
            }//fin de if
        }// fin de try
    }//fin de processRequest

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
