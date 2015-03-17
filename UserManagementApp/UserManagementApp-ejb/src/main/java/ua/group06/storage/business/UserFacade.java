/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.storage.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ua.group06.storage.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {
    @PersistenceContext(unitName = "ua.group06_UserManagementApp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User findByEmail(String email) {
        List results = em.createNamedQuery(
                "User.findByEmail")
                .setParameter("email", email)
                .getResultList();
        return results.isEmpty() ? null : (User) results.get(0);
    }
    
}
