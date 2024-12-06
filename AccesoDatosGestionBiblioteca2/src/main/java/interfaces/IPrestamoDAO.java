/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Prestamo;
import java.util.List;

/**
 *
 * @author luisa M
 */
public interface IPrestamoDAO {
    public boolean registrarPrestamo(Prestamo prestamo);
    public Prestamo buscarPrestamoPorLibro(Prestamo prestamo);
    public List<Prestamo> buscarPrestamosPorUsuario(Prestamo prestamo);
    public List<Prestamo> buscarPrestamosPorFechaDevolucion(Prestamo prestamo);
    public List<Prestamo> buscarPrestamosPorFechaRegistro(Prestamo prestamo);
    public List<Prestamo> buscarPrestamosPorFechaLimite(Prestamo prestamo);
    public List<Prestamo> buscarPrestamos();
    public boolean agregarFechaDevolucion(Prestamo prestamo);
}
