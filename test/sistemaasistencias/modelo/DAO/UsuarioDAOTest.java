/*
 * Autor: Leonardo Criollo Ramirez
 * Fecha de creacion: 19/08/22
 * Fecha de modificacion: 19/08/22
 * Descripcion: Prueba del DAO de la clase Usuario
 */
package sistemaasistencias.modelo.DAO;

import org.junit.Test;
import static org.junit.Assert.*;
import proyectoconstruccion.util.Constantes;
import sistemaasistencias.modelo.POJO.Usuario;

public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }

    @Test
    public void testIniciarSesion() {
        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setIdUsuario("S00000000");
        usuarioEsperado.setNombreUsuario("tobias22");
        usuarioEsperado.setPassword("1234");
        usuarioEsperado.setRol("Estudiante");
        usuarioEsperado.setNombre("Tobias");
        usuarioEsperado.setApellidoPaterno("Smith");
        usuarioEsperado.setApellidoMaterno("Hernandez");
        
        System.out.println("ID esperado: "+usuarioEsperado.getIdUsuario());
        System.out.println("Nombre de usuario esperado: "+usuarioEsperado.getNombreUsuario());
        System.out.println("Password esperado: "+usuarioEsperado.getPassword());
        System.out.println("Rol esperado: "+usuarioEsperado.getRol());
        System.out.println("Nombre esperado: "+usuarioEsperado.getNombre());
        System.out.println("Apellido Paterno esperado: "+usuarioEsperado.getApellidoPaterno());
        System.out.println("APellido Materno esperado: "+usuarioEsperado.getApellidoMaterno()+"\n");
        
        String nombreUsuario = "tobias22";
        String password = "1234";
        Usuario usuarioResultado = UsuarioDAO.IniciarSesion(nombreUsuario, password);
        
        assertEquals(Constantes.CODIGO_OPERACION_CORRECTA, usuarioResultado.getCodigoRespuesta());
        
        System.out.println("ID esperado: "+usuarioResultado.getIdUsuario());
        System.out.println("Nombre de usuario esperado: "+usuarioResultado.getNombreUsuario());
        System.out.println("Password esperado: "+usuarioResultado.getPassword());
        System.out.println("Rol esperado: "+usuarioResultado.getRol());
        System.out.println("Nombre esperado: "+usuarioResultado.getNombre());
        System.out.println("Apellido Paterno esperado: "+usuarioResultado.getApellidoPaterno());
        System.out.println("APellido Materno esperado: "+usuarioResultado.getApellidoMaterno());
    }
}
