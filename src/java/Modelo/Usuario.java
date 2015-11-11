package Modelo;

/**
 *
 * @author Erich Benjamin Silva García  1566176
 * @name Usuario
 * @description Modelo del usuario para su registro o actualizacion.
 */
public class Usuario {
    
    //Declaración de variables
    private String nombre;
    private String password;
    private String usuario;
    
    //Metodo constructor utilizado al momento de registrar o actualizar un usuario
    public Usuario(String usuario, String password, String nombre){
        setNombre(nombre);
        setPassword(password);
        setUsuario(usuario);
    }
    
    //Metodo constructor utilizado al momento del inicio de sesion de un usuario.
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
        //pasa por el procesos de Hash 
        this.password = Utilidades.Utilidades.cifrarDatos(password);
    }
    
    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    /**
     * @return the usuario
     */
    public String getUsuario(){
        return usuario;
    }
}
