/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 18/08/22
 * Descripcion: POJO de la clase ExperienciaEducativa
 */
package sistemaasistencias.modelo.POJO;

public class ExperienciaEducativa {

    private String NRC;
    private String nombre;

    public String getNRC() {
        return NRC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}