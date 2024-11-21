/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Usuario;
import interfaces.IUsuarioDAO;
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
    public Usuario obtenerUsuario(String id){
        CriteriaQuery<Usuario> criteria=cb.createQuery(Usuario.class);
        Root<Usuario> root=criteria.from(Usuario.class);

        Predicate predicate = cb.equal(root.get("id"), id);
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
        StoredProcedureQuery spc=em.createStoredProcedureQuery("sp_agregar_usuario",Usuario.class);
        try {
            if (spc.execute()) {
                Long idNuevo = (Long)spc.getOutputParameterValue("idNuevo");
                usuario.setId(idNuevo);
                return usuario;
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage() , e); 
        }
        return null;
    }
    
    public boolean actualizarEmailUsuario(Usuario usuario){
        Usuario usuarioBuscado = em.find(Usuario.class, usuario.getId());
        if(usuarioBuscado != null){
            try {
                usuarioBuscado.setEmail(usuario.getEmail());
                usuarioBuscado = em.merge(usuarioBuscado);
                System.out.println("usuario act: "+usuarioBuscado);
                return true;
            } catch (Exception e) {
                LOG.log(Level.SEVERE, e.getMessage() , e); 
            }
        }
        return false;
        
    }
    
    public boolean actualizarNombreUsuario(Usuario usuario){
        
    }
}
