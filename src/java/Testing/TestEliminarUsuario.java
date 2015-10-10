/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import SQLdb.UsuarioDAO;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class TestEliminarUsuario {
    public static void main(String []args){
        Scanner entrada = new Scanner(System.in);
        UsuarioDAO dao = new UsuarioDAO();
        
        String nombre = null;
        
        System.out.print("Introduce el nombre del usuario que deseas eliminar: ");
        nombre = entrada.nextLine();
        
        dao.eliminarUsuario(nombre);
    }
}
