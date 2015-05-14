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
import ua.group06.persistence.Chat;

/**
 *
 * @author Michael
 */
@Stateless
public class ChatFacade extends AbstractFacade<Chat> implements ChatFacadeLocal {
    @PersistenceContext(unitName = "ua.group06_CollaborativeEditorApp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ChatFacade() {
        super(Chat.class);
    }

    @Override
    public List<Chat> findForFile(Long fid) {
        List results = em.createNamedQuery(
                "Chat.findForFile")
                .setParameter("fid", fid)
                .getResultList();
        return results;
    }

    @Override
    public List<Chat> findRecentForFile(Long fid, Long time) {
        List results = em.createNamedQuery(
                "Chat.findRecentForFile")
                .setParameter("fid", fid)
                .setParameter("time", time)
                .getResultList();
        return results;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
