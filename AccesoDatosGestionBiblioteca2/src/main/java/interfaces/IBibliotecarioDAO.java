/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Bibliotecario;
import java.util.List;

/**
 *
 * @author luisa M
 */
public interface IBibliotecarioDAO {
    public Bibliotecario obtenerBibliotecario(Bibliotecario bibliotecario);
    public boolean agregarBibliotecarios();
}
