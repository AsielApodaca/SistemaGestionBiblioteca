/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author luisa M
 */
public class UsuarioDAO implements IUsuarioDAO{
    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG= Logger.getLogger(UsuarioDAO.class.getName());
    
    public UsuarioDAO(){
        em=ConexionBD.getEntityManager();
        cb=em.getCriteriaBuilder();
    }
    
    @Override
    public List<Usuario> obtenerUsuarios(){
        StoredProcedureQuery spc = em.createStoredProcedureQuery("sp_insertar_usuarios", Usuario.class);
        List<Usuario> usuarios = new ArrayList<>();
        try {
            if (spc.execute()) {
                usuarios = spc.getResultList();
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        return usuarios;
    }
    
    @Override
    public Usuario obtenerUsuario(Usuario usuario){
        CriteriaQuery<Usuario> criteria=cb.createQuery(Usuario.class);
        Root<Usuario> root=criteria.from(Usuario.class);

        Predicate predicate = cb.equal(root.get("id"), usuario.getId());
        criteria.select(root).where(predicate);
        
        TypedQuery<Usuario> query=em.createQuery(criteria);
        try {
            Usuario personaConsultada=query.getSingleResult();
            return personaConsultada;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage() , e);
        }
        return null;
    }
    
    @Override
    public Usuario obtenerUsuarioPorEmail(Usuario usuario){
        CriteriaQuery<Usuario> criteria=cb.createQuery(Usuario.class);
        Root<Usuario> root=criteria.from(Usuario.class);

        Predicate predicate = cb.equal(root.get("email"), usuario.getEmail());
        criteria.select(root).where(predicate);
        
        TypedQuery<Usuario> query=em.createQuery(criteria);
        try {
            Usuario personaConsultada=query.getSingleResult();
            return personaConsultada;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage() , e);
        }
        return null;
    }
    
    @Override
    public Usuario agregarUsuario(Usuario usuario){
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage() , e); 
        }
        return null;
    }
    
    @Override
    public boolean actualizarUsuario(Usuario usuario){
        Usuario usuarioBuscado = em.find(Usuario.class, usuario.getId());
        if (usuarioBuscado != null) {
            try {
                em.getTransaction().begin();
                usuarioBuscado.setEmail(usuario.getEmail());
                usuarioBuscado.setNombre(usuario.getNombre());
                //usuarioBuscado = em.merge(usuarioBuscado);
                System.out.println("usuario act: " + usuarioBuscado);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                em.getTransaction().rollback();
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return false;
    }
    
    @Override
    public boolean actualizarEmailUsuario(Usuario usuario){
        Usuario usuarioBuscado = em.find(Usuario.class, usuario.getId());
        if(usuarioBuscado != null){
            try {
                em.getTransaction().begin();
                usuarioBuscado.setEmail(usuario.getEmail());
                //usuarioBuscado = em.merge(usuarioBuscado);
                System.out.println("usuario act: "+usuarioBuscado);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                em.getTransaction().rollback();
                LOG.log(Level.SEVERE, e.getMessage() , e); 
            }
        }
        return false;
        
    }
    
    @Override
    public boolean actualizarNombreUsuario(Usuario usuario){
        Usuario usuarioBuscado = em.find(Usuario.class, usuario.getId());
        if(usuarioBuscado != null){
            try {
                em.getTransaction().begin();
                usuarioBuscado.setNombre(usuario.getNombre());
                //usuarioBuscado = em.merge(usuarioBuscado);
                System.out.println("usuario act: "+usuarioBuscado);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                em.getTransaction().rollback();
                LOG.log(Level.SEVERE, e.getMessage() , e); 
            }
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.remove(usuario);
            System.out.println("usuario eliminado: " + usuario);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }
}
