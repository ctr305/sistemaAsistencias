/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de lista de asitencias
 */
package sistemaasistencias.vistas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import sistemaasistencias.modelo.DAO.HorarioDAO;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;
import sistemaasistencias.modelo.POJO.Horario;

public class FXMLListaAsistenciasController implements Initializable {

    @FXML
    private Label lbNombreEE;
    @FXML
    private Label lbHorario;
    @FXML
    private TableView tbAsistencias;
    
    private ExperienciaEducativa experienciaEducativa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
        configurarLabels();
    }
    
    private void configurarLabels(){
        lbNombreEE.setText(experienciaEducativa.getNombre());
        
        ArrayList<Horario> horarios = HorarioDAO.getHorarios(experienciaEducativa);
        String horariosLabel = "Horario: ";
        for(Horario h : horarios){
            horariosLabel += h.getDia() + ": " + h.getHoras() + ",";
        }
        lbHorario.setText(horariosLabel);
    }
}
