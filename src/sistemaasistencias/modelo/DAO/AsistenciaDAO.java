/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 20/08/22
 * Fecha de modificacion: 20/08/22
 * Descripcion: DAO de la clase Asistencia
 */
package sistemaasistencias.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemaasistencias.modelo.ConexionBD;
import sistemaasistencias.modelo.POJO.Asistencia;
import sistemaasistencias.modelo.POJO.Clase;

public class AsistenciaDAO {
    public static ArrayList<Asistencia> getAsistencias(Clase clase){
        ArrayList<Asistencia> asistencias = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        try {
            if(conexionBD != null){
                String consulta = "SELECT * FROM asistencia WHERE idClase = ?";
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, clase.getIdClase());
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Asistencia asistenciaTemp = new Asistencia();
                    asistenciaTemp.setIdAsistencia(resultadoConsulta.getInt("idAsistencia"));
                    asistenciaTemp.setIdClase(resultadoConsulta.getInt("idAsistencia"));
                    asistenciaTemp.setIdUsuario(resultadoConsulta.getString("idUsuario"));
                    asistencias.add(asistenciaTemp);
                }
                conexionBD.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asistencias;
    }
}
