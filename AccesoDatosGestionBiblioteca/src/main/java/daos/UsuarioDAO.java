package daos;

import entidades.Usuario;
import interfaces.IUsuarioDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luisa M
 */
public class UsuarioDAO implements IUsuarioDAO {

    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO() {
        em = ConexionBD.getEntityManager();
        cb = em.getCriteriaBuilder();
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> root = criteria.from(Usuario.class);
        criteria.select(root).where(cb.equal(root.get("id"), id));
        TypedQuery<Usuario> query = em.createQuery(criteria);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(Usuario usuario) {
        try {
            CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.select(root).where(cb.equal(root.get("email"), usuario.getEmail()));
            return em.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Usuario agregarUsuario(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean eliminarUsuario(Usuario usuario) {
        try {
            em.getTransaction().begin();
            Usuario usuarioAEliminar = em.find(Usuario.class, usuario.getId());
            if (usuarioAEliminar != null) {
                em.remove(usuarioAEliminar);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean actualizarEmailUsuario(Usuario usuario) {
        try {
            Usuario usuarioBuscado = em.find(Usuario.class, usuario.getId());
            if (usuarioBuscado != null) {
                em.getTransaction().begin();
                usuarioBuscado.setEmail(usuario.getEmail());
                em.merge(usuarioBuscado);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean actualizarNombreUsuario(Usuario usuario) {
        try {
            Usuario usuarioBuscado = em.find(Usuario.class, usuario.getId());
            if (usuarioBuscado != null) {
                em.getTransaction().begin();
                usuarioBuscado.setNombre(usuario.getNombre());
                em.merge(usuarioBuscado);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return false;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        try {
            CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
            Root<Usuario> root = criteria.from(Usuario.class);
            criteria.select(root);
            return em.createQuery(criteria).getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return new ArrayList<>();
    }
}
