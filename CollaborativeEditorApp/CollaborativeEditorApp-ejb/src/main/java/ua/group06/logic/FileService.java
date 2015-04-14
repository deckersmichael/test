/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import ua.group06.business.FileFacadeLocal;
import ua.group06.entities.User;
import ua.group06.persistence.File;

/**
 *
 * @author matson
 */
@Stateless
public class FileService implements FileServiceLocal {

    @EJB
    private FileFacadeLocal fileFacade;

    @Override
    public File create(File file) {
        try {
            fileFacade.create(file);
        } catch (EntityExistsException e) {
            // TODO: Should we change the return type or should we throw exception?
            file = null;
        }
        return file;
    }

    @Override
    public int fileCount() {
        return fileFacade.count();
    }

    @Override
    public List<File> filesForUser(User user) {
        return fileFacade.findAllForUser(user.getId());
    }

    @Override
    public File show(Long fid, User user) {
        File file = fileFacade.find(fid);
        return allowed(file, user) ? file : null;
    }

    @Override
    public void update(Long id, User user, String content) {
        File file = fileFacade.find(id);
        if (allowed(id, user)) {
            file.setContent(content);
            fileFacade.edit(file);
        }
    }

    @Override
    public void delete(Long fid, User user) {
        File file = fileFacade.find(fid);
        if (allowed(fid, user)) {
            fileFacade.remove(file);
        }
    }

    @Override
    public void updateContent(Long fid, String token, String content) {
        if (allowedToEdit(token, fid)) {
            File file = fileFacade.find(fid);
            file.setContent(content);
            fileFacade.edit(file);
        }
    }

    private boolean allowed(Long fid, User user) {
        return fid.equals(user.getId());
    }

    private boolean allowed(File file, User user) {
        return allowed(file.getUserId(), user);
    }

    // TODO: implement
    private boolean allowedToEdit(String token, Long fid) {
        return true;
    }

}
