/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import daos.LibroDAO;
import entidades.Libro;
import dtos.LibroDTO;
import interfaces.ILibroDAO;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class GestionLibroServicio {
    private ILibroDAO libroDAO;
    
    public GestionLibroServicio(){
        libroDAO = new LibroDAO();
    }
    
    public List<Libro> agregarLibros(){
        return libroDAO.agregarLibros();
    }
    
    public boolean agregarLibro(LibroDTO libroDTO){
        if(buscarLibro(libroDTO.getIsbn()) == null){
            Libro libroNuevo = new Libro(libroDTO.getIsbn());
            libroNuevo.setAutor(libroDTO.getAutor());
            libroNuevo.setTitulo(libroDTO.getTitulo());
            return libroDAO.registrarLibro(libroNuevo);
        }
        return false;
    }
    
    public boolean eliminarLibro(LibroDTO libroDTO){
        Libro libro = buscarLibro(libroDTO.getIsbn());
        if (libro != null) {
            return libroDAO.eliminarLibro(libro);
        }
        return false;
    }
    
    public boolean modificarLibro(LibroDTO libroDTO){
        Libro libroActual = buscarLibro(libroDTO.getIsbn());
        if(libroActual != null){
            Libro libroModificado = new Libro(libroDTO.getIsbn());
            libroModificado.setAutor(libroDTO.getAutor());
            libroModificado.setTitulo(libroDTO.getTitulo());
            if(validarModificaciones(libroActual, libroModificado))
                return libroDAO.actualizarLibro(libroModificado);
        }
        return false;
    }
    
    private boolean validarModificaciones(Libro libroActual, Libro libroModificado){
        return !libroActual.getAutor().equals(libroModificado.getAutor()) ||
                !libroActual.getTitulo().equals(libroModificado.getTitulo());
    }
    
    private Libro buscarLibro(String isbn){
        Libro libro = new Libro(isbn);
        return libroDAO.buscarLibroPorIsbn(libro);
    }
}
