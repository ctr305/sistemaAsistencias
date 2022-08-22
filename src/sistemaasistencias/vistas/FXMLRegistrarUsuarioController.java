/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 22/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de registro de usuario.
 */
package sistemaasistencias.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemaasistencias.modelo.DAO.UsuarioDAO;
import sistemaasistencias.modelo.POJO.Usuario;
import sistemaasistencias.util.Constantes;
import sistemaasistencias.util.Utilidades;


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
    private ComboBox<String> cbRol;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfConfirmarPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarCampos();
    }    

    @FXML
    private void btnAceptar(ActionEvent event) {
        if(comprobarCampos()){
            insertarUsuario();
            vaciarCampos();
        } else {
            Utilidades.mostrarAlerta("Error",
                    "Uno o mas campos no han sido llenados correctamente.",
                    Alert.AlertType.WARNING);
            pfPassword.clear();
            pfConfirmarPassword.clear();
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    private void configurarCampos(){
        ObservableList<String> roles = FXCollections.observableArrayList();
        roles.add("Estudiante");
        roles.add("Profesor");
        cbRol.setItems(roles);
    }
    
    private void cerrarVentana(){
        Stage escenario = (Stage) tfIdentificador.getScene().getWindow();
        escenario.close();
    }
    
    
     private boolean comprobarCampos() {
        boolean camposCorrectos = true;
        if(tfIdentificador.getText().isEmpty() || tfIdentificador.getText().matches("^.*\\s+.*$")){
            camposCorrectos = false;
        }
        if(tfNombreUsuario.getText().isEmpty() || tfNombreUsuario.getText().matches("^.*\\s+.*$")){
            camposCorrectos = false;
        }
        if(pfPassword.getText().isEmpty() || pfConfirmarPassword.getText().isEmpty()){
            camposCorrectos = false;
        }
        if(!pfPassword.getText().equals(pfConfirmarPassword.getText())){
            camposCorrectos = false;
        }
        if(tfNombre.getText().isEmpty() || tfNombre.getText().matches("^[0-9]+$")){
            camposCorrectos = false;
        }
        if(tfApellidoPaterno.getText().isEmpty() || tfApellidoPaterno.getText().matches("^[0-9]+$")){
            camposCorrectos = false;
        }
        if(tfApellidoMaterno.getText().isEmpty() || tfApellidoPaterno.getText().matches("^[0-9]+$")){
            camposCorrectos = false;
        }
        if(cbRol.getSelectionModel().isEmpty()){
            camposCorrectos = false;
        }
        return camposCorrectos;
    }
     
     private void insertarUsuario(){
         Usuario usuarioRegistro = new Usuario();
         usuarioRegistro.setIdUsuario(tfIdentificador.getText());
         usuarioRegistro.setNombreUsuario(tfNombreUsuario.getText());
         usuarioRegistro.setPassword(pfPassword.getText());
         usuarioRegistro.setRol(cbRol.getSelectionModel().getSelectedItem());
         usuarioRegistro.setNombre(tfNombre.getText());
         usuarioRegistro.setApellidoPaterno(tfApellidoMaterno.getText());
         usuarioRegistro.setApellidoMaterno(tfApellidoMaterno.getText());
         
         switch(UsuarioDAO.insertarUsuario(usuarioRegistro)){
            case Constantes.CODIGO_OPERACION_CORRECTA:
                 Utilidades.mostrarAlerta("Operación Correcta",
                         "El usuario se ha registrado de forma correcta.",
                         Alert.AlertType.INFORMATION);
                 break;
            case Constantes.CODIGO_ERROR_CONEXIONBD:
                Utilidades.mostrarAlerta("Error de conexion", 
                        "No existe conexion con la base de datos.",
                        Alert.AlertType.ERROR);
                break;
            default:
                Utilidades.mostrarAlerta("Error", 
                        "Ocurrió un error desconocido.", 
                        Alert.AlertType.ERROR);
                Logger.getLogger(FXMLRegistrarUsuarioController.class.getName()).log(Level.SEVERE, null, new RuntimeException());
         }
     }
     
    private void vaciarCampos(){
        tfIdentificador.clear();
        tfNombreUsuario.clear();
        tfNombre.clear();
        tfApellidoPaterno.clear();
        tfApellidoMaterno.clear();
        cbRol.getSelectionModel().clearSelection();
        pfPassword.clear();
        pfConfirmarPassword.clear();
     }
}
