/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLdb;

import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jorge
 */
public class UsuarioDAO {
    
    public boolean verificarLogin(Usuario user){
        DbConnection conex = new DbConnection();
        boolean login = false;
        try{
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM `usuarios` WHERE BINARY nombre= ? AND BINARY password= ?");
            consulta.setString(1, user.getNombre());
            consulta.setString(2, user.getPassword());
            ResultSet res = consulta.executeQuery();
            while(res.next()){
                login = true;
            }
            res.close();
            consulta.close();
            conex.desconectar();
        }catch(Exception e){
            e.printStackTrace();
        }
        return login;
    }
    
    public void registrarUsuario(Usuario userReg){
        DbConnection conex = new DbConnection();
        try{
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO `usuarios` VALUES (NULL, '"+userReg.getPassword()+"', '"+userReg.getNombre()+"')");
            estatuto.close();
            conex.desconectar();
            System.out.println("Se ha registrado!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
