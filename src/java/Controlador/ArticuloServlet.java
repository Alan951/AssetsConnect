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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge Alan Villalón Pérez 1588765
 * @name ArticuloServlet
 * @description Recibe parametros las cuales son validados por seguridad, 
 *              procesa la informacion segun la accion tomada / enviada,
 *              registrar, modificar y eliminar, son los precesos principales.
 */
public class ArticuloServlet extends HttpServlet {
    //Creamos sesion
    private HttpSession sesion;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //Iniciamos sesion y creamos Cookie
            sesion = request.getSession();
            Cookie cookie = null;
            
            //Declaracion de variables
            String titulo = null;
            String descripcion = null;
            int categoria = 0;
            String url_imagen = null;
            String idArticulo = null;
            String usuario = (String) sesion.getAttribute("usuario"); //obtenemos el nombre de usuario
            String buscar = null;

            //Recibimos las acciones tomadas
            String accion = request.getParameter("accion");
            String accion2 = request.getParameter("accion2");
            String accion3 = request.getParameter("accion3");
            
            //Reiniciamos declaracion de las acciones, que no sean null
            accion = (accion == null)? "" : request.getParameter("accion");
            accion2 = (accion2 == null)? "" : request.getParameter("accion2");
            accion3 = (accion3 == null)? "" : request.getParameter("accion3");
            
            //Si es 'newArticulo' o 'modificar' o 'editar' obtendra los siguientes parametros
            if (accion.equals("newArticulo") || accion2.equals("modificar") || accion2.equals("editar")) {
                titulo = request.getParameter("titulo");
                descripcion = request.getParameter("descripcion");
                categoria = Integer.parseInt(request.getParameter("categoria"));
                url_imagen = request.getParameter("url");
                idArticulo = request.getParameter("clave");
            }else if (accion.equals("detallesArticulo")) {//sino, obtendremos solo clave
                idArticulo = request.getParameter("clave");
            }else if(accion3.equals("buscar")){//sino, obtendremos solo el valor a buscar
                buscar = request.getParameter("buscador");
            }
            
            //Delcaracion de variables de error
            boolean errorFlag = false;
            String errorTitulo = "";
            String errorDescripcion = "";
            String errorCategoria = "";
            String errorURL_imagen = "";
            String errorIdArticulo = "";

            //Comprobacion de datos
            if (accion.equals("newArticulo") || accion2.equals("modificar") || accion2.equals("editar")){ 
                if(!idArticulo.matches("^([a-zA-Z 0-9 ñáéíóú]{1,60})$")){ 
                    errorIdArticulo = "210";
                    errorFlag = true;
                }if(!titulo.matches("^([a-zA-Z 0-9 ñáéíóú]{1,60})$")){ 
                    errorTitulo = "220";
                    errorFlag = true;
                }if(!descripcion.matches("^([a-zA-Z 0-9 ñáéíóú \\. \\,\\; \\( \\) \\\"]{1,500})$")){
                    errorDescripcion = "230";
                    errorFlag = true;
                }if(categoria < 1){
                    errorCategoria = "240";
                    errorFlag = true;
                }if(!url_imagen.matches("^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})).?)(?::\\d{2,5})?(?:[/?#]\\S*)?$")){
                    errorURL_imagen = "250";
                    errorFlag = true;
                }
            }else if (accion.equals("detallesArticulo")) {
                if(!idArticulo.matches("^([a-zA-Z 0-9 ñáéíóú]{1,60})$")){ 
                    errorIdArticulo = "210";
                    errorFlag = true;
                }
            }
            
