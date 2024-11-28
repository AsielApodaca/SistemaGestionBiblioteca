package pruebas;

import dtos.LibroDTO;
import entidades.Libro;
import logica.BusquedaLibrosServicio;
import logica.GestionLibroServicio;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class GestionLibrosServicioTest {
    private GestionLibroServicio servicio;
    private BusquedaLibrosServicio servicioBusqueda;
    @BeforeEach
    public void setUp(){
        servicio = new GestionLibroServicio();
        servicioBusqueda = new BusquedaLibrosServicio();
        servicio.agregarLibros();
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
    public void testAgregarLibroExitoso() {
        LibroDTO libro = new LibroDTO();
        libro.setAutor("Jorge Luis Borges");
        libro.setDisponible(true);
        libro.setIsbn("978-0802130303");
        libro.setTitulo("Ficciones");

        boolean resultado = servicio.agregarLibro(libro);

        assertFalse(resultado, "El libro debería agregarse correctamente al catálogo");
        assertNotNull(servicioBusqueda.buscarLibroPorIsbn(libro), "Debería estar el libro con isbn '978-0802130303' en el catálogo");
    }
    
    @Test
    public void testAgregarLibroExistente() {
        LibroDTO libro = new LibroDTO();
        libro.setAutor("Jorge Luis Borges");
        libro.setDisponible(true);
        libro.setIsbn("978-0802130303");
        libro.setTitulo("Ficciones");

        // Intentar agregar el mismo libro nuevamente
        boolean resultado = servicio.agregarLibro(libro);

        assertTrue(resultado, "No debería permitir agregar un libro con el mismo isbn");
    }
    
    @Test
    public void testModificarLibroExitoso() {
        LibroDTO libro = new LibroDTO();
        libro.setAutor("Isabel Allende");
        libro.setIsbn("978-0525433477");
        
        // Modificar el libro
        libro.setTitulo("La casa del espíritu");
        
        boolean resultado = servicio.modificarLibro(libro);

        assertTrue(resultado, "La modificación del libro debería ser exitosa");
        assertEquals("La casa del espíritu", servicioBusqueda.buscarLibroPorIsbn(libro).getTitulo(), "El título del libro debería ser actualizado");
    }

    @Test
    public void testModificarLibroInexistente() {
        LibroDTO libro = new LibroDTO();
        libro.setAutor("Isabel Allende");
        libro.setIsbn("978-0522433498");
        libro.setTitulo("Más allá del invierno");

        // Intentar modificar un libro inexistente
        boolean resultado = servicio.modificarLibro(libro);

        assertFalse(resultado, "No debería permitir modificar un libro que no existe");
    }

    @Test
    public void testEliminarLibroExitoso() {
        LibroDTO libro = new LibroDTO();
        libro.setAutor("Julio Cortázar");
        libro.setIsbn("978-8437604572");
        libro.setTitulo("Rayuela");
        // Eliminar el libro
        boolean resultado = servicio.eliminarLibro(libro);

        assertTrue(resultado, "El libro debería eliminarse correctamente");
        assertNull(servicioBusqueda.buscarLibroPorIsbn(libro), "No debería encontrar el libro que se eliminó");
    }

    @Test
    public void testEliminarLibroInexistente() {
        LibroDTO libro = new LibroDTO();
        libro.setAutor("Isabel Allende");
        libro.setIsbn("978-0522433498");
        libro.setTitulo("Más allá del invierno");
        // Intentar eliminar un libro inexistente
        boolean resultado = servicio.eliminarLibro(libro);

        assertFalse(resultado, "No debería permitir eliminar un libro que no existe");
    }
}
