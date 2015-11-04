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
 * @author Jorge Alan Villalón Pérez | Puto el que lo lea.
 */
public class UsuarioDAO {
    
    public boolean verificarLogin(Usuario user){
        DbConnection conex = new DbConnection();
        boolean login = false;
        try{
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM `biblioteca`.`usuarios` WHERE BINARY Usuario= ? AND BINARY Password= ?");
            consulta.setString(1, user.getUsuario());
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
    
    public boolean comprobarUsuario(String nombre){
        DbConnection conex = new DbConnection();
        boolean existe = false;
        try{
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM `biblioteca`.`usuarios` WHERE BINARY Usuario = ?");
            consulta.setString(1, nombre);
            ResultSet res = consulta.executeQuery();
            while(res.next()){
                existe = true;
            }
            res.close();
            consulta.close();
            conex.desconectar();
        }catch(Exception e){
            e.printStackTrace();
        }
        return existe;
    }
    
    public void registrarUsuario(Usuario userReg){
        DbConnection conex = new DbConnection();
        try{
            Statement estatuto = conex.getConnection().createStatement();
            PreparedStatement prep = conex.getConnection().prepareStatement("INSERT INTO `usuarios` (`Usuario`, `Password`, `Nombre`) VALUES (?, ?, ?)");
            //estatuto.execute("INSERT INTO `usuarios`(`Usuario`, `Password`, `Nombre`) VALUES ('"+userReg.getUsuario()+"', '"+userReg.getPassword()+"', '"+userReg.getNombre()+"')");
            prep.setString(1, userReg.getUsuario());
            prep.setString(2, userReg.getPassword());
            prep.setString(3, userReg.getNombre());
            prep.executeUpdate();
            estatuto.close();
            conex.desconectar();
            System.out.println("Se ha registrado!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar usuarios *Nombre *Contraseña
    public void actualizarDatos(String nombreAnt, String nombre, String pass){
        DbConnection conex = new DbConnection();
        String setter = null;
        if(nombre != null && pass != null) setter = "Usuario = ?, Password = ?";
        else if(nombre != null) setter = "Usuario = ?";
        else if(pass != null) setter = "Password = ?"; 
        
        String query = "UPDATE `biblioteca`.`usuarios` SET "+setter+" WHERE `Usuario` = ?";
        
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement(query);
            if(nombre != null && pass != null){
                prep.setString(1, nombre);
                prep.setString(2, pass);
                prep.setString(3, nombreAnt);
            }else{
                prep.setString(1, nombre == null ? pass : nombre);
                prep.setString(2, nombreAnt);
            }
            prep.executeUpdate();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void eliminarUsuario(String nombre){
        DbConnection conex = new DbConnection();
        try{
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("DELETE FROM `biblioteca`.`usuarios` WHERE BINARY `Usuario` = '"+nombre+"'");
            estatuto.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
