/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 19/08/22
 * Descripcion: POJO de la clase Usuario
 */
package sistemaasistencias.modelo.POJO;

import javafx.scene.control.CheckBox;

public class Usuario {

    public static Usuario usuarioLogin;
    
    private String idUsuario;
    private String nombreUsuario;
    private String password;
    private String rol;
    private int codigoRespuesta;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private CheckBox asistio;

    public Usuario(String idUsuario, String nombreUsuario, String password, String rol, Integer codigoRespuesta) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.codigoRespuesta = codigoRespuesta;
    }

    public Usuario() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public CheckBox getAsistio() {
        return asistio;
    }
    
    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setAsistio(CheckBox asistio) {
        this.asistio = asistio;
    }
}