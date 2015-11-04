package Modelo;

/**
 *
 * @author Jorge Alan Villalón Pérez
 */
public class Articulo {
    private String titulo;
    private String descripcion;
    private int categoria;
    private String url_imagen;
    private String idArticulo;
    private String usuario;
    
    //Metodo constructor para Articulo.
    public Articulo(String titulo, String descripcion, int categoria, String url_imagen, String idArticulo, String usuario){
        setTitulo(titulo);
        setDescripcion(descripcion);
        setCategoria(categoria);
        setUrl_imagen(url_imagen);
        setIdArticulo(idArticulo);
        setUsuario(usuario);
    }
    
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the categoria
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the url_imagen
     */
    public String getUrl_imagen() {
        return url_imagen;
    }

    /**
     * @param url_imagen the url_imagen to set
     */
    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    /**
     * @return the idArticulo
     */
    public String getIdArticulo() {
        return idArticulo;
    }

    /**
     * @param idArticulo the idArticulo to set
     */
    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
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
}
