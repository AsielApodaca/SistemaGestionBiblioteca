/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.pruebas.gestionbiblioteca;

import dtos.LibroDTO;
import dtos.PrestamoDTO;
import dtos.UsuarioDTO;
import fachada.FachadaGestionBiblioteca;
import fachada.TipoAccion;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author luisa M
 */
public class PanelRegistro extends javax.swing.JPanel {
    private static FachadaGestionBiblioteca gestor;
    private UsuarioDTO usuarioAgregado;
    private LibroDTO libroAgregado;
    private PrestamoDTO prestamoAgregado;
    private static List<UsuarioDTO> usuarios;
    private static List<LibroDTO> libros;
    protected static final int REGISTRAR_LIBRO = 1;
    protected static final int REGISTRAR_USUARIO = 2;
    protected static final int REGISTRAR_PRESTAMO = 3;
    protected static final int REGISTRAR_DEVOLUCION = 4;
    private static int tipoRegistro;
    /**
     * Creates new form PanelRegistro
     */
    public PanelRegistro(FachadaGestionBiblioteca gestor) {
        initComponents();
        this.gestor = gestor;
    }
    
    protected static void setUpLabels(int tipoRegistro){
        switch (tipoRegistro) {
            case REGISTRAR_LIBRO-> setUpLibro();
            case REGISTRAR_PRESTAMO->setUpPrestamo();
            case REGISTRAR_USUARIO->setUpUsuario();
            case REGISTRAR_DEVOLUCION->setUpDevolucion();
        }
    }
    
    private static void setUpDevolucion(){
        lbl1.setText("ISBN libro:");
        lbl2.setVisible(false);
        txt2.setVisible(false);
        lbl3.setVisible(false);
    }
    private static void setUpLibro(){
        lbl1.setText("ISBN:");
        lbl2.setText("Titulo:");
        lbl3.setText("Autor:");
        lbl3.setVisible(true);
        txt3.setVisible(true);
        tipoRegistro= REGISTRAR_LIBRO;
    }
    
