/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 21/08/22
 * Fecha de modificacion: 21/08/22
 * Descripcion: Prueba del DAO de Horario
 */
package sistemaasistencias.modelo.DAO;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import sistemaasistencias.modelo.POJO.ExperienciaEducativa;
import sistemaasistencias.modelo.POJO.Horario;

public class HorarioDAOTest {
    
    public HorarioDAOTest() {
    }

    @Test
    public void testGetHorariosExito() {
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNRC("80773");
        
        ArrayList<Horario> expResult = new ArrayList<>();
        Horario horarioExp = new Horario();
        horarioExp.setIdHorario(1);
        horarioExp.setNRC("80773");
        horarioExp.setDia("Lunes");
        horarioExp.setHoras("9:00 - 11:00");
        expResult.add(horarioExp);
        
        ArrayList<Horario> result = HorarioDAO.getHorarios(experienciaEducativa);
        assertEquals(expResult.get(0).getIdHorario(), result.get(0).getIdHorario());
        assertEquals(expResult.get(0).getNRC(), result.get(0).getNRC());
        assertEquals(expResult.get(0).getDia(), result.get(0).getDia());
        assertEquals(expResult.get(0).getHoras(), result.get(0).getHoras());
    }
    
    @Test
    public void testGetHorariosFallo() {
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNRC("80773");
        
        ArrayList<Horario> expResult = new ArrayList<>();
        Horario horarioExp = new Horario();
        horarioExp.setIdHorario(0);
        horarioExp.setNRC("80774");
        horarioExp.setDia("Martes");
        horarioExp.setHoras("11:00 - 13:00");
        expResult.add(horarioExp);
        
        ArrayList<Horario> result = HorarioDAO.getHorarios(experienciaEducativa);
        assertNotEquals(expResult.get(0).getIdHorario(), result.get(0).getIdHorario());
        assertNotEquals(expResult.get(0).getNRC(), result.get(0).getNRC());
        assertNotEquals(expResult.get(0).getDia(), result.get(0).getDia());
        assertNotEquals(expResult.get(0).getHoras(), result.get(0).getHoras());
    }
}
