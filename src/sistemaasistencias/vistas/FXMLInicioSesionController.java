/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 21/08/22
 * Descripcion: Controlador de la ventana de incio de sesión
 */
package sistemaasistencias.vistas;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        
        if(ValidarCampos(usuario, password)){
            AbrirVentanaPrincipal();
        }
    }

    private boolean ValidarCampos(String usuario, String password) {
        Usuario.usuarioLogin = UsuarioDAO.IniciarSesion(usuario, password);
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
                break;
            default:
                Utilidades.mostrarAlerta("Error", 
                        "Ocurrio un error desconocido.", 
                        Alert.AlertType.ERROR);
                Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, new RuntimeException());
        }
        return resultado;
    }

    private void AbrirVentanaPrincipal() {
        //TODO
    }
    
}
