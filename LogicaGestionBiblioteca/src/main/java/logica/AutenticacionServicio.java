/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import entidades.Bibliotecario;

/**
 *
 * @author luisa M
 */
public class AutenticacionServicio {
    private IBibliotecarioDAO bibliotecarioDAO;
    
    public boolean registrarBibliotecario(String usuario, String contra){
        if(buscarBibliotecario(usuario) != null){
            return bibliotecarioDAO.registrarBibliotecario(new Bibliotecario(usuario, contra));
        }
        return false;
    }
    
    public boolean iniciarSesion(String usuario, String contra){
        Bibliotecario bibliotecarioBuscado = buscarBibliotecario(usuario);
        if(bibliotecarioBuscado != null){
            return validarCredenciales(bibliotecarioBuscado, contra);
        }
        return false;
    }
    
    private Bibliotecario buscarBibliotecario(String username){
        return bibliotecarioDAO.buscarBibliotecario(username);
    }
    
    private boolean validarCredenciales(Bibliotecario bibliotecario, String contra){
        return bibliotecario.getContra().equals(contra);
    }
}
