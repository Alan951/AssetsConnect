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
        System.out.println(user.getPassword());
    }
}
