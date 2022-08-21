/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 
 * Fecha de modificacion:
 * Descripcion: DAO de la clase Usuario
 */
package sistemaasistencias.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectoconstruccion.util.Constantes;
import sistemaasistencias.modelo.ConexionBD;
import sistemaasistencias.modelo.POJO.Usuario;
import static sistemaasistencias.modelo.POJO.Usuario.usuarioLogin;

public class UsuarioDAO {
    public static Usuario IniciarSesion(String nombreUsuario, String password){
        Usuario.usuarioLogin = new Usuario();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM usuario WHERE nombreUsuario = ? AND password = SHA(?,256)";
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setString(1, nombreUsuario);
                prepararConsulta.setString(2, password);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                if(resultadoConsulta.next()){
                    usuarioLogin.setIdUsuario(resultadoConsulta.getString("idUsuario"));
                    usuarioLogin.setNombreUsuario(resultadoConsulta.getString("nombreUsuario"));
                    usuarioLogin.setRol(resultadoConsulta.getString("rol"));
                    usuarioLogin.setNombre(resultadoConsulta.getString("nombre"));
                    usuarioLogin.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                    usuarioLogin.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                    usuarioLogin.setCodigoRespuesta(Constantes.CODIGO_OPERACION_CORRECTA);
                } else {
                    usuarioLogin.setCodigoRespuesta(Constantes.CODIGO_CREDENCIALES_INCORRECTAS);
                }
                conexionBD.close();
            } catch(SQLException e) {
                usuarioLogin.setCodigoRespuesta(Constantes.CODIGO_ERROR_CONEXIONBD);
            }
        }
        return Usuario.usuarioLogin;
    }
    
    public static Usuario getUsuario(String idUsuario){
        Usuario usuario = new Usuario();
        Connection conexionDB = ConexionBD.abrirConexionBD();
        if(conexionDB != null){
            try {
                String consulta = "SELECT idUsuario, rol, nombre, apellidoPaterno, apellidoMaterno FROM usuario WHERE idUsuario = ?";
                PreparedStatement prepararConsulta = conexionDB.prepareStatement(consulta);
                prepararConsulta.setString(1, idUsuario);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                if(resultadoConsulta.next()){
                    usuario.setIdUsuario(resultadoConsulta.getString("idUsuario"));
                    usuario.setNombre(resultadoConsulta.getString("nombre"));
                    usuario.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                    usuario.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }
}
