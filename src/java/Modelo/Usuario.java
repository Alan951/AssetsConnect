/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class Usuario {
    private String nombre;
    private String password;
    private String usuario;
    
    //Metodo constructor utilizado al momento de registrar un usuario
    public Usuario(String usuario, String password, String nombre){
        setNombre(nombre);
        setPassword(password);
        setUsuario(usuario);
    }
    
    //Metodo constructor utilizado l momento del inicio de sesion de un usuario.
    public Usuario(String usuario, String password){
        setUsuario(usuario);
        setPassword(password);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        this.password = Utilidades.Utilidades.cifrarDatos(password);
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public String getUsuario(){
        return usuario;
    }
}
