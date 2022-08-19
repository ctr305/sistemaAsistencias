/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 18/08/22
 * Descripcion: Clase de conexion con base de datos.
 */
package sistemaasistencias.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    public static Connection abriConexionBD(){
        Connection conexion = null;
        try{
            Path CURRENT_FILE = Paths.get("");
            String directory = CURRENT_FILE.toAbsolutePath().toString();
            directory = Paths.get(directory, "src", "sistemaasistencias", "modelo", "dbconfig.txt").toString();
            URL url = new File(directory).toURI().toURL();
            FileInputStream archivoConfiguracion = new FileInputStream(new File(url.getPath()));
            Properties atributos = new Properties();
            atributos.load(archivoConfiguracion);
            archivoConfiguracion.close();
            Class.forName("java.sql.Driver");
            String direccionBD = atributos.getProperty("DireccionDB");
            String usuario = atributos.getProperty("Uusario");
            String password = atributos.getProperty("Password");
            conexion = DriverManager.getConnection(direccionBD, usuario, password);
        }catch(FileNotFoundException fe){
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, fe);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
}
