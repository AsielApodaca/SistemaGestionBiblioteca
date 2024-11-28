/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Usuario;
import java.util.List;

/**
 *
 * @author luisa M
 */
public interface IUsuarioDAO {
    public Usuario obtenerUsuario(String id);
    public Usuario obtenerUsuarioPorEmail(Usuario usuario);
    public Usuario agregarUsuario(Usuario usuario);
    public boolean eliminarUsuario(Usuario usuario);
    public boolean actualizarUsuario(Usuario usuario);
    public boolean actualizarEmailUsuario(Usuario usuario);
    public boolean actualizarNombreUsuario(Usuario usuario);
    public List<Usuario> obtenerUsuarios();
}
