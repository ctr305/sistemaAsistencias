/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de consulta de asistencias
 */
package sistemaasistencias.vistas;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sistemaasistencias.modelo.DAO.ExperienciaEducativaDAO;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;
import sistemaasistencias.util.Utilidades;

public class FXMLConsultarAsistenciasController implements Initializable {

    @FXML
    private TableView<ExperienciaEducativa> tbExperienciasEducativas;
    @FXML
    private TableColumn colNRC;
    @FXML
    private TableColumn colNombre;
    
    private ObservableList<ExperienciaEducativa> infoExperienciasEducativas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        cargarInformacion();
    }    

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void configurarColumnas(){
        colNRC.setCellValueFactory(new PropertyValueFactory("NRC"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        infoExperienciasEducativas = FXCollections.observableArrayList();
    }
    
    private void cargarInformacion(){
        ArrayList<ExperienciaEducativa> resultadoConsulta = ExperienciaEducativaDAO.getExperienciasEducativas();
        if(resultadoConsulta != null){
            infoExperienciasEducativas.clear();
            infoExperienciasEducativas.addAll(resultadoConsulta);
            tbExperienciasEducativas.setItems(infoExperienciasEducativas);
        }else{
            Utilidades.mostrarAlerta("Error de conexion", 
                        "No existe conexion con la base de datos.",
                        Alert.AlertType.ERROR);
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, new RuntimeException());
        }
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage) tbExperienciasEducativas.getScene().getWindow();
        escenario.close();
    }
}
