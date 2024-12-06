/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fachada;

import dtos.BibliotecarioDTO;
import dtos.DevolucionDTO;
import dtos.LibroDTO;
import dtos.PrestamoDTO;
import dtos.UsuarioDTO;
import java.util.List;

/**
 *
 * @author luisa M
 */
public interface FachadaGestionBiblioteca {
    public boolean agregarBibliotecarios();
    public boolean iniciarSesion(BibliotecarioDTO bibliotecario);
    public boolean gestionarUsuarios(TipoAccion accion, UsuarioDTO usuario);
    public boolean gestionarLibros(TipoAccion accion, LibroDTO libro);
    public boolean registrarPrestamo(PrestamoDTO prestamo);
    public PrestamoDTO buscarPrestamo(PrestamoDTO prestamo);
    public List<PrestamoDTO> buscarPrestamos(FiltroBusquedaPrestamo filtro, PrestamoDTO prestamo);
    public List<PrestamoDTO> buscarPrestamos();
    public boolean registrarDevolucion(DevolucionDTO devolucion);
    public List<LibroDTO> busquedaLibro(TipoBusqueda criterio, LibroDTO libro);
    public List<LibroDTO> buscarLibros();
    public List<UsuarioDTO> buscarUsuarios();
}
