package SQLdb;

import Modelo.Articulo;
import Modelo.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge Alan Villalón Pérez 1588765
 * @name ArticuloDAO
 * @description Funciones para registrar, eliminar, actualizar, verificar y obtener articulos y categorias.
 */
public class ArticuloDAO {
    
    public void registrarArticulo(Articulo articulo){
        DbConnection conex = new DbConnection();
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("INSERT INTO `biblioteca`.`articulos` (id_articulo, Titulo, Descripcion, URL_imagen, id_categoria, Usuario) VALUES (?, ?, ?, ?, ?, ?)");
            prep.setString(1, articulo.getIdArticulo());
            prep.setString(2, articulo.getTitulo());
            prep.setString(3, articulo.getDescripcion());
            prep.setString(4, articulo.getUrl_imagen());
            prep.setInt(5, articulo.getIdCategoria());
            prep.setString(6, articulo.getUsuario());
            prep.executeUpdate();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void modificarArticulo(Articulo articulo){
        DbConnection conex = new DbConnection();
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("UPDATE `biblioteca`.`articulos` SET `Titulo` = ?, `Descripcion` = ?, `URL_imagen` = ?, `id_categoria` = ? WHERE `id_articulo` = ?");
            prep.setString(1, articulo.getTitulo());
            prep.setString(2, articulo.getDescripcion());
            prep.setString(3, articulo.getUrl_imagen());
            prep.setInt(4, articulo.getIdCategoria());
            prep.setString(5, articulo.getIdArticulo());
            prep.executeUpdate();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void eliminarArticulo(String id, String usuario){
        DbConnection conex = new DbConnection();
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("DELETE FROM `biblioteca`.`articulos` WHERE `id_articulo` = ? AND `Usuario` = ?");
            prep.setString(1, id);
            prep.setString(2, usuario);
            prep.executeUpdate();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean verificarArticulo(String id){
        DbConnection conex = new DbConnection();
        ResultSet result = null;
        boolean existe = false;
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("SELECT * FROM `biblioteca`.`articulos` WHERE BINARY `id_articulo` = ?");
            prep.setString(1, id);
            result = prep.executeQuery();
            while(result.next()){
                existe = true;
            }
            result.close();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return existe;
    }
    
    public ArrayList<Articulo> getArticulos(String nombre){
        DbConnection conex = new DbConnection();
        ResultSet result = null;
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("SELECT a.id_articulo,a.Titulo,a.Descripcion,a.URL_imagen,a.Usuario,a.id_categoria,c.categoria FROM `articulos` AS a,`categorias` AS c WHERE BINARY a.id_categoria=c.id_categoria and a.Usuario = ?");
            prep.setString(1, nombre);
            result = prep.executeQuery();
            while(result.next()){
               articulos.add(new Articulo(result.getString("id_articulo"),result.getString("Titulo"),result.getInt("id_categoria"),result.getString("categoria"),result.getString("Descripcion"),result.getString("URL_imagen"),result.getString("Usuario")));
            }
            result.close();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return articulos;
    }
    
    public ArrayList<Articulo> getBuscarArticulos(String nombre, String buscar){
        DbConnection conex = new DbConnection();
        ResultSet result = null;
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("SELECT a.id_articulo,a.Titulo,a.Descripcion,a.URL_imagen,a.Usuario,a.id_categoria,c.categoria FROM `articulos` AS a,`categorias` AS c WHERE a.id_categoria=c.id_categoria and a.Usuario = ? and ((a.Descripcion like (?)) or (a.Titulo like (?)))");
            prep.setString(1, nombre);
            prep.setString(2, "%"+buscar+"%");
            prep.setString(3, "%"+buscar+"%");
            result = prep.executeQuery();
            while(result.next()){
               articulos.add(new Articulo(result.getString("id_articulo"),result.getString("Titulo"),result.getInt("id_categoria"),result.getString("categoria"),result.getString("Descripcion"),result.getString("URL_imagen"),result.getString("Usuario")));
            }
            result.close();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return articulos;
    }
    
    public ArrayList<Categoria> getCategorias(){
        DbConnection conex = new DbConnection();
        ResultSet result = null;
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        try{
            PreparedStatement prep = conex.getConnection().prepareStatement("SELECT * FROM `biblioteca`.`categorias` WHERE BINARY 1");
            result = prep.executeQuery();
            while(result.next()){
               categorias.add(new Categoria(result.getInt("id_categoria"),result.getString("categoria")));
            }
            result.close();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return categorias;
    }
}
