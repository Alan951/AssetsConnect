/*
Programa de pruebas
*/
package Testing;


import Modelo.Usuario;
import SQLdb.UsuarioDAO;
import java.util.Scanner;
/**
 *
 * @author jorge
 */
public class TestReg {
    public static void main(String []args){
        Scanner entrada = new Scanner(System.in);
        UsuarioDAO userDAO = new UsuarioDAO();
        Usuario userReg = null;
        
        String nombre = "";
        String pass = "";
        
        System.out.println("Escribe tu nombre");
        nombre = entrada.nextLine();
        System.out.println("Escribe tu contraseña");
        pass = entrada.nextLine();
        userReg = new Usuario(nombre, pass);
        userDAO.registrarUsuario(userReg);
        
    }
}
