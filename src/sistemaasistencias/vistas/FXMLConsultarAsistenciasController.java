/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de consulta de asistencias
 */
package sistemaasistencias.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    
    private void valorSeleccionadoTabla(){
        int filaSeleccionada = tbExperienciasEducativas.getSelectionModel().getSelectedIndex();
        if(filaSeleccionada >= 0){
            ExperienciaEducativa experienciaEducativa = infoExperienciasEducativas.get(filaSeleccionada);
            irListaAsistencias(experienciaEducativa);
        }else{
            Utilidades.mostrarAlerta("Experiencia Educativa no seleccionada",
            "Debes seleccionar una Experiencia Educativa para continuar.", 
            Alert.AlertType.WARNING);
        }
    }

    private void irListaAsistencias(ExperienciaEducativa experienciaEducativa) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLListaAsistencias.fxml"));
            Parent root = loader.load();
            FXMLListaAsistenciasController controladorAsistencias = loader.getController();
            controladorAsistencias.setExperienciaEducativa(experienciaEducativa);
            Stage escenarioPrincipal = (Stage) tbExperienciasEducativas.getScene().getWindow();
            Scene pantallaAsistencias = new Scene(root);
            escenarioPrincipal.setScene(pantallaAsistencias);
            escenarioPrincipal.show();            
        } catch (IOException ex) {
            Utilidades.mostrarAlerta("Error",
                    "Error al mostrar ventana.",
                    Alert.AlertType.ERROR);
            Logger.getLogger(FXMLConsultarAsistenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickExperienciaEducativa(MouseEvent event) {
        if(event.getClickCount() >= 2){
            valorSeleccionadoTabla();
        }
    }

}
