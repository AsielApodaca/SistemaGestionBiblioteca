/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class PrestamoDevolucionServicioTest {

//    private GestionLibrosServicio gestionLibrosServicio;
//    private GestionUsuariosServicio gestionUsuariosServicio;
//    private PrestamoDevolucionServicio prestamoDevolucionServicio;
//
//    @BeforeEach
//    public void setUp() {
//        gestionLibrosServicio = new GestionLibrosServicio();
//        gestionUsuariosServicio = new GestionUsuariosServicio();
//        prestamoDevolucionServicio = new PrestamoDevolucionServicio(gestionLibrosServicio, gestionUsuariosServicio);
//
//        // Configuramos datos iniciales para las pruebas
//        Usuario usuario = new Usuario("123", "Juan Perez", "juan@correo.com");
//        gestionUsuariosServicio.registrarUsuario(usuario);
//
//        Libro libro = new Libro("001", "El Quijote", "Miguel de Cervantes", true);
//        gestionLibrosServicio.agregarLibro(libro);
//    }
//
//    @Test
//    public void testPrestamoLibroExitoso() {
//        // Obtener libro y usuario para préstamo
//        Usuario usuario = gestionUsuariosServicio.obtenerUsuarioPorId("123");
//        Libro libro = gestionLibrosServicio.obtenerLibroPorId("001");
//
//        // Realizar préstamo
//        boolean resultado = prestamoDevolucionServicio.prestarLibro(usuario, libro);
//
//        assertTrue(resultado, "El préstamo del libro debería ser exitoso");
//        assertFalse(libro.isDisponible(), "El libro debería estar marcado como no disponible después del préstamo");
//        assertEquals(usuario, libro.getUsuarioPrestado(), "El libro debería estar asociado al usuario que realizó el préstamo");
//    }
//
//    @Test
//    public void testPrestamoLibroInexistente() {
//        // Obtener usuario
//        Usuario usuario = gestionUsuariosServicio.obtenerUsuarioPorId("123");
//
//        // Intentar prestar un libro que no existe
//        Libro libroInexistente = new Libro("999", "Libro Inexistente", "Autor Desconocido", true);
//        boolean resultado = prestamoDevolucionServicio.prestarLibro(usuario, libroInexistente);
//
//        assertFalse(resultado, "No debería permitir prestar un libro que no existe");
//    }
//
//    @Test
//    public void testPrestamoLibroYaPrestado() {
//        // Obtener libro y usuario para préstamo
//        Usuario usuario1 = gestionUsuariosServicio.obtenerUsuarioPorId("123");
//        Libro libro = gestionLibrosServicio.obtenerLibroPorId("001");
//
//        // Primer préstamo exitoso
//        prestamoDevolucionServicio.prestarLibro(usuario1, libro);
//
//        // Intentar prestar el mismo libro a otro usuario
//        Usuario usuario2 = new Usuario("124", "Ana Gomez", "ana@correo.com");
//        gestionUsuariosServicio.registrarUsuario(usuario2);
//
//        boolean resultado = prestamoDevolucionServicio.prestarLibro(usuario2, libro);
//
//        assertFalse(resultado, "No debería permitir prestar un libro que ya está prestado");
//    }
//
//    @Test
//    public void testDevolverLibroExitoso() {
//        // Obtener libro y usuario para préstamo
//        Usuario usuario = gestionUsuariosServicio.obtenerUsuarioPorId("123");
//        Libro libro = gestionLibrosServicio.obtenerLibroPorId("001");
//
//        // Realizar préstamo primero
//        prestamoDevolucionServicio.prestarLibro(usuario, libro);
//
//        // Realizar devolución
//        boolean resultado = prestamoDevolucionServicio.devolverLibro(libro);
//
//        assertTrue(resultado, "La devolución del libro debería ser exitosa");
//        assertTrue(libro.isDisponible(), "El libro debería estar marcado como disponible después de la devolución");
//        assertNull(libro.getUsuarioPrestado(), "El libro debería dejar de estar asociado a un usuario después de la devolución");
//    }
//
//    @Test
//    public void testDevolverLibroNoPrestado() {
//        // Obtener libro que no está prestado
//        Libro libro = gestionLibrosServicio.obtenerLibroPorId("001");
//
//        // Intentar devolver un libro que no ha sido prestado
//        boolean resultado = prestamoDevolucionServicio.devolverLibro(libro);
//
//        assertFalse(resultado, "No debería permitir devolver un libro que no ha sido prestado");
//    }
}