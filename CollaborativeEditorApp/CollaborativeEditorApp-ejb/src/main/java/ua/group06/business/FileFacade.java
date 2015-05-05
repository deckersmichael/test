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
import ua.group06.persistence.File;
import ua.group06.persistence.PersistString;

/**
 *
 * @author matson
 */
@Stateless
public class FileFacade extends AbstractFacade<File> implements FileFacadeLocal {
    @PersistenceContext(unitName = "ua.group06_CollaborativeEditorApp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FileFacade() {
        super(File.class);
    }

    @Override
    public List<File> findAllForUser(Long uid, String uemail) {
        List results = em.createNamedQuery(
                "File.findAllForUser")
                .setParameter("uid", uid)
                .getResultList();
        List results2 = em.createNamedQuery(
                "File.findAllForCollabs")
                .setParameter("uemail", new PersistString(uemail))
                .getResultList();
        results.addAll(results2);
        return results;
            
    }

}
