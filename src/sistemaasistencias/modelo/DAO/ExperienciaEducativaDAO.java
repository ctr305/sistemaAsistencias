/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 19/08/22
 * Descripcion: DAO de la clase ExperienciaEducativa
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

public class ExperienciaEducativaDAO {
    public static ArrayList<ExperienciaEducativa> getExperienciasEducativas(){
        Connection conexionBD = ConexionBD.abrirConexionBD();
        ArrayList<ExperienciaEducativa> experienciasEducativas = new ArrayList<>();
        if(conexionBD != null){
            String consulta = "SELECT * FROM experienciaEducativa";
            try{
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                while(resultadoConsulta.next()){
                    ExperienciaEducativa experienciaEducativaRetorno = new ExperienciaEducativa();
                    experienciaEducativaRetorno.setNRC(resultadoConsulta.getString("NRC"));
                    experienciaEducativaRetorno.setNombre(resultadoConsulta.getString("nombre"));
                    experienciasEducativas.add(experienciaEducativaRetorno);
                }
                conexionBD.close();
            } catch (SQLException e){
                Logger.getLogger(ExperienciaEducativaDAO.class.getName()).log(Level.SEVERE, null, e);
                experienciasEducativas = null;
            }
        } else {
            experienciasEducativas = null;
        }
        return experienciasEducativas;
    }
}
