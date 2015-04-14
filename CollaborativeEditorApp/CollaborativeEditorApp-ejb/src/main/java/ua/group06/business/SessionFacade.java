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
import ua.group06.persistence.Session;

/**
 *
 * @author matson
 */
@Stateless
public class SessionFacade extends AbstractFacade<Session> implements SessionFacadeLocal {
    @PersistenceContext(unitName = "ua.group06_CollaborativeEditorApp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionFacade() {
        super(Session.class);
    }

    @Override
    public Session findByToken(String token) {
        List results = em.createNamedQuery(
                "Session.findByToken")
                .setParameter("token", token)
                .getResultList();
        return results.isEmpty() ? null : (Session) results.get(0);
    }

}
