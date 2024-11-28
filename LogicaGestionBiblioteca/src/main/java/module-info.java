/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module LogicaGestionBiblioteca {
    requires AccesoDatosGestionBiblioteca;
    requires org.eclipse.persistence.core;
    requires org.eclipse.persistence.asm;
    requires eclipselink.antlr;
    requires org.eclipse.persistence.jpa;
    requires org.eclipse.persistence.jpa.jpql;
    requires org.eclipse.persistence.moxy;
    requires java.persistence;
    requires mysql.connector.j;
    requires com.google.protobuf;
    requires DTOGestionBiblioteca;
    
    uses interfaces.IBibliotecarioDAO;
    uses interfaces.ILibroDAO;
    uses interfaces.IPrestamoDAO;
    uses interfaces.IUsuarioDAO;
    
    exports fachada;
}
