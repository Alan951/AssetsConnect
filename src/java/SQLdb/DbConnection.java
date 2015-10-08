
package SQLdb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Jorge Alan Villalón Pérez
 */
public class DbConnection {
    static String bd;
    static String login;
    static String password;
    static String url;
    
    Connection connection;
    
    public DbConnection(){
        //URL ARCHIVO
//        String userDir = System.getProperty("user.dir");
//        String separador = System.getProperty("file.separator");
//        String url = userDir+separador+"Files"+separador+"info.properties";
        //String glassFish = System.getProperty("com.sun.aas.instanceRoot");
        //Properties prop = System.getProperties();
        //System.out.println(glassFish);
        //Cargar credenciales.
        try{
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream("C://info.properties"));
            //propiedades.load(new FileInputStream(url));
            bd = propiedades.getProperty("bd");
            login = propiedades.getProperty("login");
            password = propiedades.getProperty("password");
            url = propiedades.getProperty("url");
        }catch(FileNotFoundException fnfe){
            System.out.println("No se ha encontrado archivo. | ");
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
        //Cargar driver
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexion
            connection = DriverManager.getConnection(url, login, password);
            
            if(connection!=null){
                System.out.println("Conexion a la base de datos "+bd+" establecida"+connection.toString());
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Connection getConnection(){
            return connection;
    }
    
    public void desconectar(){
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        connection = null;
    }
}
