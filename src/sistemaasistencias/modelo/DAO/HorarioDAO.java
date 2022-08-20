/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 20/08/22
 * Fecha de modificacion: 20/08/22
 * Descripcion: DAO de la clase Horario
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
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;
import sistemaasistencias.modelo.POJO.Horario;

public class HorarioDAO {
    public static ArrayList<Horario> getHorarios(ExperienciaEducativa experienciaEducativa){
        ArrayList<Horario> horarios = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            String consulta = "SELECT * FROM horario WHERE NRC = ?";
            try {
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setString(1, experienciaEducativa.getNRC());
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Horario horarioTemp = new Horario();
                    horarioTemp.setIdHorario(resultadoConsulta.getInt("idHorario"));
                    horarioTemp.setNRC(resultadoConsulta.getString("NRC"));
                    horarioTemp.setDia(resultadoConsulta.getString("dia"));
                    horarioTemp.setHoras(resultadoConsulta.getString("horas"));
                    horarios.add(horarioTemp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HorarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                conexionBD.close();
            } catch (SQLException ex) {
                Logger.getLogger(HorarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            horarios = null;
        }
        return horarios;
    }
}
