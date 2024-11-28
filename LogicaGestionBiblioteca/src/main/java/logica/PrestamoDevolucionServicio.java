/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import daos.LibroDAO;
import daos.PrestamoDAO;
import dtos.DevolucionDTO;
import entidades.Libro;
import entidades.Prestamo;
import dtos.PrestamoDTO;
import interfaces.ILibroDAO;
import interfaces.IPrestamoDAO;

/**
 *
 * @author luisa M
 */
public class PrestamoDevolucionServicio {
    private IPrestamoDAO prestamoDAO;
    private ILibroDAO libroDAO;
    
    public PrestamoDevolucionServicio(){
        prestamoDAO = new PrestamoDAO();
        libroDAO = new LibroDAO();
    }
    
    public boolean registrarPrestamo(PrestamoDTO prestamo){
        Prestamo prestamoNuevo = new Prestamo();
        Libro libroPrestamo = new Libro(prestamo.getIsbnLibro());
        libroPrestamo = libroDAO.buscarLibroPorIsbn(libroPrestamo);
        if(libroPrestamo != null){
            prestamoNuevo.setLibro(libroPrestamo);
            if (prestamoDAO.buscarPrestamoPorLibro(prestamoNuevo) != null) {
                libroDAO.actualizarDisponibilidadLibro(libroPrestamo);
                return prestamoDAO.registrarPrestamo(prestamoNuevo);
            }
        }
        return false;
    }
    
    public boolean devolverLibro(DevolucionDTO devolucion){
        Libro libro = new Libro(devolucion.getIsbnLibro());
        
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        
        prestamo = prestamoDAO.buscarPrestamoPorLibro(prestamo);
        
        if(prestamo != null){
            return prestamoDAO.agregarFechaDevolucion(prestamo);
        }
        return false;
    }
}
