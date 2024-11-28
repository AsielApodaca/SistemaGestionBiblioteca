/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import dtos.DevolucionDTO;
import dtos.LibroDTO;
import dtos.PrestamoDTO;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import logica.BusquedaLibrosServicio;
import logica.GestionLibroServicio;
import logica.GestionUsuarioServicio;
import logica.PrestamoDevolucionServicio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class PrestamoDevolucionServicioTest {

    private GestionLibroServicio gestionLibrosServicio;
    private BusquedaLibrosServicio busquedaLibros;
    private GestionUsuarioServicio gestionUsuariosServicio;
    private PrestamoDevolucionServicio prestamoDevolucionServicio;

    @BeforeEach
    public void setUp() {
        gestionLibrosServicio = new GestionLibroServicio();
        busquedaLibros = new BusquedaLibrosServicio();
        gestionUsuariosServicio = new GestionUsuarioServicio();
        prestamoDevolucionServicio = new PrestamoDevolucionServicio();
        
        gestionLibrosServicio.agregarLibros();
        gestionUsuariosServicio.buscarUsuarios();
        
        /*Usuarios agregados:
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
        
        /*Libros agregados.
        'Cien años de soledad', 'Gabriel García Márquez', '978-0307474728', true
	'Don Quijote de la Mancha', 'Miguel de Cervantes', '978-8420412146', true
	'1984', 'George Orwell', '978-0451524935', false
	'El principito', 'Antoine de Saint-Exupé935ry', '978-0156012195', true
	'Rayuela', 'Julio Cortázar', '978-8437604572', false
	'La casa de los espíritus', 'Isabel Allende', '978-0525433477', true
	'El laberinto de la soledad', 'Octavio Paz', '978-9681603011', true
	'Pedro Páramo', 'Juan Rulfo', '978-0802133908', false
	'La sombra del viento', 'Carlos Ruiz Zafón', '978-0143034902', true
	'Los detectives salvajes', 'Roberto Bolaño', '978-0312427481', true
        */
    }

    @Test
    public void testPrestamoLibroExitoso() {
        // Obtener libro y usuario para préstamo
        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setEmailUsuario("juan.martinez@email.com");
        prestamo.setIsbnLibro("978-9681603011");//El laberinto de la soledad, disponible antes del prestamo
        Calendar fechaLimite = Calendar.getInstance();
        fechaLimite.add(Calendar.MONTH, 1);//1 mes despues del prestamo
        prestamo.setFechaLimite(fechaLimite);
        // Realizar préstamo
        boolean resultado = prestamoDevolucionServicio.registrarPrestamo(prestamo);
        
        LibroDTO libro = new LibroDTO();
        libro.setIsbn("978-9681603011");
        Libro libroPrestado = busquedaLibros.buscarLibroPorIsbn(libro); 
        Prestamo prestamoRealizado = prestamoDevolucionServicio.buscarPrestamoPorLibro(prestamo);
        
        assertTrue(resultado, "El préstamo del libro debería ser exitoso");
        assertFalse(libroPrestado.isDisponible(),"El libro debería estar marcado como no disponible después del préstamo");
        assertEquals(prestamoRealizado.getUsuario().getEmail(), prestamo.getEmailUsuario(), "El prestamo debería estar asociado al usuario que lo realizó");
    }

    @Test
    public void testPrestamoLibroInexistente() {
        // Intentar prestar un libro que no existe
        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setEmailUsuario("miguel.ramirez@email.com");
        prestamo.setIsbnLibro("111-1111112223");
        boolean resultado = prestamoDevolucionServicio.registrarPrestamo(prestamo);

        assertFalse(resultado, "No debería permitir prestar un libro que no existe");
    }

    @Test
    public void testPrestamoLibroYaPrestado() {
        // Intentar prestar un libro no disponible 
        PrestamoDTO prestamo = new PrestamoDTO();
        prestamo.setIsbnLibro("978-0451524935");
        prestamo.setEmailUsuario("patricia.torres@email.com");

        boolean resultado = prestamoDevolucionServicio.registrarPrestamo(prestamo);

        assertFalse(resultado, "No debería permitir prestar un libro que ya está prestado");
    }

    @Test
    public void testDevolverLibroExitoso() {
        DevolucionDTO devolucion = new DevolucionDTO();
        devolucion.setIsbnLibro("978-9681603011");//el laberinto de la soledad

        // Realizar devolución
        boolean resultado = prestamoDevolucionServicio.devolverLibro(devolucion);
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setIsbn("978-9681603011");
        Libro libro = busquedaLibros.buscarLibroPorIsbn(libroDTO);
        
        PrestamoDTO prestamoDTO = new PrestamoDTO();
        prestamoDTO.setIsbnLibro(libro.getIsbn());
        Prestamo prestamo = prestamoDevolucionServicio.buscarPrestamoPorLibro(prestamoDTO);
        
        assertTrue(resultado, "La devolución del libro debería ser exitosa");
        assertTrue(libro.isDisponible(), "El libro debería estar marcado como disponible después de la devolución");
    }

    @Test
    public void testDevolverLibroNoPrestado() {
        DevolucionDTO devolucion = new DevolucionDTO();
        devolucion.setIsbnLibro("978-0312427481");//Los detectives salvajes, actualmente disponible
        
        // Intentar devolver un libro que no ha sido prestado
        boolean resultado = prestamoDevolucionServicio.devolverLibro(devolucion);

        assertFalse(resultado, "No debería permitir devolver un libro que no ha sido prestado");
    }
}