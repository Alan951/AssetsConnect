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
public class LoginServlet extends HttpServlet {
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
                        
            if(accion.equals("loggin")){
                String nombre = request.getParameter("usuario");
                String password = request.getParameter("password");

                boolean errorFlag = false;
                String errorUsuario = "";
                String errorPass = "";

                if(nombre.length() < 4){ //verificar username
                //    errorUsuario = "El username debe de tener almenos 5 letras";
                    errorUsuario = "110";
                    errorFlag = true;
                }if(password.length() < 5 || !password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]+$")){ //Verificar password
                //    errorPass = "La contraseña debe de tener al menos 1 numero, letras mayusculas y minusculas";
                    errorPass = "120";
                    errorFlag = true;
                }

                if(errorFlag){
                    response.sendRedirect("login.jsp?errorUsuario="+errorUsuario+"&errorPass="+errorPass);
                }else{
                    //UsuarioDAO
                    Usuario user = new Usuario(nombre, password);
                    UsuarioDAO dao = new UsuarioDAO();
                    System.out.println("Usuario: "+user.getNombre()+"\nPass: "+user.getPassword());
                    if(dao.verificarLogin(user)){
                        sesion = request.getSession();
                        sesion.setAttribute("usuario", nombre);
                        //Traer articulos
                        response.addCookie(Utilidades.Utilidades.recargarCookie(nombre));
                        response.sendRedirect("principal.jsp");
                    }else{
                        response.sendRedirect("login.jsp?login=loginFail");
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
