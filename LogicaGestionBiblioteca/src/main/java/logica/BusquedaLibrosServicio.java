/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import daos.LibroDAO;
import dtos.LibroDTO;
import entidades.Libro;
import interfaces.ILibroDAO;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class BusquedaLibrosServicio {
    private ILibroDAO libroDAO;
    
    public BusquedaLibrosServicio(){
        libroDAO = new LibroDAO();
    }
    
    public Libro buscarLibroPorIsbn(LibroDTO libro){
        Libro libroBuscado = new Libro(libro.getIsbn());
        return libroDAO.buscarLibroPorIsbn(libroBuscado);
    }
    public List<Libro> buscarLibrosPorAutor(LibroDTO libro){
        Libro libroBuscado = new Libro();
        libroBuscado.setAutor(libro.getAutor());
        List<Libro> libros = libroDAO.buscarLibroPorAutor(libroBuscado);
        if(libros.isEmpty())
            return null;
        return libros;
    }
    
    public List<Libro> buscarLibrosPorTitulo(LibroDTO libro){
        Libro libroBuscado = new Libro();
        libroBuscado.setTitulo(libro.getTitulo());
        List<Libro> libros = libroDAO.buscarLibrosPorTitulo(libroBuscado);
        if(libros.isEmpty())
            return null;
        return libros;
    }
}
