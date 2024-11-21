/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luisa M
 */
public class ConexionBD {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("conexionPU");
    private static EntityManager em;

    private ConexionBD() {
    }

    public static synchronized EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void cerrarConexion() {
        if (em.isOpen()) {
            em.close();
            System.out.println("entity manager cerrado");
        }
        if (emf.isOpen()) {
            emf.close();
            System.out.println("entity manager factory cerrado");
        }
    }
}
