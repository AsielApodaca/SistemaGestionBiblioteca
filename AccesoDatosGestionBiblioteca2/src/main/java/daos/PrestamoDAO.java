/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Libro;
import entidades.Prestamo;
import entidades.Usuario;
import interfaces.IPrestamoDAO;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author luisa M
 */
public class PrestamoDAO implements IPrestamoDAO{
    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG = Logger.getLogger(PrestamoDAO.class.getName());

    public PrestamoDAO() {
        em = ConexionBD.getEntityManager();
        cb=em.getCriteriaBuilder();
    }

    @Override
    public boolean registrarPrestamo(Prestamo prestamo) {
        try {
            em.getTransaction().begin();
            em.persist(prestamo);
            em.getTransaction().commit();
            return true;
        } catch (EntityExistsException | IllegalStateException | RollbackException e) {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage() , e);
            return false;
        }
    }

    @Override
    public Prestamo buscarPrestamoPorLibro(Prestamo prestamo) {
        CriteriaQuery<Prestamo> criteria=cb.createQuery(Prestamo.class);
        Root<Prestamo> root=criteria.from(Prestamo.class);

        //Join<Prestamo, Libro> libroJoin = root.join("libro");

        //Predicate predicate = cb.equal(libroJoin.get("isbn"), prestamo.getLibro().getIsbn());

        Predicate predicate = cb.equal(root.<Libro>get("libro").<String>get("isbn"), prestamo.getLibro().getIsbn());
        criteria.select(root).where(predicate);
        
        TypedQuery<Prestamo> query=em.createQuery(criteria);
        try {
            Prestamo prestamoConsultado=query.getSingleResult();
            return prestamoConsultado;
        } catch (NoResultException | NonUniqueResultException e) {
            LOG.log(Level.SEVERE, e.getMessage() , e);
        }
        return null;
    }

    @Override
    public List<Prestamo> buscarPrestamosPorUsuario(Prestamo prestamo) {
        CriteriaQuery<Prestamo> criteria=cb.createQuery(Prestamo.class);
        Root<Prestamo> root=criteria.from(Prestamo.class);
        
        Predicate predicate = cb.equal(root.<Usuario>get("usuario").<Long>get("id"), prestamo.getUsuario().getId());
        criteria.select(root).where(predicate);
        
        TypedQuery<Prestamo> query=em.createQuery(criteria);
        try {
            List<Prestamo> prestamosConsultados=query.getResultList();
            return prestamosConsultados;
        } catch (IllegalStateException e) {
            LOG.log(Level.SEVERE, e.getMessage() , e);
        }
        return null;
    }
    
    @Override
    public boolean agregarFechaDevolucion(Prestamo prestamo){
        Prestamo prestamoDevuelto = em.find(Prestamo.class, prestamo.getId());
        try {
            em.getTransaction().begin();
            Calendar fechaDevolucion = Calendar.getInstance();
            prestamoDevuelto.setFechaDevolucion(fechaDevolucion);
            em.getTransaction().commit();
            return true;
        } catch ( IllegalStateException | RollbackException e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage() , e);
            return false;
        }
    }
}
