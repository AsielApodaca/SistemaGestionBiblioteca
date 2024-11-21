/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Usuario;

/**
 *
 * @author luisa M
 */
public interface IUsuarioDAO {
    public Usuario obtenerUsuario(String id);
    public Usuario agregarUsuario(Usuario usuario);
}
