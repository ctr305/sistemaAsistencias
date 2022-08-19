/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 18/08/22
 * Descripcion: POJO de la clase Asistencia
 */
package sistemaasistencias.modelo.POJO;

public class Horario {

    private int idHorario;
    private String NRC;
    private String dia;
    private String horas;

    public int getIdHorario() {
        return idHorario;
    }

    public String getNRC() {
        return NRC;
    }

    public String getDia() {
        return dia;
    }

    public String getHoras() {
        return horas;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
}