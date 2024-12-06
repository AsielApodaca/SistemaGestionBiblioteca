/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import dtos.BibliotecarioDTO;
import dtos.LibroDTO;
import dtos.PrestamoDTO;
import dtos.UsuarioDTO;
import entidades.Bibliotecario;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class ConversionesServicio {

    public List<BibliotecarioDTO> convertirBibliotecarios(List<Bibliotecario> bibliotecarios) {
        List<BibliotecarioDTO> dtos = new ArrayList<>();
        for (Bibliotecario bibliotecario : bibliotecarios) {
            dtos.add(convertirBibliotecario(bibliotecario));
        }
        return dtos;
    }

    public BibliotecarioDTO convertirBibliotecario(Bibliotecario bibliotecario) {
        BibliotecarioDTO dto = new BibliotecarioDTO(bibliotecario.getUsuario(), bibliotecario.getContra());
        return dto;
    }

    public List<UsuarioDTO> convertirUsuarios(List<Usuario> usuarios) {
        List<UsuarioDTO> dtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            dtos.add(convertirUsuario(usuario));
        }
        return dtos;
    }

    public List<LibroDTO> convertirLibros(List<Libro> libros) {
        List<LibroDTO> dtos = new ArrayList<>();
        for (Libro libro : libros) {
            dtos.add(convertirLibro(libro));
        }
        return dtos;
    }

    public LibroDTO convertirLibro(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setIsbn(libro.getIsbn());
        dto.setAutor(libro.getAutor());
        dto.setTitulo(libro.getTitulo());
        dto.setDisponible(libro.isDisponible());
        return dto;
    }

    public UsuarioDTO convertirUsuario(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(String.valueOf(usuario.getId()));
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNombre(usuario.getNombre());
        return usuarioDTO;
    }

    public PrestamoDTO convertirPrestamo(Prestamo prestamo) {
        PrestamoDTO dto = new PrestamoDTO();
        dto.setEmailUsuario(prestamo.getUsuario().getEmail());
        dto.setFechaRegistro(prestamo.getFechaPrestamo());
        dto.setFechaLimite(prestamo.getFechaLimite());
        if(prestamo.getFechaDevolucion() != null)
            dto.setFechaDevolucion(prestamo.getFechaDevolucion());
        dto.setIsbnLibro(prestamo.getLibro().getIsbn());
        dto.setTituloLibro(prestamo.getLibro().getTitulo());
        dto.setAutorLibro(prestamo.getLibro().getAutor());
        return dto;
    }
    
    public List<PrestamoDTO> convertirPrestamos(List<Prestamo> prestamos) {
        List<PrestamoDTO> dtos = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            dtos.add(convertirPrestamo(prestamo));
        }
        return dtos;
    }
        
}
