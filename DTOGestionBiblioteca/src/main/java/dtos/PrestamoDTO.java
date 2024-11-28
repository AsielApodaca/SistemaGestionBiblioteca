/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.Calendar;

/**
 *
 * @author luisa M
 */
public class PrestamoDTO {
    private String isbnLibro;
    private String emailUsuario;
    private Calendar fechaRegistro;
    private Calendar fechaLimite;
    private Calendar fechaDevolucion;

    public PrestamoDTO() {
    }

    public Calendar getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Calendar fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Calendar getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Calendar fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
    
}
