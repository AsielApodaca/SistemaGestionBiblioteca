/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import dtos.LibroDTO;
import entidades.Libro;
import java.util.List;
import logica.BusquedaLibrosServicio;
import logica.GestionLibroServicio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class BusquedaLibrosServicioTest {

    private GestionLibroServicio gestionLibrosServicio;
    private BusquedaLibrosServicio busquedaLibrosServicio;

    @BeforeEach
    public void setUp() {
        gestionLibrosServicio = new GestionLibroServicio();
        busquedaLibrosServicio = new BusquedaLibrosServicio();

        // Agregar libros al catálogo para las pruebas
        gestionLibrosServicio.agregarLibros();
        /*
        'Cien años de soledad', 'Gabriel García Márquez', '978-0307474728', true
	'Don Quijote de la Mancha', 'Miguel de Cervantes', '978-8420412146', true
	'1984', 'George Orwell', '978-0451524935', false
	'El principito', 'Antoine de Saint-Exupéry', '978-0156012195', true
	'Rayuela', 'Julio Cortázar', '978-8437604572', false
	'La casa de los espíritus', 'Isabel Allende', '978-0525433477', true
	'El laberinto de la soledad', 'Octavio Paz', '978-9681603011', true
	'Pedro Páramo', 'Juan Rulfo', '978-0802133908', false
	'La sombra del viento', 'Carlos Ruiz Zafón', '978-0143034902', true
	'Los detectives salvajes', 'Roberto Bolaño', '978-0312427481', true
        */
    }

    @Test
    public void testBuscarLibroPorTitulo() {
        LibroDTO dto = new LibroDTO();
        dto.setTitulo("Don Quijote de la Mancha");
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorTitulo(dto);

        assertEquals(1, resultados.size(), "Debería encontrar un libro con el título 'Don Quijote de la Mancha'");
        assertEquals("Don Quijote de la Mancha", resultados.get(0).getTitulo(), "El título del libro encontrado debería ser 'Don Quijote de la Mancha'");
    }

    @Test
    public void testBuscarLibroPorAutor() {
        LibroDTO dto = new LibroDTO();
        dto.setAutor("Gabriel García Márquez");
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorAutor(dto);

        assertEquals(1, resultados.size(), "Debería encontrar un libro del autor 'Gabriel García Márquez'");
        assertEquals("Cien años de soledad", resultados.get(0).getTitulo(), "El título del libro encontrado debería ser 'Cien años de soledad'");
    }

    @Test
    public void testBuscarLibroPorIdentificador() {
        LibroDTO dto = new LibroDTO();
        dto.setIsbn("978-0451524935");
        Libro resultado = busquedaLibrosServicio.buscarLibroPorIsbn(dto);

        assertNotNull(resultado, "Debería encontrar un libro con el identificador '978-0451524935'");
        assertEquals("1984", resultado.getTitulo(), "El título del libro encontrado debería ser '1984'");
    }

    @Test
    public void testBuscarLibroNoExistentePorTitulo() {
        LibroDTO dto = new LibroDTO();
        dto.setTitulo("Don Quijote");
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorTitulo(dto);

        assertNull(resultados, "No debería encontrar ningún libro con el título 'Don Quijote'");
    }

    @Test
    public void testBuscarLibroNoExistentePorAutor() {
        LibroDTO dto = new LibroDTO();
        dto.setAutor("Autor Desconocido");
        List<Libro> resultados = busquedaLibrosServicio.buscarLibrosPorAutor(dto);

        assertNull(resultados, "No debería encontrar ningún libro del autor 'Autor Desconocido'");
    }

    @Test
    public void testBuscarLibroNoExistentePorIdentificador() {
        LibroDTO dto = new LibroDTO();
        dto.setIsbn("000-1111111111");
        Libro resultado = busquedaLibrosServicio.buscarLibroPorIsbn(dto);

        assertNull(resultado, "No debería encontrar ningún libro con el identificador '000-1111111111'");
    }
}