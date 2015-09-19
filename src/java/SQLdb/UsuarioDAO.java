/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLdb;

import Modelo.UsuarioLogin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author jorge
 */
public class UsuarioDAO {
    
    public boolean verificarLogin(UsuarioLogin user){
        DbConnection conex = new DbConnection();
        boolean login = false;
        try{
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM `usuarios` WHERE BINARY usuario= ? AND BINARY password= ?");
            consulta.setString(1, user.getUsername());
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
}
