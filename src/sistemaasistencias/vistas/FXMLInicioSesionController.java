/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 22/08/22
 * Descripcion: Controlador de la ventana de incio de sesión
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistemaasistencias.modelo.DAO.UsuarioDAO;
import sistemaasistencias.modelo.POJO.Usuario;
import sistemaasistencias.util.Constantes;
import sistemaasistencias.util.Utilidades;

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
        String usuario = tfNombreUsuario.getText();
        String password = pfPassword.getText();
        pfPassword.clear();
        
        if(validarCampos(usuario, password)){
            abrirVentanaPrincipal();
        }
    }

    private boolean validarCampos(String usuario, String password) {
        Usuario.usuarioLogin = UsuarioDAO.iniciarSesion(usuario, password);
        boolean resultado = false;
        
        switch(Usuario.usuarioLogin.getCodigoRespuesta()){
            case Constantes.CODIGO_OPERACION_CORRECTA:
                resultado = true;
                break;
            case Constantes.CODIGO_CREDENCIALES_INCORRECTAS:
                Utilidades.mostrarAlerta("Error de autenticacion",
                        "Las credenciales introducidas no son correctas.",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.CODIGO_ERROR_CONEXIONBD:
                Utilidades.mostrarAlerta("Error de conexion", 
                        "No existe conexion con la base de datos.",
                        Alert.AlertType.ERROR);
                Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, new RuntimeException());
                break;
            default:
                Utilidades.mostrarAlerta("Error", 
                        "Ocurrio un error desconocido.", 
                        Alert.AlertType.ERROR);
                Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, new RuntimeException());
        }
        return resultado;
    }

    
    private void abrirVentanaPrincipal() {
        try{
            Stage escenarioPrincipal = (Stage) tfNombreUsuario.getScene().getWindow();
            Scene menu = new Scene(FXMLLoader.load(getClass().getResource("FXMLMenu.fxml")));
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
