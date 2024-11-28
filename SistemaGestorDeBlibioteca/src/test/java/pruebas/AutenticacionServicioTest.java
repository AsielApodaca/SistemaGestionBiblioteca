package pruebas;

import dtos.BibliotecarioDTO;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import logica.AutenticacionServicio;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author asielapodaca
 */
public class AutenticacionServicioTest {
    
    private AutenticacionServicio servicio;
    
    @BeforeEach
    public void setUp(){
        servicio = new AutenticacionServicio();
        System.out.println(servicio.agregarBibliotecarios());
        /*
        registros insertados:
        'biblio_ana', 'ana123secure'
	'biblio_juan', 'juan456secure'
	'biblio_maria', 'maria789secure'
	'biblio_carlos', 'carlos101secure'
	'biblio_laura', 'laura202secure'
        */
    }
    
    @Test
    public void testInicioSesionExitoso() {
        BibliotecarioDTO dto = new BibliotecarioDTO("biblio_juan", "juan456secure");
        
        boolean resultado = servicio.iniciarSesion(dto);

        assertTrue(resultado, "El inicio de sesión debería ser exitoso con credenciales válidas");
    }

    @Test
    public void testInicioSesionFallido_UsuarioInexistente() {
        BibliotecarioDTO dto = new BibliotecarioDTO("invalido", "password");
        
        boolean resultado = servicio.iniciarSesion(dto);

        assertFalse(resultado, "El inicio de sesión debería fallar para un usuario inexistente");
    }

    @Test
    public void testInicioSesionFallido_ContrasenaIncorrecta() {
        BibliotecarioDTO dto = new BibliotecarioDTO("biblio_ana", "wrongpassword");
        
        boolean resultado = servicio.iniciarSesion(dto);

        assertFalse(resultado, "El inicio de sesión debería fallar si la contraseña es incorrecta");
    }
}
