package Testing;

import Modelo.Articulo;
import SQLdb.ArticuloDAO;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class RegistrarArticuloTest {
    public static void main(String []args){
        Scanner entrada = new Scanner(System.in);
        ArticuloDAO dao = new ArticuloDAO();
        
//        System.out.println("Introduce el nombre del articulo");
//        String nombre = entrada.nextLine();
//        System.out.println("Introduce la descripcion del articulo");
//        String descripcion = entrada.nextLine();
//        System.out.println("Introduce la categoria");
//        String categoria = entrada.nextLine();
//        System.out.println("Introduce la url de la imagen");
//        String url = entrada.nextLine();
//        System.out.println("Introduce la idArticulo");
//        String id = entrada.nextLine();
//        System.out.println("Introduce el usuario");
//        String usuario = entrada.nextLine();
        //Articulo art = new Articulo(nombre, descripcion, categoria, url, id, usuario);
        Articulo art = new Articulo("SpiderMan", "Una pelicula mala de SpiderMan", 1, "http://imgur.com/UnaPeliculaMalaDeSpiderman.jpg", "spi912", "Coke951");
        
        dao.registrarArticulo(art);
    }
}
