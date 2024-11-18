/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class GestionUsuariosServicioTest {
    @Test
    public void testRegistrarUsuarioExitoso() {
        GestionUsuariosServicio servicio = new GestionUsuariosServicio();
        Usuario usuario = new Usuario("123", "Juan Perez", "juan@correo.com");

        boolean resultado = servicio.registrarUsuario(usuario);

        assertTrue(resultado, "El registro de usuario debería ser exitoso");
        assertEquals(1, servicio.obtenerUsuarios().size(), "Debería haber 1 usuario registrado");
    }

    @Test
    public void testRegistrarUsuarioExistente() {
        GestionUsuariosServicio servicio = new GestionUsuariosServicio();
        Usuario usuario = new Usuario("123", "Juan Perez", "juan@correo.com");
        servicio.registrarUsuario(usuario);

        // Intentar registrar el mismo usuario nuevamente
        boolean resultado = servicio.registrarUsuario(usuario);

        assertFalse(resultado, "No debería permitir registrar un usuario con el mismo ID");
    }

    @Test
    public void testActualizarUsuario() {
        GestionUsuariosServicio servicio = new GestionUsuariosServicio();
        Usuario usuario = new Usuario("123", "Juan Perez", "juan@correo.com");
        servicio.registrarUsuario(usuario);

        // Actualizar información del usuario
        usuario.setNombre("Juan Pérez Gómez");
        boolean resultado = servicio.actualizarUsuario(usuario);

        assertTrue(resultado, "La actualización del usuario debería ser exitosa");
        assertEquals("Juan Pérez Gómez", servicio.obtenerUsuarioPorId("123").getNombre(), "El nombre del usuario debería ser actualizado");
    }

    @Test
    public void testActualizarUsuarioInexistente() {
        GestionUsuariosServicio servicio = new GestionUsuariosServicio();
        Usuario usuario = new Usuario("999", "Carlos López", "carlos@correo.com");

        // Intentar actualizar un usuario que no existe
        boolean resultado = servicio.actualizarUsuario(usuario);

        assertFalse(resultado, "No debería permitir actualizar un usuario inexistente");
    }

    @Test
    public void testObtenerUsuario() {
        GestionUsuariosServicio servicio = new GestionUsuariosServicio();
        Usuario usuario = new Usuario("123", "Juan Perez", "juan@correo.com");
        servicio.registrarUsuario(usuario);

        Usuario usuarioObtenido = servicio.obtenerUsuarioPorId("123");

        assertNotNull(usuarioObtenido, "Debería devolver un usuario válido");
        assertEquals("Juan Perez", usuarioObtenido.getNombre(), "El nombre del usuario debería ser el mismo");
    }
}
