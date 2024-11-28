/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Bibliotecario;

/**
 *
 * @author luisa M
 */
public interface IBibliotecarioDAO {
    public Bibliotecario obtenerBibliotecario(String id);
    
    public boolean agregarBibliotecario(Bibliotecario bibliotecario);
}
