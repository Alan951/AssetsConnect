
package Testing;

import Modelo.Articulo;
import SQLdb.ArticuloDAO;

public class ModificarArticuloTest {
    public static void main(String []args){
        ArticuloDAO dao = new ArticuloDAO();
        dao.modificarArticulo(new Articulo("El hombre Araña", "Una pelicula del hombre Araña", 1, "http://imgur.com/UnaPeliculaMalaDeSpiderman.png", "spi912", null));
    }
}
