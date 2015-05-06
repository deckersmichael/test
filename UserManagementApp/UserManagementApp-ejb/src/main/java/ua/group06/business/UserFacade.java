/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ua.group06.persistence.AbstractUser;
import ua.group06.persistence.ExternalUser;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
public class UserFacade extends AbstractFacade<AbstractUser> implements UserFacadeLocal {
    @PersistenceContext(unitName = "ua.group06_UserManagementApp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(AbstractUser.class);
    }

    @Override
    public AbstractUser findByEmail(String email) {
        List results = em.createNamedQuery(
                "User.findByEmail")
                .setParameter("email", email)
                .getResultList();
        return results.isEmpty() ? null : (AbstractUser) results.get(0);
    }
    
    @Override
    public ExternalUser findByUsername(String username) {
        List results = em.createNamedQuery(
                "ExternalUser.findByUsername")
                .setParameter("username", username)
                .getResultList();
        return results.isEmpty() ? null : (ExternalUser) results.get(0);
    }
    
}
