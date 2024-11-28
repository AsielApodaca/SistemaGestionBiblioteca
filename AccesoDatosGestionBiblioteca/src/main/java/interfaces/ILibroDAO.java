/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Libro;
import java.util.List;

/**
 *
 * @author luisa M
 */
public interface ILibroDAO {
    public Libro buscarLibroPorIsbn(Libro libro);
    public List<Libro> buscarLibroPorAutor(Libro libro);
    public List<Libro> buscarLibrosPorTitulo(Libro libro);
    public List<Libro> agregarLibros();
    public boolean registrarLibro(Libro libro);
    public boolean eliminarLibro(Libro libro);
    public boolean actualizarLibro(Libro libro);
    public boolean actualizarDisponibilidadLibro(Libro libro);
}
