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
import ua.group06.persistence.Session;

/**
 *
 * @author matson
 */
@Stateless
public class FileService implements FileServiceLocal {

    @EJB
    private FileFacadeLocal fileFacade;
    @EJB
    private SessionServiceLocal sessionService;

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

    // Update file content if user is allowed to edit given file.
    // This is used by web service.
    @Override
    public void updateContent(Long fid, String token, String content) {
        File file = fileFacade.find(fid);
        if (allowedToEdit(token, file.getUserId())) {
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

    // Check if user is allowed to edit given file.
    private boolean allowedToEdit(String token, Long uid) {
        Session session = sessionService.findByToken(token);
        return session.getUserId().equals(uid);
    }

}
