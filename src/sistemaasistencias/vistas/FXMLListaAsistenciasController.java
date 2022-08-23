/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de lista de asitencias
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistemaasistencias.modelo.DAO.AsistenciaDAO;
import sistemaasistencias.modelo.DAO.ClaseDAO;
import sistemaasistencias.modelo.DAO.HorarioDAO;
import sistemaasistencias.modelo.DAO.UsuarioDAO;
import sistemaasistencias.modelo.POJO.Asistencia;
import sistemaasistencias.modelo.POJO.Clase;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;
import sistemaasistencias.modelo.POJO.Horario;
import sistemaasistencias.modelo.POJO.Usuario;
import sistemaasistencias.util.Utilidades;

public class FXMLListaAsistenciasController implements Initializable {

    @FXML
    private Label lbNombreEE;
    @FXML
    private Label lbHorario;
    @FXML
    private TableView tbClases;
    @FXML
    private TableColumn colClase;
    
    private ExperienciaEducativa experienciaEducativa;
    
    private ObservableList<Clase> infoClases;
    
    ArrayList<Usuario> asistentes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
    }    

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
        configurarLabels();
        cargarInformacion();
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
    
    private void configurarColumnas(){
        colClase.setCellValueFactory(new PropertyValueFactory("fecha"));
        infoClases = FXCollections.observableArrayList();
    }
    
    private void cargarInformacion(){
        ArrayList<Clase> resultadoConsulta = ClaseDAO.getClasesDeExperienciaEducativa(experienciaEducativa);
        if(resultadoConsulta != null){
            asistentes = new ArrayList<>();
            
            for(Clase c : resultadoConsulta){
                ArrayList<Asistencia> asistenciaTemp = AsistenciaDAO.getAsistencias(c);
                for(Asistencia a : asistenciaTemp){
                    Usuario usuarioTemp = UsuarioDAO.getUsuario(a.getIdUsuario());
                    if(!asistentes.contains(usuarioTemp)){
                        asistentes.add(usuarioTemp);
                    }
                }
            }
            
            tbClases.getItems().clear();
            infoClases.addAll(resultadoConsulta);
            tbClases.setItems(infoClases);
        } else {
            Utilidades.mostrarAlerta("Error de conexion", 
                        "No existe conexion con la base de datos.",
                        Alert.AlertType.ERROR);
            Logger.getLogger(FXMLListaAsistenciasController.class.getName()).log(Level.SEVERE, null, new RuntimeException());
        }
    }

    private void valorSeleccionadoTabla(){
        int filaSeleccionada = tbClases.getSelectionModel().getSelectedIndex();
        if(filaSeleccionada >= 0){
            Clase clase = infoClases.get(filaSeleccionada);
            irAsistentes(clase, asistentes);
        }else{
            Utilidades.mostrarAlerta("Experiencia Educativa no seleccionada",
            "Debes seleccionar una Experiencia Educativa para continuar.", 
            Alert.AlertType.WARNING);
        }
    }

    private void irAsistentes(Clase clase, ArrayList<Usuario> asistentes) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAsistentes.fxml"));
            Parent root = loader.load();
            FXMLAsistentesController controladorAsistencias = loader.getController();
            controladorAsistencias.setClase(clase);
            controladorAsistencias.setAsistentes(asistentes);
            Stage escenarioPrincipal = (Stage) tbClases.getScene().getWindow();
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
    private void clickClase(MouseEvent event) {
        if(event.getClickCount() >= 2){
            valorSeleccionadoTabla();
        }
    }
}
