/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 21/08/22
 * Fecha de modificacion: 21/08/22
 * Descripcion: Prueba del DAO de Asistencia
 */
package sistemaasistencias.modelo.DAO;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

import sistemaasistencias.modelo.POJO.Asistencia;
import sistemaasistencias.modelo.POJO.Clase;

public class AsistenciaDAOTest {

    public AsistenciaDAOTest(){
    }
    
    @Test
    public void testGetAsistenciasExito() {
        Clase clase = new Clase();
        clase.setIdClase(1);
        
        ArrayList<Asistencia> expResult = new ArrayList<>();
        Asistencia asistenciaExp = new Asistencia();
        asistenciaExp.setIdAsistencia(1);
        asistenciaExp.setIdClase(1);
        asistenciaExp.setIdUsuario("S00000000");
        expResult.add(asistenciaExp);
        
        ArrayList<Asistencia> result = AsistenciaDAO.getAsistencias(clase);
        assertEquals(expResult.get(0).getIdAsistencia(), result.get(0).getIdAsistencia());
        assertEquals(expResult.get(0).getIdClase(), result.get(0).getIdClase());
        assertEquals(expResult.get(0).getIdUsuario(), result.get(0).getIdUsuario());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetAsistenciasFallo() {
        Clase clase = new Clase();
        clase.setIdClase(2);
        
        ArrayList<Asistencia> expResult = new ArrayList<>();
        Asistencia asistenciaExp = new Asistencia();
        asistenciaExp.setIdAsistencia(0);
        asistenciaExp.setIdClase(2);
        asistenciaExp.setIdUsuario("S00000001");
        expResult.add(asistenciaExp);
        
        ArrayList<Asistencia> result = AsistenciaDAO.getAsistencias(clase);
        assertEquals(expResult.get(0).getIdAsistencia(), result.get(0).getIdAsistencia());
        assertEquals(expResult.get(0).getIdClase(), result.get(0).getIdClase());
        assertEquals(expResult.get(0).getIdUsuario(), result.get(0).getIdUsuario());
    }
 }
