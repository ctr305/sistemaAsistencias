/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de inicio de sesión
 */
package sistemaasistencias.vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sistemaasistencias.util.Utilidades;

public class FXMLMenuController implements Initializable {

    @FXML
    private Label lbMenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void btnRegistrarUsuario(ActionEvent event) {
        abrirRegistroUsuarios();
    }

    @FXML
    private void btnVerAsistencia(ActionEvent event) {
        abrirConsultaAsistencias();
    }
    
    private void abrirRegistroUsuarios() {
        try{
            Stage escenarioPrincipal = (Stage) lbMenu.getScene().getWindow();
            Scene menu = new Scene(FXMLLoader.load(getClass().getResource("FXMLRegistrarUsuario.fxml")));
            escenarioPrincipal.setScene(menu);
            escenarioPrincipal.show();
        } catch(IOException e){
            Utilidades.mostrarAlerta("Error",
                    "Ocurrio un error al mostrar la ventana",
                    Alert.AlertType.ERROR);
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void abrirConsultaAsistencias() {
        try{
            Stage escenarioPrincipal = (Stage) lbMenu.getScene().getWindow();
            Scene menu = new Scene(FXMLLoader.load(getClass().getResource("FXMLConsultarAsistencias.fxml")));
            escenarioPrincipal.setScene(menu);
            escenarioPrincipal.show();
        } catch(IOException e){
            Utilidades.mostrarAlerta("Error",
                    "Ocurrio un error al mostrar la ventana",
                    Alert.AlertType.ERROR);
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
