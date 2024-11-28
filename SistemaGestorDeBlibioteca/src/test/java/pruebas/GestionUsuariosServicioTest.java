/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import dtos.UsuarioDTO;
import entidades.Usuario;
import logica.GestionUsuarioServicio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class GestionUsuariosServicioTest {
    private GestionUsuarioServicio servicio;
    
    @BeforeEach
    public void setUp(){
        servicio = new GestionUsuarioServicio();
        
        servicio.buscarUsuarios();
        /*Usuarios agregados
        'Ana García', 'ana.garcia@email.com'
        'Carlos Rodríguez', 'carlos.rodriguez@email.com'
        'María López', 'maria.lopez@email.com'
        'Juan Martínez', 'juan.martinez@email.com'
        'Laura Sánchez', 'laura.sanchez@email.com'
        'Roberto Fernández', 'roberto.fernandez@email.com'
        'Patricia Torres', 'patricia.torres@email.com'
        'Miguel Ramírez', 'miguel.ramirez@email.com'
        'Isabel Castro', 'isabel.castro@email.com'
        'Diego Morales', 'diego.morales@email.com'
        */
    }
    
    @Test
    public void testRegistrarUsuarioExitoso() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setEmail("luisa.morales@email.com");
        usuario.setNombre("Luisa Morales");

        boolean resultado = servicio.registrarUsuario(usuario);

        assertTrue(resultado, "El registro de usuario debería ser exitoso");
        assertEquals(11, servicio.buscarUsuarios().size(), "Debería haber 11 usuarios registrados");
    }

    @Test
    public void testRegistrarUsuarioExistente() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setEmail("maria.lopez@email.com");
        usuario.setNombre("María López");
        // Intentar registrar el mismo usuario nuevamente
        boolean resultado = servicio.registrarUsuario(usuario);

        assertFalse(resultado, "No debería permitir registrar un usuario con el mismo correo");
    }

    @Test
    public void testActualizarUsuario() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setEmail("carlos.rodriguez@email.com");
        
        // Actualizar información del usuario
        usuario.setNombre("Carlos Rodríguez Gómez");
        boolean resultado = servicio.modificarUsuario(usuario);

        assertTrue(resultado, "La actualización del usuario debería ser exitosa");
        assertEquals("Carlos Rodríguez Gómez", servicio.buscarUsuario(usuario).getNombre(), "El nombre del usuario debería ser actualizado");
    }

    @Test
    public void testActualizarUsuarioInexistente() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre("Carlos Pérez");
        usuario.setEmail("carlitos@correo.com");
        // Intentar actualizar un usuario que no existe
        boolean resultado = servicio.modificarUsuario(usuario);

        assertFalse(resultado, "No debería permitir actualizar un usuario inexistente");
    }

    @Test
    public void testObtenerUsuario() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setEmail("ana.garcia@email.com");
        Usuario usuarioObtenido = servicio.buscarUsuario(usuario);

        assertNotNull(usuarioObtenido, "Debería devolver un usuario válido");
        assertEquals("Ana García", usuarioObtenido.getNombre(), "El nombre del usuario debería ser el mismo");
    }
}
