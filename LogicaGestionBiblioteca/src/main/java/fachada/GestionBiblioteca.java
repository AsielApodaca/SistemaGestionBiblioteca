/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import dtos.BibliotecarioDTO;
import dtos.LibroDTO;
import dtos.UsuarioDTO;
import entidades.Bibliotecario;
import entidades.Libro;
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
                librosDTO.addAll(convertidor.convertirLibros(libros));
            }
            case ISBN -> {
                Libro libro = busquedaLibro.buscarLibroPorIsbn(libroDTO);
                librosDTO.add(convertidor.convertirLibro(libro));
            }
            case TITULO -> {
                List<Libro> libros = busquedaLibro.buscarLibrosPorTitulo(libroDTO);
                librosDTO.addAll(convertidor.convertirLibros(libros));
            }
        }
        return librosDTO;
    }

    @Override
    public List<UsuarioDTO> buscarUsuarios() {
        return convertidor.convertirUsuarios(gestionUsuarios.buscarUsuarios());
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
    
}
