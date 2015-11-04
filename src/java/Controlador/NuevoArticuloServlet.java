/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Articulo;
import SQLdb.ArticuloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Alan Villalón Pérez
 */
public class NuevoArticuloServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();

            String titulo = null;
            String descripcion = null;
            int categoria = 0;
            String url_imagen = null;
            String idArticulo = null;
            String usuario = null;

            String accion = request.getParameter("accion");
            String accion2 = request.getParameter("accion2");
            //switch (accion) {
                //case "newArticulo":
            if(accion.equals("newArticulo")){
                    //Declaracion de variables
                    titulo = request.getParameter("titulo");
                    descripcion = request.getParameter("descripcion");
                    categoria = Integer.parseInt(request.getParameter("categoria"));
                    url_imagen = request.getParameter("url");
                    idArticulo = request.getParameter("clave");
                    usuario = (String) sesion.getAttribute("usuario");
                    
                    
                    
                    if (url_imagen.equals("")) {
                        url_imagen = "images/ArticuloDefault.jpg";
                    }

                    Articulo articulo = new Articulo(titulo, descripcion, categoria, url_imagen, idArticulo, usuario);

                    ArticuloDAO dao = new ArticuloDAO();
                    
                    if(dao.verificarArticulo(idArticulo)){
                        //Enviar valores. No reescribir.
                        response.sendRedirect("nuevoArticulo.jsp?error=200");
                    }else{
                        dao.registrarArticulo(articulo);
                        //Reedireccionar a detalles del articulo
                        response.sendRedirect("detalleArticulo.jsp");
                    }
                    
                    
                    //break;
            }else if(accion.equals("detallesArticulo")){//    case "detallesArticulo":
                    
                    if (accion2.equals("modificar")) {
                        titulo = request.getParameter("titulo");
                        descripcion = request.getParameter("descripcion");
                        categoria = Integer.parseInt(request.getParameter("categoria"));
                        url_imagen = request.getParameter("url");
                        idArticulo = request.getParameter("clave");
                        usuario = (String) sesion.getAttribute("usuario");

                        Articulo articuloMod = new Articulo(titulo, descripcion, categoria, url_imagen, idArticulo, usuario);

                        ArticuloDAO daoMod = new ArticuloDAO();
                        daoMod.registrarArticulo(articuloMod);
                        if (url_imagen.equals("")) {
                            url_imagen = "images/ArticuloDefault.jpg";
                        }
                    } else if ("accion2".equals("eliminar")) {
                        idArticulo = request.getParameter("clave");
                        ArticuloDAO artDao = new ArticuloDAO();
                        artDao.eliminarArticulo(idArticulo);
                        response.sendRedirect("parincipal.jsp");
                    }
            //        break;
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
