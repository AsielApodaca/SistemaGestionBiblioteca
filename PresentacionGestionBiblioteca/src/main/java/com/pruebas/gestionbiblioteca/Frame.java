/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pruebas.gestionbiblioteca;

import fachada.FachadaGestionBiblioteca;
import fachada.GestionBiblioteca;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author luisa M
 */
public class Frame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private static final FachadaGestionBiblioteca gestorBiblioteca = GestionBiblioteca.getInstance();
    
    public Frame() {
        setTitle("Sistema Gestor de Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Se crean los paneles (ventanas) y se añaden los paneles al panel con CardLayout
        cardPanel.add(new PanelLogin(gestorBiblioteca), "PanelLogin");
        cardPanel.add(new PanelDashboard(gestorBiblioteca), "PanelDashboard");
        cardPanel.add(new PanelRegistro(gestorBiblioteca), "PanelRegistro");
        
        // Añadir el panel con CardLayout
        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }
    
    public void mostrarVentana(String nombrePanel) {
        cardLayout.show(cardPanel, nombrePanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame frame = new Frame();
                frame.setVisible(true);
            }
        });
    }
}
