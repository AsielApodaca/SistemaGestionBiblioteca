/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package logica;

import dtos.DevolucionDTO;
import dtos.PrestamoDTO;
import dtos.UsuarioDTO;
import entidades.Prestamo;
import entidades.Usuario;
import fachada.FachadaGestionBiblioteca;
import fachada.GestionBiblioteca;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author luisa M
 */
public class LogicaGestionBiblioteca {

    public static void main(String[] args) {
        GestionUsuarioServicio servicioUser = new GestionUsuarioServicio();
        FachadaGestionBiblioteca fachada = GestionBiblioteca.getInstance();
        PrestamoDevolucionServicio servicio = new PrestamoDevolucionServicio();
//        DevolucionDTO devolucion = new DevolucionDTO();
//        devolucion.setIsbnLibro("978-9681603011");
//        if(servicio.devolverLibro(devolucion))
//            System.out.println("success");
//        else
//            System.out.println("not success");
//        
//        PrestamoDTO prestamo = new PrestamoDTO();
//        prestamo.setEmailUsuario("laura.sanchez@email.com");
//        prestamo.setIsbnLibro("978-0307474728");
//        
//        Calendar fechaLimite = Calendar.getInstance();
//        fechaLimite.add(Calendar.MONTH, 1);
//        prestamo.setFechaLimite(fechaLimite);
//        if(servicio.registrarPrestamo(prestamo))
//            System.out.println("succes");
//        else
//            System.out.println("not success");
                

        List<Usuario> users = servicioUser.buscarUsuarios();
        List<UsuarioDTO> dtos = fachada.buscarUsuarios();
        if(dtos != null){
            for (UsuarioDTO dto : dtos) {
                System.out.println("dto: "+dto);
            }
        }
        
        if(users != null){
            for (Usuario user : users) {
                System.out.println("user: "+user);
            }
        }
//        List<Prestamo> prestamos = servicio.buscarPrestamos();
//        if(prestamos != null){
//            for (Prestamo prestamo : prestamos) {
//                
//            }
//        }
    }
}
