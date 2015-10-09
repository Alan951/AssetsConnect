package Modelo;

/**
 *
 * @author Jorge Alan Villalón Pérez
 */
public class UsuarioRegistro {
    private String nombre;
    private String usuario;
    private String password;
    
    public UsuarioRegistro(String nombre, String usuario, String password){
        setNombre(nombre);
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
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
}
