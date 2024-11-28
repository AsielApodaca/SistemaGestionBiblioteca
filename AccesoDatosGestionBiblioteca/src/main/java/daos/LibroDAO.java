package daos;

import entidades.Libro;
import interfaces.ILibroDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements ILibroDAO {
    private EntityManager em;

    public LibroDAO() {
        this.em = ConexionBD.getEntityManager();
    }

    @Override
    public Libro buscarLibroPorIsbn(Libro libro) {
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn", Libro.class);
            query.setParameter("isbn", libro.getIsbn());
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
        }
        return null;
    }

    @Override
    public List<Libro> buscarLibroPorAutor(Libro libro) {
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.autor = :autor", Libro.class);
            query.setParameter("autor", libro.getAutor());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
        }
        return new ArrayList<>();
    }

    @Override
    public List<Libro> buscarLibrosPorTitulo(Libro libro) {
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class);
            query.setParameter("titulo", "%" + libro.getTitulo() + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
        }
        return new ArrayList<>();
    }

    @Override
    public List<Libro> agregarLibros() {
        try {
            TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l", Libro.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores.
        }
        return new ArrayList<>();
    }

    @Override
    public boolean registrarLibro(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(libro);
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
    public boolean eliminarLibro(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Libro libroEncontrado = em.find(Libro.class, libro.getId());
            if (libroEncontrado != null) {
                em.remove(libroEncontrado);
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

    @Override
    public boolean actualizarLibro(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Libro libroActual = em.find(Libro.class, libro.getId());
            if (libroActual != null) {
                libroActual.setTitulo(libro.getTitulo());
                libroActual.setAutor(libro.getAutor());
                em.merge(libroActual);
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

    @Override
    public boolean actualizarDisponibilidadLibro(Libro libro) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Libro libroEncontrado = em.find(Libro.class, libro.getId());
            if (libroEncontrado != null) {
                libroEncontrado.setDisponible(libro.isDisponible());
                em.merge(libroEncontrado);
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
