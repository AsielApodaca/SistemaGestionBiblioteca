/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class BusquedaLibrosServicioTest {

    private GestionLibrosServicio gestionLibrosServicio;
    private BusquedaLibrosServicio busquedaLibrosServicio;

    @BeforeEach
    public void setUp() {
        gestionLibrosServicio = new GestionLibrosServicio();
        busquedaLibrosServicio = new BusquedaLibrosServicio(gestionLibrosServicio);

        // Agregar libros al catálogo para las pruebas
        gestionLibrosServicio.agregarLibro(new Libro("001", "El Quijote", "Miguel de Cervantes", true));
        gestionLibrosServicio.agregarLibro(new Libro("002", "Cien Años de Soledad", "Gabriel García Márquez", true));
        gestionLibrosServicio.agregarLibro(new Libro("003", "1984", "George Orwell", true));
    }

    @Test
    public void testBuscarLibroPorTitulo() {
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorTitulo("El Quijote");

        assertEquals(1, resultados.size(), "Debería encontrar un libro con el título 'El Quijote'");
        assertEquals("El Quijote", resultados.get(0).getTitulo(), "El título del libro encontrado debería ser 'El Quijote'");
    }

    @Test
    public void testBuscarLibroPorAutor() {
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorAutor("Gabriel García Márquez");

        assertEquals(1, resultados.size(), "Debería encontrar un libro del autor 'Gabriel García Márquez'");
        assertEquals("Cien Años de Soledad", resultados.get(0).getTitulo(), "El título del libro encontrado debería ser 'Cien Años de Soledad'");
    }

    @Test
    public void testBuscarLibroPorIdentificador() {
        Libro resultado = busquedaLibrosServicio.buscarLibroPorId("003");

        assertNotNull(resultado, "Debería encontrar un libro con el identificador '003'");
        assertEquals("1984", resultado.getTitulo(), "El título del libro encontrado debería ser '1984'");
    }

    @Test
    public void testBuscarLibroNoExistentePorTitulo() {
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorTitulo("Don Quijote");

        assertTrue(resultados.isEmpty(), "No debería encontrar ningún libro con el título 'Don Quijote'");
    }

    @Test
    public void testBuscarLibroNoExistentePorAutor() {
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorAutor("Autor Desconocido");

        assertTrue(resultados.isEmpty(), "No debería encontrar ningún libro del autor 'Autor Desconocido'");
    }

    @Test
    public void testBuscarLibroNoExistentePorIdentificador() {
        Libro resultado = busquedaLibrosServicio.buscarLibroPorId("999");

        assertNull(resultado, "No debería encontrar ningún libro con el identificador '999'");
    }
}