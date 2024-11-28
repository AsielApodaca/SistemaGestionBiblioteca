/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import daos.BibliotecarioDAO;
import dtos.BibliotecarioDTO;
import entidades.Bibliotecario;
import interfaces.IBibliotecarioDAO;

/**
 *
 * @author luisa M
 */
public class AutenticacionServicio {
    private IBibliotecarioDAO bibliotecarioDAO;
    
    public AutenticacionServicio(){
        bibliotecarioDAO = new BibliotecarioDAO();
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
        return bibliotecarioDAO.obtenerBibliotecario(bibliotecarioBuscado.getId().toString());
    }
    
    private boolean validarCredenciales(Bibliotecario bibliotecario, String contra){
        return bibliotecario.getContra().equals(contra);
    }
    
    /**
     * Método para agregar bibliotecarios hardcodeados a la base de datos.
     * @return true si los bibliotecarios se agregan correctamente, false en caso contrario.
     */
    public boolean agregarBibliotecarios() {
        // Crear bibliotecarios hardcodeados
        Bibliotecario bibliotecario1 = new Bibliotecario();
        bibliotecario1.setUsuario("admin");
        bibliotecario1.setContra("admin123");
        
        Bibliotecario bibliotecario2 = new Bibliotecario();
        bibliotecario2.setUsuario("librarian");
        bibliotecario2.setContra("librarian123");
        
        Bibliotecario bibliotecario3 = new Bibliotecario();
        bibliotecario3.setUsuario("user");
        bibliotecario3.setContra("user123");

        // Agregar bibliotecarios a la base de datos
        boolean resultado1 = bibliotecarioDAO.agregarBibliotecario(bibliotecario1);
        boolean resultado2 = bibliotecarioDAO.agregarBibliotecario(bibliotecario2);
        boolean resultado3 = bibliotecarioDAO.agregarBibliotecario(bibliotecario3);

        return resultado1 && resultado2 && resultado3;
    }
}
