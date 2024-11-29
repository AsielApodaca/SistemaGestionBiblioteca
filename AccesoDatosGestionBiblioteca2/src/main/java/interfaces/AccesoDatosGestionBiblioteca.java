/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package interfaces;

import daos.UsuarioDAO;
import entidades.Usuario;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class AccesoDatosGestionBiblioteca {

    public static void main(String[] args) {
        IUsuarioDAO dao = new UsuarioDAO();
        
        List<Usuario> usuarios = dao.obtenerUsuarios();
        
        if(!usuarios.isEmpty()){
            for (Usuario usuario : usuarios) {
                System.out.println("usuario: "+usuario);
            }
        }
    }
}
