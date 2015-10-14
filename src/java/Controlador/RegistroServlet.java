/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import SQLdb.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jorge
 */
public class RegistroServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String usuario = request.getParameter("usuario");
            String nombre = request.getParameter("name");
            String password = request.getParameter("password");
            
            boolean errorFlag = false;
            String errorUsuario = "";
            String errorPass = "";
            String errorNombre = "";

            if(password.length() < 5 || !password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]+$")){ //Verificar password
            //  errorPass = "La contraseña debe de tener al menos 1 numero, letras mayusculas y minusculas";
                errorPass = "110";
                errorFlag = true;
            }if(nombre.length() < 4 || nombre.length() > 30){
            //  errorNombre = "El nombre debe de tener al menos 5 caracteres";
                errorNombre = "120";
                errorFlag = true;
            }if(usuario.length() < 4 || usuario.length() > 15){
            //  errorUsuario = "El username debe de tener al menos 4 caracteres";
                errorUsuario = "130";
                errorFlag = true;
            }
            UsuarioDAO dao = new UsuarioDAO();
            if(errorFlag){
                response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario+"&errorPass="+errorPass+"&errorNombre="+errorNombre);
            }else{
                if(dao.comprobarUsuario(usuario)){
                    errorUsuario = "131";
                    response.sendRedirect("registro.jsp?errorUsuario="+errorUsuario);
                }else{
                    dao.registrarUsuario(new Usuario(usuario, password, nombre));
                    // reedireccionar a la pagina de info usuario --> pendiente por hacer la vista
                    response.sendRedirect("registro.jsp?registro=completo");
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
