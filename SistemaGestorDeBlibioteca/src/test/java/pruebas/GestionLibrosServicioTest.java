package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author asielapodaca
 */
public class GestionLibrosServicioTest {
    @Test
    public void testAgregarLibroExitoso() {
        GestionLibrosServicio servicio = new GestionLibrosServicio();
        Libro libro = new Libro("123", "El Quijote", "Miguel de Cervantes", true);

        boolean resultado = servicio.agregarLibro(libro);

        assertTrue(resultado, "El libro debería agregarse correctamente al catálogo");
        assertEquals(1, servicio.obtenerLibros().size(), "Debería haber 1 libro en el catálogo");
    }

    @Test
    public void testAgregarLibroExistente() {
        GestionLibrosServicio servicio = new GestionLibrosServicio();
        Libro libro = new Libro("123", "El Quijote", "Miguel de Cervantes", true);
        servicio.agregarLibro(libro);

        // Intentar agregar el mismo libro nuevamente
        boolean resultado = servicio.agregarLibro(libro);

        assertFalse(resultado, "No debería permitir agregar un libro con el mismo ID");
    }

    @Test
    public void testModificarLibroExitoso() {
        GestionLibrosServicio servicio = new GestionLibrosServicio();
        Libro libro = new Libro("123", "El Quijote", "Miguel de Cervantes", true);
        servicio.agregarLibro(libro);

        // Modificar el libro
        libro.setTitulo("Don Quijote de la Mancha");
        boolean resultado = servicio.modificarLibro(libro);

        assertTrue(resultado, "La modificación del libro debería ser exitosa");
        assertEquals("Don Quijote de la Mancha", servicio.obtenerLibroPorId("123").getTitulo(), "El título del libro debería ser actualizado");
    }

    @Test
    public void testModificarLibroInexistente() {
        GestionLibrosServicio servicio = new GestionLibrosServicio();
        Libro libro = new Libro("999", "El Cid", "Anónimo", true);

        // Intentar modificar un libro inexistente
        boolean resultado = servicio.modificarLibro(libro);

        assertFalse(resultado, "No debería permitir modificar un libro que no existe");
    }

    @Test
    public void testEliminarLibroExitoso() {
        GestionLibrosServicio servicio = new GestionLibrosServicio();
        Libro libro = new Libro("123", "El Quijote", "Miguel de Cervantes", true);
        servicio.agregarLibro(libro);

        // Eliminar el libro
        boolean resultado = servicio.eliminarLibro("123");

        assertTrue(resultado, "El libro debería eliminarse correctamente");
        assertEquals(0, servicio.obtenerLibros().size(), "El catálogo debería estar vacío después de eliminar el libro");
    }

    @Test
    public void testEliminarLibroInexistente() {
        GestionLibrosServicio servicio = new GestionLibrosServicio();

        // Intentar eliminar un libro inexistente
        boolean resultado = servicio.eliminarLibro("999");

        assertFalse(resultado, "No debería permitir eliminar un libro que no existe");
    }
}
