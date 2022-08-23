/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 18/08/22
 * Fecha de modificacion: 19/08/22
 * Descripcion: Prueba del DAO de ExperienciaEducativa.
 */
package sistemaasistencias.modelo.DAO;

import java.util.ArrayList;
import org.junit.Test;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;


public class ExperienciaEducativaDAOTest {
    
    public ExperienciaEducativaDAOTest() {
    }

    @Test
    public void testGetExperienciasEducativas() {
        ArrayList<ExperienciaEducativa> result = ExperienciaEducativaDAO.getExperienciasEducativas();
        assert(!result.isEmpty());
    }
}
