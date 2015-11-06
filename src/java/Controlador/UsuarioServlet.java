package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modelo.Usuario;
import SQLdb.ArticuloDAO;
import SQLdb.UsuarioDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Alan Villalón Pérez
 */
public class UsuarioServlet extends HttpServlet {
    private HttpSession sesion;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String accion = request.getParameter("accion");
            
            if(accion.equals("cerrar")){
                sesion.invalidate();
            }
            
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            boolean errorFlag = false;
            String errorUsuario = "";
            String errorPass = "";
            String errorLogin = "";

            if(!usuario.matches("^([a-zA-Zñáéíóú 0-9\\_]{4,60})$")){
                errorUsuario = "100";
                errorFlag = true;
            }if(!password.matches("^((?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{5,60})$")){ 
                errorPass = "120";
                errorFlag = true;
            }

            if(errorFlag){
                response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario+"&errorPass="+errorPass);
            }else{
                
                UsuarioDAO dao = new UsuarioDAO();
                
                response.addCookie(Utilidades.Utilidades.recargarCookieCategorias());
                
                if(accion.equals("loggin")){

                    if(dao.verificarLogin(new Usuario(usuario, password))){
                        
                        String nombre = dao.nombreUser(usuario);
                        
                        sesion = request.getSession();
                        sesion.setAttribute("usuario", usuario);
                        sesion.setAttribute("nombre", nombre);
                        sesion.setAttribute("articulos",Utilidades.Utilidades.recargarArticulos(usuario));
                        
                        response.sendRedirect("principal.jsp");
                    }else{
                        errorLogin = "102";
                        response.sendRedirect("login.jsp?errorLogin="+errorLogin);
                    }
                }else{

                    String nombre = request.getParameter("name");

                    String errorNombre = "";

                    if(!nombre.matches("^([a-zA-Z ñáéíóú]{10,60})$")){ 
                        errorNombre = "110";
                        errorFlag = true;
                    }

                    if(errorFlag){
                        response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario+"&errorPass="+errorPass+"&errorNombre="+errorNombre);
                    }else{

                        if(accion.equals("registrar")){

                            if(dao.comprobarUsuario(usuario)){
                                errorUsuario = "101";

                                response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario);
                            }else{
                                dao.registrarUsuario(new Usuario(usuario, password, nombre));

                                sesion = request.getSession();
                                sesion.setAttribute("usuario", usuario);
                                sesion.setAttribute("nombre", nombre);

                                response.sendRedirect("infoUsuario.jsp");
                            }
                        }else if(accion.equals("modificar")){

                                dao.actualizarDatos(new Usuario(usuario, password, nombre));

                                sesion = request.getSession();
                                sesion.setAttribute("usuario", usuario);
                                sesion.setAttribute("nombre", nombre);

                                response.sendRedirect("principal.jsp");
                        }
                    }
                }
            }
        }
    }

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
