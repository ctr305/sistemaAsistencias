/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 19/08/22
 * Fecha de modificacion: 19/08/22
 * Descripcion: Prueba del DAO de la clase Usuario
 */
package sistemaasistencias.modelo.DAO;

import org.junit.Test;
import static org.junit.Assert.*;
import sistemaasistencias.modelo.POJO.Usuario;
import sistemaasistencias.util.Constantes;

public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }

    @Test
    public void testIniciarSesionExito() {
        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setIdUsuario("S00000000");
        usuarioEsperado.setNombreUsuario("tobias22");
        usuarioEsperado.setPassword("1234");
        usuarioEsperado.setRol("Estudiante");
        usuarioEsperado.setNombre("Tobias");
        usuarioEsperado.setApellidoPaterno("Smith");
        usuarioEsperado.setApellidoMaterno("Hernandez");
        
        String nombreUsuario = "tobias22";
        String password = "1234";
        Usuario usuarioResultado = UsuarioDAO.IniciarSesion(nombreUsuario, password);
        
        assertEquals(Constantes.CODIGO_OPERACION_CORRECTA, usuarioResultado.getCodigoRespuesta());
    }
    
    @Test
    public void testIniciarSesionFallido() {
        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setIdUsuario("S00000000");
        usuarioEsperado.setNombreUsuario("tobias22");
        usuarioEsperado.setPassword("1234");
        usuarioEsperado.setRol("Estudiante");
        usuarioEsperado.setNombre("Tobias");
        usuarioEsperado.setApellidoPaterno("Smith");
        usuarioEsperado.setApellidoMaterno("Hernandez");
        
        String nombreUsuario = "tobias23";
        String password = "1234";
        Usuario usuarioResultado = UsuarioDAO.IniciarSesion(nombreUsuario, password);
        
        assertEquals(Constantes.CODIGO_CREDENCIALES_INCORRECTAS, usuarioResultado.getCodigoRespuesta());
    }
    
    @Test
    public void testGetUsuarioExito() {
        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setIdUsuario("S00000000");
        usuarioEsperado.setRol("Estudiante");
        usuarioEsperado.setNombre("Tobias");
        usuarioEsperado.setApellidoPaterno("Smith");
        usuarioEsperado.setApellidoMaterno("Hernandez");
        
        String idUsuario = "S00000000";
        Usuario usuarioResultado = UsuarioDAO.getUsuario(idUsuario);
        
        assertEquals(usuarioEsperado.getIdUsuario(), usuarioResultado.getIdUsuario());
        assertEquals(usuarioEsperado.getNombre(), usuarioResultado.getNombre());
        assertEquals(usuarioEsperado.getApellidoPaterno(), usuarioResultado.getApellidoPaterno());
        assertEquals(usuarioEsperado.getApellidoMaterno(), usuarioResultado.getApellidoMaterno());
    }
    
    @Test
    public void testGetUsuarioFallido() {
        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setIdUsuario("S00000000");
        usuarioEsperado.setRol("Estudiante");
        usuarioEsperado.setNombre("Tobias");
        usuarioEsperado.setApellidoPaterno("Smith");
        usuarioEsperado.setApellidoMaterno("Hernandez");
        
        String idUsuario = "S00000001";
        Usuario usuarioResultado = UsuarioDAO.getUsuario(idUsuario);
        
        assertNotEquals(usuarioEsperado.getIdUsuario(), usuarioResultado.getIdUsuario());
        assertNotEquals(usuarioEsperado.getNombre(), usuarioResultado.getNombre());
        assertNotEquals(usuarioEsperado.getApellidoPaterno(), usuarioResultado.getApellidoPaterno());
        assertNotEquals(usuarioEsperado.getApellidoMaterno(), usuarioResultado.getApellidoMaterno());
    }
    
    @Test
    public void testRegistrarUsuario(){
        Usuario usuarioregistro = new Usuario();
        usuarioregistro.setIdUsuario("testID");
        usuarioregistro.setNombreUsuario("usuarioTest");
        usuarioregistro.setPassword("4321");
        usuarioregistro.setRol("Estudiante");
        usuarioregistro.setNombre("Alvaro");
        usuarioregistro.setApellidoPaterno("Gonzales");
        usuarioregistro.setApellidoMaterno("Ramirez");
        
        assertEquals(Constantes.CODIGO_OPERACION_CORRECTA, UsuarioDAO.insertarUsuario(usuarioregistro));
    }
}