            //Si un dato no es correcto, devuelve pagian actual con su respectivo error
            if(errorFlag){
                if(accion.equals("newArticulo"))
                    response.sendRedirect("nuevoArticulo.jsp?errorIdArticulo="+errorIdArticulo+"&errorTitulo="+errorTitulo+"&errorDescripcion="+errorDescripcion+"&errorCategoria="+errorCategoria+"&errorURL_imagen"+errorURL_imagen);
                if(accion.equals("modificar") || accion2.equals("modificar"))
                    response.sendRedirect("detalleArticulo.jsp?&errorTitulo="+errorTitulo+"&errorDescripcion="+errorDescripcion+"&errorCategoria="+errorCategoria+"&errorURL_imagen"+errorURL_imagen);
            
            }else{//sino, continua los procesos
                
                //si es 'newArticulo'
                if (accion.equals("newArticulo")) {
                    
                    //Guardamos los valores en la sesion
                    sesion = request.getSession();
                    sesion.setAttribute("titulo", titulo);
                    sesion.setAttribute("descripcion", descripcion);
                    sesion.setAttribute("categoria", categoria);
                    sesion.setAttribute("url_imagen", url_imagen);

                    //Instanciamos el modelo de Articulo
                    ArticuloDAO dao = new ArticuloDAO();

                    //Si exite previamente el articulo
                    if (dao.verificarArticulo(idArticulo)) {
                        
                        //reenvia a la página con código de existente
                        response.sendRedirect("nuevoArticulo.jsp?error=200");
                    
                    } else {//sino

                        //Registramos el articulo son sus respectivos datos obtenidos
                        dao.registrarArticulo(new Articulo(titulo, descripcion, categoria, url_imagen, idArticulo, usuario));

                        //guardamos los articulos registrados por el ususario
                        sesion.setAttribute("articulos",Utilidades.Utilidades.recargarArticulos(usuario));

                        //reenvia a la página de detalles del articulo con sus datos
                        response.sendRedirect("detalleArticulo.jsp?idArticulo="+idArticulo);
                    }
                    //Si es 'detalleArticulo'
                } else if (accion.equals("detallesArticulo")) {
                    
                    //Instanciamos el modelo del articulo
                    ArticuloDAO artDao = new ArticuloDAO();
                    
                    //si la accion es 'modificar'
                    if (accion2.equals("modificar")) {
                        //se guarda modificaciones del articulo con los nuevos datos
                        artDao.modificarArticulo(new Articulo(titulo, descripcion, categoria, url_imagen, idArticulo, usuario));

                        //se obtiene los articulos registrados por el usuario
                        sesion.setAttribute("articulos",Utilidades.Utilidades.recargarArticulos(usuario));
                        
                        //si la accion es 'eliminar'
                    } else if (accion2.equals("eliminar")) {
                        
                        //obtenemos el el nombre de usuario
                        usuario = (String) sesion.getAttribute("usuario");

                        //eliminamos el arituclo del usuario correspondiente
                        artDao.eliminarArticulo(idArticulo,usuario);

                        //se obtiene los articulos registrados por el usuario
                        sesion.setAttribute("articulos",Utilidades.Utilidades.recargarArticulos(usuario));
                        
                        //si la accion es 'editar'
                    } else if (accion2.equals("editar")){
                        
                        //Cargamos los datos en la sesion
                        sesion = request.getSession();
                        sesion.setAttribute("titulo", titulo);
                        sesion.setAttribute("descripcion", descripcion);
                        sesion.setAttribute("categoria", categoria);
                        sesion.setAttribute("url_imagen", url_imagen);
                    
                    }//fin de if
                }//fin de if
            }//fin de if/else 
            
            //si es 'buscar'
            if(accion3.equals("buscar")){
                
                //iniciamos sesion
                sesion = request.getSession();
                
                //establecemos nombre de usuario
                sesion.setAttribute("usuario", usuario);
                //se obtiene los articulos buscados registrados por el usuario
                sesion.setAttribute("articulos",Utilidades.Utilidades.buscarArticulos(usuario, buscar));
                
                //reennvia a la pagina de busqueda
                response.sendRedirect("busqueda.jsp");
            }//fin de if
        }//fin de try
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
