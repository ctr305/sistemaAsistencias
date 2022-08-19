/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 18/08/22
 * Descripcion: DAO de la clase ExperienciaEducativa
 */
package sistemaasistencias.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemaasistencias.modelo.ConexionBD;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;

public class ExperienciaEducativaDAO {
    public static ExperienciaEducativa getExperienciaEducativa(String NRC){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        ExperienciaEducativa experienciaEducativaRetorno = new ExperienciaEducativa();
        if(conexionBD != null){
            String consulta = "SELECT * FROM experienciaEducativa WHERE NRC = ?";
            try{
                PreparedStatement configurarConsulta = conexionBD.prepareStatement(consulta);
                configurarConsulta.setString(1, NRC);
                ResultSet resultadoConsulta = configurarConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    experienciaEducativaRetorno.setNRC(resultadoConsulta.getString("NRC"));
                    experienciaEducativaRetorno.setNombre(resultadoConsulta.getString("nombre"));
                }
                conexionBD.close();
            } catch (SQLException e){
                e.printStackTrace();
                experienciaEducativaRetorno = null;
            }
        } else {
            experienciaEducativaRetorno = null;
        }
        return experienciaEducativaRetorno;
    }
}
