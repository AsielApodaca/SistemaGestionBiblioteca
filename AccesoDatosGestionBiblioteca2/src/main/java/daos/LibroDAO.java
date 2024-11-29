/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Libro;
import interfaces.ILibroDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author luisa M
 */
public class LibroDAO implements ILibroDAO{
    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG = Logger.getLogger(LibroDAO.class.getName());

    public LibroDAO() {
        em = ConexionBD.getEntityManager();
        cb = em.getCriteriaBuilder();
    }

    @Override
    public List<Libro> agregarLibros(){
        StoredProcedureQuery spc = em.createStoredProcedureQuery("sp_insertar_libros", Libro.class);
        List<Libro> libros = null;
        try {
            em.getTransaction().begin();
            if (spc.execute()) {
                libros = spc.getResultList();
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return libros;
    }
    
    @Override
    public boolean registrarLibro(Libro libro) {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean actualizarDisponibilidadLibro(Libro libro) {
        Libro libroBuscado = em.find(Libro.class, libro.getIsbn());
        if (libroBuscado != null) {
            try {
                em.getTransaction().begin();
                libroBuscado.setDisponible(!libro.isDisponible());
                //usuarioBuscado = em.merge(usuarioBuscado);
                System.out.println("usuario act: " + libroBuscado);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                if(em.getTransaction().isActive())
                    em.getTransaction().rollback();
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return false;
    }

    @Override
    public boolean actualizarLibro(Libro libro) {
        Libro libroBuscado = em.find(Libro.class, libro.getIsbn());
        try {
            em.getTransaction().begin();
            libroBuscado.setAutor(libro.getAutor());
            libroBuscado.setTitulo(libro.getTitulo());
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean eliminarLibro(Libro libro) {
        try {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public Libro buscarLibroPorIsbn(Libro libro){
        CriteriaQuery<Libro> criteria = cb.createQuery(Libro.class);
        Root<Libro> root = criteria.from(Libro.class);

        Predicate predicate = cb.equal(root.get("isbn"), libro.getIsbn());
        criteria.select(root).where(predicate);

        TypedQuery<Libro> query = em.createQuery(criteria);
        Libro libroConsultado = null;
        try {
            libroConsultado = query.getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return libroConsultado;
    }

    @Override
    public List<Libro> buscarLibroPorAutor(Libro libro) {
        CriteriaQuery<Libro> criteria = cb.createQuery(Libro.class);
        Root<Libro> root = criteria.from(Libro.class);

        Predicate predicate = cb.equal(root.get("autor"), libro.getAutor());
        criteria.select(root).where(predicate);

        TypedQuery<Libro> query = em.createQuery(criteria);
        List<Libro> librosConsultados = null;
        try {
            librosConsultados = query.getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return librosConsultados;
    }

    @Override
    public List<Libro> buscarLibrosPorTitulo(Libro libro) {
        CriteriaQuery<Libro> criteria = cb.createQuery(Libro.class);
        Root<Libro> root = criteria.from(Libro.class);

        Predicate predicate = cb.equal(root.get("titulo"), libro.getTitulo());
        criteria.select(root).where(predicate);

        TypedQuery<Libro> query = em.createQuery(criteria);
        List<Libro> libroConsultado = null;
        try {
            libroConsultado = query.getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return libroConsultado;
    }
    
}
