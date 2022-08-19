/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 19/08/22
 * Descripcion: Prueba del DAO de ExperienciaEducativa.
 */
package sistemaasistencias.modelo.DAO;

import org.junit.Test;
import static org.junit.Assert.*;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;


public class ExperienciaEducativaDAOTest {
    
    public ExperienciaEducativaDAOTest() {
    }

    @Test
    public void testGetExperienciaEducativa() {
        String NRC = "80773";
        ExperienciaEducativa esperado = new ExperienciaEducativa();
        esperado.setNRC("80773");
        esperado.setNombre("Derecho Aduanero");
        ExperienciaEducativa resultado = ExperienciaEducativaDAO.getExperienciaEducativa(NRC);
        assertEquals(esperado.getNRC(), resultado.getNRC());
        assertEquals(esperado.getNombre(), resultado.getNombre());
    }
    
    @Test
    public void testGetExperienciaEducativaFallido() {
        String NRC = "80774";
        ExperienciaEducativa esperado = new ExperienciaEducativa();
        esperado.setNRC("80773");
        esperado.setNombre("Derecho Aduanero");
        ExperienciaEducativa resultado = ExperienciaEducativaDAO.getExperienciaEducativa(NRC);
        assertNotEquals(esperado.getNRC(), resultado.getNRC());
        assertNotEquals(esperado.getNombre(), resultado.getNombre());
    }
}
