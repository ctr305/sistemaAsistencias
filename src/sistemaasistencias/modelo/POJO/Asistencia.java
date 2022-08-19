/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 18/08/22
 * Descripcion: POJO de la clase Asistencia
 */
package sistemaasistencias.modelo.POJO;

public class Asistencia {
    
    private int idAsistencia;
    private String idUsuario;
    private int idClase;

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }
}