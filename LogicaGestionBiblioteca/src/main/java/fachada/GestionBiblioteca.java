
package fachada;

import dtos.BibliotecarioDTO;
import dtos.DevolucionDTO;
import dtos.LibroDTO;
import dtos.PrestamoDTO;
import dtos.UsuarioDTO;
import entidades.Bibliotecario;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import logica.AutenticacionServicio;
import logica.BusquedaLibrosServicio;
import logica.ConversionesServicio;
import logica.GestionLibroServicio;
import logica.GestionUsuarioServicio;
import logica.PrestamoDevolucionServicio;

/**
 *
 * @author luisa M
 */
public class GestionBiblioteca implements FachadaGestionBiblioteca {
    private static GestionBiblioteca instance;
    private AutenticacionServicio autenticacion;
    private BusquedaLibrosServicio busquedaLibro;
    private GestionLibroServicio gestionLibros;
    private GestionUsuarioServicio gestionUsuarios;
    private PrestamoDevolucionServicio gestionPrestamoDevolucion;
    private ConversionesServicio convertidor;
    
    private GestionBiblioteca(){
        autenticacion= new AutenticacionServicio();
        busquedaLibro = new BusquedaLibrosServicio();
        gestionLibros = new GestionLibroServicio();
        gestionUsuarios = new GestionUsuarioServicio();
        gestionPrestamoDevolucion = new PrestamoDevolucionServicio();
        convertidor = new ConversionesServicio();
    }
    
    public static GestionBiblioteca getInstance(){
        if(instance == null)
            instance = new GestionBiblioteca();
        return instance;
    }
    
    @Override
    public boolean iniciarSesion(BibliotecarioDTO bibliotecario) {
        return autenticacion.iniciarSesion(bibliotecario);
    }

    @Override
    public boolean gestionarUsuarios(TipoAccion accion, UsuarioDTO usuario) {
        boolean flag = false;
        switch(accion){
            case AGREGAR -> flag = gestionUsuarios.registrarUsuario(usuario);
            case ELIMINAR -> flag = gestionUsuarios.eliminarUsuario(usuario);
            case ACTUALIZAR -> flag = gestionUsuarios.modificarUsuario(usuario);
        }
        return flag;
    }

    @Override
    public boolean gestionarLibros(TipoAccion accion, LibroDTO libro) {
        boolean flag = false;
        switch(accion){
            case AGREGAR -> flag = gestionLibros.agregarLibro(libro);
            case ELIMINAR -> flag = gestionLibros.eliminarLibro(libro);
            case ACTUALIZAR -> flag = gestionLibros.modificarLibro(libro);
        }
        return flag;
    }

    @Override
    public List<LibroDTO> busquedaLibro(TipoBusqueda criterio, LibroDTO libroDTO) {
        List<LibroDTO> librosDTO = new ArrayList<>();
        switch (criterio) {
            case AUTOR ->{
                List<Libro> libros = busquedaLibro.buscarLibrosPorAutor(libroDTO);
                if(libros != null)
                    librosDTO.addAll(convertidor.convertirLibros(libros));
            }
            case ISBN -> {
                Libro libro = busquedaLibro.buscarLibroPorIsbn(libroDTO);
                if(libro != null)
                    librosDTO.add(convertidor.convertirLibro(libro));
            }
            case TITULO -> {
                List<Libro> libros = busquedaLibro.buscarLibrosPorTitulo(libroDTO);
                if(libros != null)
                    librosDTO.addAll(convertidor.convertirLibros(libros));
            }
        }
        return librosDTO;
    }

    @Override
    public List<UsuarioDTO> buscarUsuarios() {
        List<Usuario> usuarios = gestionUsuarios.buscarUsuarios();
        System.out.println("usuarios del dao: "+usuarios);
        if(usuarios != null)
            return convertidor.convertirUsuarios(usuarios);
        System.out.println("usuarios null");
        return null;
    }

    @Override
    public boolean agregarBibliotecarios() {
        return autenticacion.agregarBibliotecarios();
    }

    @Override
    public List<LibroDTO> buscarLibros() {
        List<Libro> libros = gestionLibros.agregarLibros();
        return convertidor.convertirLibros(libros);
    }

    @Override
    public boolean registrarPrestamo(PrestamoDTO prestamo) {
        return gestionPrestamoDevolucion.registrarPrestamo(prestamo);
    }

    @Override
    public boolean registrarDevolucion(DevolucionDTO devolucion) {
        return gestionPrestamoDevolucion.devolverLibro(devolucion);
    }

    @Override
    public PrestamoDTO buscarPrestamo(PrestamoDTO prestamo) {
        return convertidor.convertirPrestamo(gestionPrestamoDevolucion.buscarPrestamoPorLibro(prestamo));
    }

    @Override
    public List<PrestamoDTO> buscarPrestamos(FiltroBusquedaPrestamo filtro, PrestamoDTO prestamo) {
        List<Prestamo> prestamos = new ArrayList<>();
        switch (filtro) {
            case FiltroBusquedaPrestamo.FECHA_DEVOLUCION -> {prestamos = gestionPrestamoDevolucion.buscarPrestamosPorFechaDevolucion(prestamo);}
            case FiltroBusquedaPrestamo.FECHA_LIMITE -> {prestamos = gestionPrestamoDevolucion.buscarPrestamosPorFechaLimite(prestamo);}
            case FiltroBusquedaPrestamo.FECHA_REGISTRO -> {prestamos = gestionPrestamoDevolucion.buscarPrestamosPorFechaRegistro(prestamo);}
            case FiltroBusquedaPrestamo.USUARIO -> {prestamos = gestionPrestamoDevolucion.buscarPrestamosPorUsuario(prestamo);
            }
        }
        return convertidor.convertirPrestamos(prestamos);
    }
    
    @Override
    public List<PrestamoDTO> buscarPrestamos() {
        return convertidor.convertirPrestamos(gestionPrestamoDevolucion.buscarPrestamos());
    }
    
}
