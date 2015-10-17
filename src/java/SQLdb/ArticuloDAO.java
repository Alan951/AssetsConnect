package SQLdb;

import Modelo.Articulo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
