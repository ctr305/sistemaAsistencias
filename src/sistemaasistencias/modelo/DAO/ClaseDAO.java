/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 20/08/22
 * Fecha de modificacion: 20/08/22
 * Descripcion: DAO de la clase 
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
import sistemaasistencias.modelo.POJO.Clase;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;

public class ClaseDAO {
    public static ArrayList<Clase> getClasesDeExperienciaEducativa(ExperienciaEducativa experienciaEducativa){
        ArrayList<Clase> clases = new ArrayList<>();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            String consulta = "SELECT * FROM clase WHERE NRC = ?";
            try {
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setString(1, experienciaEducativa.getNRC());
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    Clase claseTemp = new Clase();
                    claseTemp.setIdClase(resultadoConsulta.getInt("idClase"));
                    claseTemp.setNRC(resultadoConsulta.getString("NRC"));
                    claseTemp.setFecha(resultadoConsulta.getDate("fecha").toLocalDate());
                    clases.add(claseTemp);
                }
                conexionBD.close();
            } catch (SQLException ex) {
                Logger.getLogger(HorarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            clases = null;
        }
        return clases;
    }
}
