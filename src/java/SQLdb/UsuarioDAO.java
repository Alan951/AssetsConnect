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
    
    public void actualizarDatos(Usuario actUser){
        DbConnection conex = new DbConnection(); 
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("UPDATE `biblioteca`.`usuarios` SET Nombre = ?, Password = ? WHERE `Usuario` = ?");
            prep.setString(1, actUser.getNombre());
            prep.setString(2, actUser.getPassword());
            prep.setString(3, actUser.getUsuario());

            prep.executeUpdate();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public String nombreUser(String user){
        DbConnection conex = new DbConnection();
        String nombre = "";
        try{
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT Nombre FROM `biblioteca`.`usuarios` WHERE BINARY Usuario = ?");
            consulta.setString(1, user);
            ResultSet res = consulta.executeQuery();
            while(res.next()){
                nombre = res.getString("Nombre");
            }
            res.close();
            consulta.close();
            conex.desconectar();
        }catch(Exception e){
            e.printStackTrace();
        }
        return nombre;
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
