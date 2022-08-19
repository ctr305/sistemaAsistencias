/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 18/08/22
 * Descripcion: POJO de la clase Clase
 */
package sistemaasistencias.modelo.POJO;

import java.time.LocalDate;

public class Clase {

    private int idClase;
    private String NRC;
    private LocalDate fecha;

    public int getIdClase() {
        return idClase;
    }

    public String getNRC() {
        return NRC;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}