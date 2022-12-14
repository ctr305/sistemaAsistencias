/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 19/08/22
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
    public static Connection abrirConexionBD(){
        Connection conexion = null;
        Path CURRENT_FILE = Paths.get("");
        String directory = CURRENT_FILE.toAbsolutePath().toString();
        directory = Paths.get(directory, "src", "sistemaasistencias", "modelo", "dbconfig.txt").toString();
        URL url;
        try {
            url = new File(directory).toURI().toURL();
            FileInputStream archivoConfiguracion = new FileInputStream(new File(url.getPath()));
            Properties atributos = new Properties();
            atributos.load(archivoConfiguracion);
            archivoConfiguracion.close();
            Class.forName("java.sql.Driver");
            String direccionBD = atributos.getProperty("DireccionBD");
            String usuario = atributos.getProperty("Usuario");
            String password = atributos.getProperty("Password");
            conexion = DriverManager.getConnection(direccionBD, usuario, password);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
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
