package Modelo;

/**
 *
 * @author Jorge Alan Villalón Pérez
 */
public class Categoria {
    private int idCategoria;
    private String categoria;
    
    //Metodo constructor para Articulo.
    public Categoria(int idCategoria, String categoria){
        setIdCategoria(idCategoria);
        setCategoria(categoria);
    }

    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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
