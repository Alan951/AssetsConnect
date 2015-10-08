/*
Programa de pruebas
*/
package Testing;

import Modelo.Usuario;

/**
 *
 * @author jorge
 */
public class TestUser {
    public static void main(String []args){
        Usuario user = new Usuario("Jorge Alan", "Maria951teniaunperrito");
        System.out.println(user.getPassword()+"\n\n\n");
        String userDir = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        
        System.out.println(userDir+separador+"Files"+separador+"info.properties");
    }
}