    private static void setUpPrestamo(){
        lbl1.setText("Email usuario:");
        lbl2.setText("ISBN libro:");
        lbl3.setText("Fecha limite:");
        buscarUsuarios();
        buscarLibros();
        txt1.setText("Seleccione un usuario");
        txt2.setText("Seleccione un libro");
        lbl3.setVisible(true);
        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.MONTH, 1);
        txt3.setText(convertirFecha(fecha));
        txt3.setVisible(true);
        tipoRegistro= REGISTRAR_PRESTAMO;
    }
    private static void setUpUsuario(){
        lbl1.setText("Email usuario:");
        lbl2.setText("Nombre usuario:");
        lbl3.setVisible(false);
        txt3.setVisible(false);
        tipoRegistro= REGISTRAR_USUARIO;
    }
    
    public static String convertirFecha(Calendar fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fecha.getTime());
    }
    
    private static void buscarUsuarios(){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        usuarios = gestor.buscarUsuarios();
        if(usuarios != null && !usuarios.isEmpty()){
            System.out.println("usuarios: "+usuarios);
            for (UsuarioDTO usuario : usuarios) {
                modelo.addElement(usuario.getNombre());
            }
            cmbBoxUsers.setModel(modelo);
            registrar.setEnabled(true);
        }else{
            String[] msj = {"No hay usuarios"};
            cmbBoxUsers = new JComboBox<>(msj);
            registrar.setEnabled(false);
        }
        
    }
    private static void buscarLibros(){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        libros = gestor.buscarLibros();
        if(libros != null && !libros.isEmpty()){
            System.out.println("libros: "+libros);
            for (LibroDTO libro : libros) {
                if(libro.isDisponible())
                    modelo.addElement(libro.getTitulo());
            }
            
            cmbBoxLibros.setModel(modelo);
            
            registrar.setEnabled(true);
        }else{
            String[] msj = {"No hay libros"};
            cmbBoxUsers = new JComboBox<>(msj);
            registrar.setEnabled(false);
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        encabezado = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        registrar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        cmbBoxLibros = new javax.swing.JComboBox<>();
        cmbBoxUsers = new javax.swing.JComboBox<>();
        txt3 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 204, 204));
        setLayout(null);

        encabezado.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        encabezado.setText("Registro");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        add(panelEncabezado);
        panelEncabezado.setBounds(0, 0, 650, 100);

        lbl2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl2.setForeground(new java.awt.Color(0, 0, 0));
        lbl2.setText("ISBN libro:");
        add(lbl2);
        lbl2.setBounds(69, 255, 100, 22);

        lbl1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl1.setForeground(new java.awt.Color(0, 0, 0));
        lbl1.setText("Id usuario:");
        add(lbl1);
        lbl1.setBounds(69, 148, 100, 22);

        lbl3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl3.setForeground(new java.awt.Color(0, 0, 0));
        lbl3.setText("Fecha limite:");
        add(lbl3);
        lbl3.setBounds(50, 360, 116, 22);

        txt1.setEditable(false);
        txt1.setBackground(new java.awt.Color(255, 255, 255));
        txt1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt1.setForeground(new java.awt.Color(0, 0, 0));
        txt1.setEnabled(false);
        txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1ActionPerformed(evt);
            }
        });
        add(txt1);
        txt1.setBounds(210, 143, 354, 35);

        txt2.setEditable(false);
        txt2.setBackground(new java.awt.Color(255, 255, 255));
        txt2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt2.setForeground(new java.awt.Color(0, 0, 0));
        txt2.setEnabled(false);
        txt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2ActionPerformed(evt);
            }
        });
        add(txt2);
        txt2.setBounds(211, 250, 353, 35);

        registrar.setBackground(new java.awt.Color(0, 153, 0));
        registrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        registrar.setForeground(new java.awt.Color(255, 255, 255));
        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });
        add(registrar);
        registrar.setBounds(347, 470, 140, 45);

        cancelar.setBackground(new java.awt.Color(51, 51, 51));
        cancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancelar.setForeground(new java.awt.Color(255, 255, 255));
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        add(cancelar);
        cancelar.setBounds(119, 470, 141, 45);

        cmbBoxLibros.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbBoxLibros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbBoxLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxLibrosActionPerformed(evt);
            }
        });
        add(cmbBoxLibros);
        cmbBoxLibros.setBounds(211, 291, 187, 35);

        cmbBoxUsers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbBoxUsers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbBoxUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxUsersActionPerformed(evt);
            }
        });
        add(cmbBoxUsers);
        cmbBoxUsers.setBounds(210, 184, 187, 36);

        txt3.setEditable(false);
        txt3.setBackground(new java.awt.Color(255, 255, 255));
        txt3.setEnabled(false);
        add(txt3);
        txt3.setBounds(210, 350, 350, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1ActionPerformed

    private void txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt2ActionPerformed

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        // TODO add your handling code here:
        String msj;
        if(!registrar()){
            msj = "Hubo un erro al hacer el registro";
        }else{
            msj = "El registro fue un exito";
            switch (tipoRegistro) {
                case REGISTRAR_LIBRO ->
                    PanelDashboard.agregarLibro(libroAgregado);
                case REGISTRAR_USUARIO ->
                    PanelDashboard.agregarUsuario(usuarioAgregado);
                case REGISTRAR_PRESTAMO ->
                    PanelDashboard.agregarPrestamo(prestamoAgregado);
            }
        }
        JOptionPane.showMessageDialog(this, msj);
        ((Frame) SwingUtilities.getWindowAncestor(PanelRegistro.this)).mostrarVentana("PanelDashboard");
        cleanUp();
    }//GEN-LAST:event_registrarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        ((Frame) SwingUtilities.getWindowAncestor(PanelRegistro.this)).mostrarVentana("PanelDashboard");
    }//GEN-LAST:event_cancelarActionPerformed

    private void cmbBoxUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxUsersActionPerformed
        // TODO add your handling code here:
        String userSeleccionado = (String) cmbBoxUsers.getSelectedItem();
        String infoUser;
        if(userSeleccionado != null){
            infoUser = obtenerEmailUser(userSeleccionado);
            txt1.setText(infoUser);
        }
    }//GEN-LAST:event_cmbBoxUsersActionPerformed

    private void cmbBoxLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxLibrosActionPerformed
        // TODO add your handling code here:
        String libroSeleccionado = (String) cmbBoxLibros.getSelectedItem();
        String infoLibro;
        if(libroSeleccionado != null){
            infoLibro = obtenerIsbnLibro(libroSeleccionado);
            registrar.setEnabled(true);
            txt2.setText(infoLibro);
        }else{
            registrar.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBoxLibrosActionPerformed

    private String obtenerIsbnLibro(String libroSeleccionado){
        for (LibroDTO libro : libros) {
            if(libro.getTitulo().equals(libroSeleccionado)){
                return libro.getIsbn();
            }
        }
        return null;
    }
    
    private String obtenerEmailUser(String userSeleccionado){
        for (UsuarioDTO user : usuarios) {
            if(user.getNombre().equals(userSeleccionado)){
                return user.getEmail();
            }
        }
        return null;
    }
    
    private int existeUsuario(){
        String email = txt1.getText();
        
        if(!email.isBlank()){
            UsuarioDTO userBuscado = new UsuarioDTO();
            userBuscado.setEmail(email);
            userBuscado = gestor.buscarUsuario(userBuscado);
            if(userBuscado != null)
                return 1;
            else
                return 0;
        }
        return -1;
    }
    
    private boolean registrar(){
        boolean flag = false;
        switch(tipoRegistro){
            case REGISTRAR_LIBRO ->{
                libroAgregado = crearLibro();
                if(libroAgregado != null)
                    flag = gestor.gestionarLibros(TipoAccion.AGREGAR, libroAgregado);
            }
            case REGISTRAR_PRESTAMO -> {
                prestamoAgregado = crearPrestamo();
                if(prestamoAgregado != null){
                    flag = gestor.registrarPrestamo(prestamoAgregado);
                }
            }
            case REGISTRAR_USUARIO -> {
                usuarioAgregado = crearUsuario();
                if(usuarioAgregado != null)
                    flag = gestor.gestionarUsuarios(TipoAccion.AGREGAR, usuarioAgregado);
            }
        }
        return flag;
    }
    
    private void cleanUp(){
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
    }
    
    private PrestamoDTO crearPrestamo(){
        PrestamoDTO prestamo = new PrestamoDTO();
        String email = txt1.getText();
        String isbn = txt2.getText();
        
        if(!email.isBlank() && !isbn.isBlank()){
            prestamo.setEmailUsuario(email);
            prestamo.setIsbnLibro(isbn);
            Calendar fechaRegistro = Calendar.getInstance();
            fechaRegistro.add(Calendar.MONTH, 1);
            prestamo.setFechaLimite(fechaRegistro);
            return prestamo;
        }
        JOptionPane.showMessageDialog(this, "No puede dejar campos vacios");
        return null;
    }
    
    private LibroDTO crearLibro(){
        LibroDTO libro = new LibroDTO();
        String titulo = txt1.getText();
        String autor = txt2.getText();
        String isbn = txt3.getText();
        
        if(!titulo.isBlank() && !isbn.isBlank()){
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);
            if(autor.isBlank())
                libro.setAutor("Autor desconocido");
            else 
                libro.setAutor(autor);
            return libro;
        }
        JOptionPane.showMessageDialog(this, "No puede dejar campos vacios");
        return null;
    }
    
    private UsuarioDTO crearUsuario(){
        UsuarioDTO usuario = new UsuarioDTO();
        String email = txt1.getText();
        String nombre = txt2.getText();
        
        if(!email.isBlank() && !nombre.isBlank()){
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            
            return usuario;
        }
        JOptionPane.showMessageDialog(this, "No puede dejar campos vacios");
        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private static javax.swing.JComboBox<String> cmbBoxLibros;
    private static javax.swing.JComboBox<String> cmbBoxUsers;
    private javax.swing.JLabel encabezado;
    private static javax.swing.JLabel lbl1;
    private static javax.swing.JLabel lbl2;
    private static javax.swing.JLabel lbl3;
    private javax.swing.JPanel panelEncabezado;
    private static javax.swing.JButton registrar;
    private static javax.swing.JTextField txt1;
    private static javax.swing.JTextField txt2;
    private static javax.swing.JTextField txt3;
    // End of variables declaration//GEN-END:variables
}
