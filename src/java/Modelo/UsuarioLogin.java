/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Utilidades.Utilidades;

/**
 *
 * @author jorge
 */
public class UsuarioLogin {
    private String username;
    private String password;
    
    public UsuarioLogin(String username, String password){
        setUsername(username);
        setPassword(password);
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        //Cifrar datos
        this.password = Utilidades.cifrarDatos(password);
    }
}
