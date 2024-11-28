/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import daos.BibliotecarioDAO;
import entidades.Bibliotecario;
import dtos.BibliotecarioDTO;
import interfaces.IBibliotecarioDAO;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class AutenticacionServicio {
    private IBibliotecarioDAO bibliotecarioDAO;
    
    public AutenticacionServicio(){
        bibliotecarioDAO = new BibliotecarioDAO();
    }
    
    public boolean agregarBibliotecarios(){
        return bibliotecarioDAO.agregarBibliotecarios();
    }
    
    public boolean iniciarSesion(BibliotecarioDTO bibliotecario){
        Bibliotecario bibliotecarioBuscado = buscarBibliotecario(bibliotecario);
        if(bibliotecarioBuscado != null){
            return validarCredenciales(bibliotecarioBuscado, bibliotecario.getContra());
        }
        return false;
    }
    
    private Bibliotecario buscarBibliotecario(BibliotecarioDTO bibliotecario){
        Bibliotecario bibliotecarioBuscado = new Bibliotecario();
        bibliotecarioBuscado.setUsuario(bibliotecario.getUsuario());
        return bibliotecarioDAO.obtenerBibliotecario(bibliotecarioBuscado);
    }
    
    private boolean validarCredenciales(Bibliotecario bibliotecario, String contra){
        return bibliotecario.getContra().equals(contra);
    }
}
