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
    private String tituloLibro;
    private String autorLibro;
    private String emailUsuario;
    private Calendar fechaRegistro;
    private Calendar fechaLimite;
    private Calendar fechaDevolucion;

    public PrestamoDTO() {
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
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

    public Calendar getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Calendar fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PrestamoDTO{");
        sb.append("isbnLibro=").append(isbnLibro);
        sb.append(", tituloLibro=").append(tituloLibro);
        sb.append(", autorLibro=").append(autorLibro);
        sb.append(", emailUsuario=").append(emailUsuario);
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", fechaLimite=").append(fechaLimite);
        sb.append(", fechaDevolucion=").append(fechaDevolucion);
        sb.append('}');
        return sb.toString();
    }
    
}
