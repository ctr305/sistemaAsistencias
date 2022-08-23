/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de asistentes
 */
package sistemaasistencias.vistas;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaasistencias.modelo.DAO.AsistenciaDAO;
import sistemaasistencias.modelo.POJO.Asistencia;
import sistemaasistencias.modelo.POJO.Clase;
import sistemaasistencias.modelo.POJO.Usuario;
import sistemaasistencias.util.Utilidades;

public class FXMLAsistentesController implements Initializable {

    @FXML
    private TableView<Usuario> tbAsistentes;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colIdentificador;
    @FXML
    private TableColumn colRol;
    @FXML
    private TableColumn colAsistencia;
    @FXML
    private Label lbAsistentes;
    
    private Clase clase;
    
    private ArrayList<Usuario> asistentes;
    
    private ObservableList<Usuario> infoAsistentes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
    }    

    public void setClase(Clase clase) {
        this.clase = clase;
        configurarLabel();
    }

    public void setAsistentes(ArrayList<Usuario> asistentes) {
        this.asistentes = asistentes;
        cargarInformacion();
    }
    
    private void configurarLabel(){
        String asistentesLabel = "Asistentes de la clase: " + clase.getFecha();
        lbAsistentes.setText(asistentesLabel);
    }

    private void configurarColumnas(){
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colIdentificador.setCellValueFactory(new PropertyValueFactory("idUsuario"));
        colRol.setCellValueFactory(new PropertyValueFactory("rol"));
        colAsistencia.setCellValueFactory(new PropertyValueFactory("asistio"));
        colNombre.setEditable(false);
        colIdentificador.setEditable(false);
        colRol.setEditable(false);
        colAsistencia.setEditable(false);
        infoAsistentes = FXCollections.observableArrayList();
    }
    
    private void cargarInformacion(){
        ArrayList<Asistencia> asistencias = AsistenciaDAO.getAsistencias(clase);
        
        if(asistencias != null){
            for(Usuario u : asistentes){
                CheckBox ckAsistencia = new CheckBox();
                ckAsistencia.setSelected(false);
                for(Asistencia a : asistencias){
                    if(a.getIdUsuario().equals(u.getIdUsuario())){
                        ckAsistencia.setSelected(true);
                    }
                }
                u.setAsistio(ckAsistencia);
            }
        
            infoAsistentes.addAll(asistentes);
            tbAsistentes.setItems(infoAsistentes);
        }else{
            Utilidades.mostrarAlerta("Error de conexion", 
                        "No existe conexion con la base de datos.",
                        Alert.AlertType.ERROR);
            Logger.getLogger(FXMLAsistentesController.class.getName()).log(Level.SEVERE, null, new RuntimeException());
        }
    }
}
