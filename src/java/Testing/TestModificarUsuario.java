
package Testing;

import SQLdb.UsuarioDAO;
import java.util.Scanner;

/**
 *
 * @author Jorge Alan Villalón Pérez
 */
public class TestModificarUsuario {
    public static void main(String []args){
        Scanner entrada = new Scanner(System.in);
        UsuarioDAO dao = new UsuarioDAO();
        
        String nombreAnt = null;
        String nombre = null;
        String pass = null;
        
        System.out.println("Que quieres hacer?\n1) Modificar usuario\n2) Modificar contraseña\n3) Modificar ambos.");
        int opcion = entrada.nextInt();
        entrada.nextLine();
        switch(opcion){
            case 1:
                System.out.print("Introduce el nombre de usuario:");
                nombre = entrada.nextLine();
                System.out.println("Introduce el nombre anterior: ");
                nombreAnt = entrada.nextLine();
                break;
            case 2:
                System.out.println("Introduce la contraseña: ");
                pass = entrada.nextLine();
                System.out.println("Introduce el nombre anterior: ");
                nombreAnt = entrada.nextLine();
                break;
            case 3:
                System.out.print("Introduce el nombre de usuario:");
                nombre = entrada.nextLine();
                System.out.println("Introduce la contraseña: ");
                pass = entrada.nextLine();
                System.out.println("Introduce el nombre anterior: ");
                nombreAnt = entrada.nextLine();
        }
        
        dao.actualizarDatos(nombreAnt, nombre, pass);
    }
}
