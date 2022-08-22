/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 21/08/22
 * Fecha de modificacion: 21/08/22
 * Descripcion: Prueba del DAO de Clase
 */
package sistemaasistencias.modelo.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import sistemaasistencias.modelo.POJO.Clase;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;

public class ClaseDAOTest {
    
    public ClaseDAOTest() {
    }

    @Test
    public void testGetClasesDeExperienciaEducativaExito() {
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNRC("80773");
        
        ArrayList<Clase> expResult = new ArrayList<>();
        Clase claseExp = new Clase();
        claseExp.setIdClase(1);
        claseExp.setNRC("80773");
        claseExp.setFecha(LocalDate.parse("2022-08-21"));
        expResult.add(claseExp);
        
        ArrayList<Clase> result = ClaseDAO.getClasesDeExperienciaEducativa(experienciaEducativa);
        assertEquals(expResult.get(0).getIdClase(), result.get(0).getIdClase());
        assertEquals(expResult.get(0).getNRC(), result.get(0).getNRC());
        assertEquals(expResult.get(0).getFecha(), result.get(0).getFecha());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetHorariosFallo() {
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNRC("80774");
        
        ArrayList<Clase> expResult = new ArrayList<>();
        Clase claseExp = new Clase();
        claseExp.setIdClase(0);
        claseExp.setNRC("80774");
        claseExp.setFecha(LocalDate.parse("2022-09-01"));
        expResult.add(claseExp);
        
        ArrayList<Clase> result = ClaseDAO.getClasesDeExperienciaEducativa(experienciaEducativa);
        assertEquals(expResult.get(0).getIdClase(), result.get(0).getIdClase());
        assertEquals(expResult.get(0).getNRC(), result.get(0).getNRC());
        assertEquals(expResult.get(0).getFecha(), result.get(0).getFecha());
    }
}
