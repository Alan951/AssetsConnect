package Modelo;

/**
 *
 * @author Erich Benjamin Silva García  1566176
 * @name Categoria
 * @description Modelo del articulo para bteneros en la DB.
 */
public class Categoria {
    
    //Declaración de variables
    private int idCategoria;
    private String categoria;
    
    //Metodo constructor para Categoria.
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
