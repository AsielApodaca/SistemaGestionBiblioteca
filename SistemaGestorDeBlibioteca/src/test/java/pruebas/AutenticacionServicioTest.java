package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class AutenticacionServicioTest {
    @Test
    public void testInicioSesionExitoso() {
        AutenticacionServicio servicio = new AutenticacionServicio();
        servicio.registrarBibliotecario("admin", "password123");

        boolean resultado = servicio.iniciarSesion("admin", "password123");

        assertTrue(resultado, "El inicio de sesión debería ser exitoso con credenciales válidas");
    }

    @Test
    public void testInicioSesionFallido_UsuarioInexistente() {
        AutenticacionServicio servicio = new AutenticacionServicio();

        boolean resultado = servicio.iniciarSesion("invalido", "password");

        assertFalse(resultado, "El inicio de sesión debería fallar para un usuario inexistente");
    }

    @Test
    public void testInicioSesionFallido_ContrasenaIncorrecta() {
        AutenticacionServicio servicio = new AutenticacionServicio();
        servicio.registrarBibliotecario("admin", "password123");

        boolean resultado = servicio.iniciarSesion("admin", "wrongpassword");

        assertFalse(resultado, "El inicio de sesión debería fallar si la contraseña es incorrecta");
    }
}
