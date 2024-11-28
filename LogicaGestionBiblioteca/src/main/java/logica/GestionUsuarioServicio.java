/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import daos.UsuarioDAO;
import entidades.Usuario;
import dtos.UsuarioDTO;
import interfaces.IUsuarioDAO;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class GestionUsuarioServicio {
    private IUsuarioDAO usuarioDAO;
    private final int NINGUNO = 0;
    private final int SOLO_EMAIL = 1;
    private final int SOLO_NOMBRE = 2;
    private final int EMAIL_Y_NOMBRE = 3;
    
    public GestionUsuarioServicio(){
        usuarioDAO = new UsuarioDAO();
    }
    
    public boolean registrarUsuario(UsuarioDTO usuario){
        Usuario usuarioNuevo = buscarUsuario(usuario);
        if(usuarioNuevo == null){
            usuarioNuevo = new Usuario();
            usuarioNuevo.setEmail(usuario.getEmail());
            usuarioNuevo.setNombre(usuario.getNombre());
            return usuarioDAO.agregarUsuario(usuarioNuevo) != null;
        }
        return false;
    }
    
    public boolean eliminarUsuario(UsuarioDTO usuario){
        Usuario usuarioAEliminar = buscarUsuario(usuario);
        if(usuarioAEliminar != null){
            return usuarioDAO.eliminarUsuario(usuarioAEliminar);
        }
        return false;
    }
    
    public boolean modificarUsuario(UsuarioDTO usuario) {
        Usuario userActual = buscarUsuario(usuario);
        if (userActual != null) {
            Usuario usuarioModificado = new Usuario();
            usuarioModificado.setId(userActual.getId());
            usuarioModificado.setEmail(usuario.getEmail());
            usuarioModificado.setNombre(usuario.getNombre());
            switch (validarModificaciones(userActual, usuarioModificado)) {
                case EMAIL_Y_NOMBRE -> {
                    return usuarioDAO.actualizarUsuario(usuarioModificado);
                }
                case  SOLO_EMAIL -> {
                    return usuarioDAO.actualizarEmailUsuario(usuarioModificado);
                }
                case SOLO_NOMBRE -> {
                    return usuarioDAO.actualizarNombreUsuario(usuarioModificado);
                }
                case NINGUNO -> {
                    return false;
                }
            }
        }
        return false;
    }

    private int validarModificaciones(Usuario userActual, Usuario userModificado) {
        int tipoModificacion = 0;
        if(!userActual.getEmail().equals(userModificado.getEmail())){
            if(!userActual.getNombre().equals(userModificado.getNombre()))
                tipoModificacion = EMAIL_Y_NOMBRE;
            else 
                tipoModificacion = SOLO_EMAIL;
        }else if(!userActual.getNombre().equals(userModificado.getNombre()))
            tipoModificacion = SOLO_NOMBRE;
        return tipoModificacion;
    }

    public Usuario buscarUsuario(UsuarioDTO usuario) {
        Usuario usuarioBuscado = new Usuario();
        usuarioBuscado.setEmail(usuario.getEmail());
        return usuarioDAO.obtenerUsuarioPorEmail(usuarioBuscado);
    }
    
    public List<Usuario> buscarUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }
    
}
