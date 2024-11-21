/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Usuario;

/**
 *
 * @author luisa M
 */
public class GestionUsuarioServicio {
    private IUsuarioDAO usuarioDAO;
    
    public boolean registrarUsuario(Usuario usuario){
        
    }
    
    public boolean modificarUsuario(Usuario usuario) {
        Usuario userActual = buscarUsuario(usuario.getId());
        if (userActual != null) {
            if (validarModificaciones(userActual, usuario)) {
                return usuarioDAO.actualizarUsuario(usuario);
            }
        }
        return false;
    }

    private boolean validarModificaciones(Usuario userActual, Usuario userModificado) {
        return !userActual.getEmail().equals(userModificado.getEmail())
                || !userActual.getNombre().equals(userModificado.getNombre());
    }

    private Usuario buscarUsuario(String idUsuario) {
        return usuarioDAO.buscarLibroPorId(idUsuario);
    }
}
