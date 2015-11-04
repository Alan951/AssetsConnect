package SQLdb;

import Modelo.Articulo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge Alan Villalón Pérez
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
            prep.setInt(5, articulo.getCategoria());
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
            prep.setInt(4, articulo.getCategoria());
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
            PreparedStatement prep = conex.getConnection().prepareStatement("DELETE FROM `biblioteca`.`articulos` WHERE `id_articulo` = ? and `Usuario` = ?");
            prep.setString(1, id);
            prep.setString(2, usuario);
            prep.executeQuery();
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
            PreparedStatement prep = conex.getConnection().prepareStatement("SELECT * FROM `biblioteca`.`articulos` WHERE BINARY `Usuario` = ?");
            prep.setString(1, nombre);
            result = prep.executeQuery();
            while(result.next()){
               articulos.add(new Articulo(result.getString("Titulo"), result.getString("Descripcion"), result.getInt("id_categoria"), result.getString("URL_imagen"), result.getString("id_articulo"), result.getString("Usuario")));
            }
            result.close();
            prep.close();
            conex.desconectar();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return articulos;
    }
}
