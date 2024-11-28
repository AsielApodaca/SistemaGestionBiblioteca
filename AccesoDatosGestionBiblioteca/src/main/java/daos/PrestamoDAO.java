package daos;

import entidades.Prestamo;
import interfaces.IPrestamoDAO;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrestamoDAO implements IPrestamoDAO {
    private EntityManager em;

    public PrestamoDAO() {
        this.em = ConexionBD.getEntityManager();
    }

    @Override
    public boolean registrarPrestamo(Prestamo prestamo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(prestamo);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public Prestamo buscarPrestamoPorLibro(Prestamo prestamo) {
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.libro.id = :idLibro AND p.fechaDevolucion IS NULL", Prestamo.class);
            query.setParameter("idLibro", prestamo.getLibro().getId());
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
        }
        return null;
    }

    @Override
    public List<Prestamo> buscarPrestamosPorUsuario(Prestamo prestamo) {
        try {
            TypedQuery<Prestamo> query = em.createQuery(
                "SELECT p FROM Prestamo p WHERE p.usuario.id = :idUsuario AND p.fechaDevolucion IS NULL", Prestamo.class);
            query.setParameter("idUsuario", prestamo.getUsuario().getId());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
        }
        return null;
    }

    @Override
    public boolean agregarFechaDevolucion(Prestamo prestamo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Prestamo prestamoEncontrado = em.find(Prestamo.class, prestamo.getId());
            if (prestamoEncontrado != null) {
                em.merge(prestamoEncontrado);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return false;
    }
}
