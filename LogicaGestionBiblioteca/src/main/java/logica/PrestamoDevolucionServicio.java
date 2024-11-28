/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import daos.LibroDAO;
import daos.PrestamoDAO;
import daos.UsuarioDAO;
import dtos.DevolucionDTO;
import entidades.Libro;
import entidades.Prestamo;
import dtos.PrestamoDTO;
import entidades.Usuario;
import interfaces.ILibroDAO;
import interfaces.IPrestamoDAO;
import interfaces.IUsuarioDAO;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class PrestamoDevolucionServicio {
    private IPrestamoDAO prestamoDAO;
    private ILibroDAO libroDAO;
    private IUsuarioDAO usuarioDAO;
    
    public PrestamoDevolucionServicio(){
        prestamoDAO = new PrestamoDAO();
        libroDAO = new LibroDAO();
        usuarioDAO = new UsuarioDAO();
    }
    
    private Usuario validarRegistroPrestamo(Libro libroPrestado, Usuario usuario){
        Libro libro = libroDAO.buscarLibroPorIsbn(libroPrestado);
        Usuario usuarioBuscado = usuarioDAO.obtenerUsuarioPorEmail(usuario);
        
        if((libro != null && libro.isDisponible()) && usuarioBuscado != null){
            return usuarioBuscado;
        }
        return null;
    }
    
    public boolean registrarPrestamo(PrestamoDTO prestamo){
        Prestamo prestamoNuevo = new Prestamo();
        Libro libroPrestamo = new Libro(prestamo.getIsbnLibro());
        
        Usuario usuario = new Usuario();
        usuario.setEmail(prestamo.getEmailUsuario());
        
        usuario = validarRegistroPrestamo(libroPrestamo, usuario);
        if(usuario != null){
            libroPrestamo.setDisponible(true);
            prestamoNuevo.setLibro(libroPrestamo);
            prestamoNuevo.setUsuario(usuario);
            prestamoNuevo.setFechaPrestamo(Calendar.getInstance());
            prestamoNuevo.setFechaLimite(prestamo.getFechaLimite());
            libroDAO.actualizarDisponibilidadLibro(libroPrestamo);
            return prestamoDAO.registrarPrestamo(prestamoNuevo);
        }
        return false;
    }
    
    public boolean devolverLibro(DevolucionDTO devolucion){
        Libro libro = new Libro(devolucion.getIsbnLibro());
        
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        
        prestamo = prestamoDAO.buscarPrestamoPorLibro(prestamo);
        
        if(prestamo != null && prestamo.getFechaDevolucion() == null){
            Libro libroDevuelto = libroDAO.buscarLibroPorIsbn(libro);
            libroDAO.actualizarDisponibilidadLibro(libroDevuelto);
            return prestamoDAO.agregarFechaDevolucion(prestamo);
        }
        return false;
    }
    
    public Prestamo buscarPrestamoPorLibro(PrestamoDTO prestamoDTO){
        Prestamo prestamo = new Prestamo();
        Libro libro = new Libro(prestamoDTO.getIsbnLibro());
        prestamo.setLibro(libro);
        return prestamoDAO.buscarPrestamoPorLibro(prestamo);
    }
    
    public List<Prestamo> buscarPrestamosPorUsuario(PrestamoDTO prestamoDTO){
        Prestamo prestamo = new Prestamo();
        Usuario usuario = new Usuario();
        usuario.setEmail(prestamoDTO.getEmailUsuario());
        prestamo.setUsuario(usuario);
        
        return prestamoDAO.buscarPrestamosPorUsuario(prestamo);
    }
}
