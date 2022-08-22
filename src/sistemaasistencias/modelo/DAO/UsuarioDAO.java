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
import sistemaasistencias.util.Constantes;
import sistemaasistencias.modelo.ConexionBD;
import sistemaasistencias.modelo.POJO.Usuario;
import static sistemaasistencias.modelo.POJO.Usuario.usuarioLogin;

public class UsuarioDAO {
    public static Usuario iniciarSesion(String nombreUsuario, String password){
        Usuario.usuarioLogin = new Usuario();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT * FROM usuario WHERE nombreUsuario = ? AND password = SHA2(?,256)";
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
    
    public static int insertarUsuario(Usuario usuarioRegistro){
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String sentencia = "INSERT INTO usuario "
                        + "(idUsuario,nombreUsuario,password,rol,nombre,apellidoPaterno,apellidoMaterno)\n"
                        + "VALUES (?,?,SHA2(?,256),?,?,?,?);";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, usuarioRegistro.getIdUsuario());
                prepararSentencia.setString(2, usuarioRegistro.getNombreUsuario());
                prepararSentencia.setString(3, usuarioRegistro.getPassword());
                prepararSentencia.setString(4, usuarioRegistro.getRol());
                prepararSentencia.setString(5, usuarioRegistro.getNombre());
                prepararSentencia.setString(6, usuarioRegistro.getApellidoPaterno());
                prepararSentencia.setString(7, usuarioRegistro.getApellidoMaterno());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ?
                        Constantes.CODIGO_OPERACION_CORRECTA :
                        Constantes.CODIGO_OPERACION_DML_FALLIDA;
                conexionBD.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                respuesta = Constantes.CODIGO_ERROR_CONEXIONBD;
            }
        } else {
            respuesta = Constantes.CODIGO_ERROR_CONEXIONBD;
        }
        return respuesta;
    }
}
