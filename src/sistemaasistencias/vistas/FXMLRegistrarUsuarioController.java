/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de registro de usuario.
 */
package sistemaasistencias.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class FXMLRegistrarUsuarioController implements Initializable {

    @FXML
    private TextField tfIdentificador;
    @FXML
    private TextField tfNombreUsuario;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private ComboBox<?> cbRol;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfConfirmarContraseña;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void btnAceptar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
