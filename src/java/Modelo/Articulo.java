package Modelo;

/**
 *
 * @author Jorge Alan Villalón Pérez 1588765
 * @name Articulo
 * @description Modelo del articulo para su registro o actualizacion.
 */
public final class Articulo {
    
    //Declaración de variables
    private String titulo;
    private String descripcion;
    private int idcategoria;
    private String categoria;
    private String url_imagen;
    private String idArticulo;
    private String usuario;

    //Metodo constructor utilizado al momento de buscar articulos
    public Articulo(String titulo, String descripcion, int idcategoria, String url_imagen, String idArticulo, String usuario){
        setTitulo(titulo);
        setDescripcion(descripcion);
        setIdCategoria(idcategoria);
        setUrl_imagen(url_imagen);
        setIdArticulo(idArticulo);
        setUsuario(usuario);
    }
    
    //Metodo constructor utilizado al momento de registar o actualizar articulos
    public Articulo(String idArticulo, String titulo, int idcategoria, String categoria, String descripcion, String url_imagen, String usuario){
        setIdArticulo(idArticulo);
        setTitulo(titulo);
        setIdCategoria(idcategoria);
        setCategoria(categoria);
        setDescripcion(descripcion);
        setUrl_imagen(url_imagen);
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
     * @return the idcategoria
     */
    public int getIdCategoria() {
        return idcategoria;
    }

    /**
     * @param idcategoria the idcategoria to set
     */
    public void setIdCategoria(int idcategoria) {
        this.idcategoria = idcategoria;
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

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
