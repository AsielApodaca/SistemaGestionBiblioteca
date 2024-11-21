/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Bibliotecario;
import interfaces.IBibliotecarioDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author luisa M
 */
public class BibliotecarioDAO implements IBibliotecarioDAO{
    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG = Logger.getLogger(BibliotecarioDAO.class.getName());

    public BibliotecarioDAO() {
        em = ConexionBD.getEntityManager();
        cb = em.getCriteriaBuilder();
    }
    
    @Override
    public Bibliotecario obtenerBibliotecario(String id){
        CriteriaQuery<Bibliotecario> criteria=cb.createQuery(Bibliotecario.class);
        Root<Bibliotecario> root=criteria.from(Bibliotecario.class);

        Predicate predicate = cb.equal(root.get("id"), id);
        criteria.select(root).where(predicate);
        
        TypedQuery<Bibliotecario> query=em.createQuery(criteria);
        try {
            Bibliotecario bibliotecarioConsultado=query.getSingleResult();
            return bibliotecarioConsultado;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage() , e);
        }
        return null;
    }
}
