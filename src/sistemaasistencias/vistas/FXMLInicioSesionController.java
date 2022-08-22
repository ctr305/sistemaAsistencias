/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 21/08/22
 * Descripcion: Controlador de la ventana de incio de sesión
 */
package sistemaasistencias.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField tfNombreUsuario;
    @FXML
    private PasswordField pfPassword;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void btnInicioSesion(ActionEvent event) {
    }
    
}
