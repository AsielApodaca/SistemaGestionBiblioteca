/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Libro;

/**
 *
 * @author luisa M
 */
public class GestionLibroServicio {
    private ILibroDAO libroDAO;
    
    public boolean agregarLibro(Libro libro){
        if(buscarLibro(libro.getId()) == null){
            return libroDAO.registrarLibro(libro);
        }
        return false;
    }
    
    public boolean modificarLibro(Libro libro){
        Libro libroActual = buscarLibro(libro.getId());
        if(libroActual != null){
            if(validarModificaciones(libroActual, libro))
                return libroDAO.actualizarLibro(libro);
        }
        return false;
    }
    
    private boolean validarModificaciones(Libro libroActual, Libro libroModificado){
        return !libroActual.getAutor().equals(libroModificado.getAutor()) ||
                !libroActual.getTitulo().equals(libroModificado.getTitulo());
    }
    
    private Libro buscarLibro(String idLibro){
        return libroDAO.buscarLibroPorId(idLibro);
    }
}
