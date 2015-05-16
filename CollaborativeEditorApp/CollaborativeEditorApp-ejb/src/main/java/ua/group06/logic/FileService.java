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
    private MergeBeanLocal merge;
    @EJB
    private FileFacadeLocal fileFacade;
    @EJB
    private SessionServiceLocal sessionService;

    private RestUserClient ruc = new RestUserClient();

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
        return allowedFile(fid, user) ? file : null;
    }

    @Override
    public void update(Long id, User user, String content) {
        File file = fileFacade.find(id);
        if (allowedFile(id, user)) {
            //String result = merge.getUpdatedFile(content);
            file.setContent(content);
            fileFacade.edit(file);
        }
    }

    @Override
    public void delete(Long fid, User user) {
        File file = fileFacade.find(fid);
        if (allowedFile(fid, user)) {
            fileFacade.remove(file);
        }
    }

    // Update file content if user is allowed to edit given file.
    // This is used by web service.
    @Override
    public String updateContent(Long fid, String token, String browserID, String content, String changes, String timeDate) {
        File file = fileFacade.find(fid);
        String result = null;
        if (allowedToEdit(token, file)) {
            result = merge.getUpdatedFile(fid, token, browserID, changes, timeDate);
        }
        return result;
    }

    private boolean allowedUser(Long uid, User user) {
        return uid.equals(user.getId());
    }
    
    /**
     * checks whether a certain user can modify a file
     * TODO: add collaborators to check
     * @param fid file ID
     * @param user the user trying to modify
     * @return whether the user can modify
     */
    private boolean allowedFile(Long fid, User user) {
        return fileFacade.find(fid).getUserId().equals(user.getId()) || fileFacade.find(fid).getCollabIds().contains(user.getId());
    }

    private boolean allowed(File file, User user) {
        return allowedUser(file.getUserId(), user);
    }

    // Check if user is allowed to edit given file.
    private boolean allowedToEdit(String token, File file) {
        Session session = sessionService.findByToken(token);
        return file.getUserId().equals(session.getUserId()) || file.getCollabIds().contains(session.getUserId());
    }

    @Override
    public boolean updateCollabs(Long fid, User user, boolean edit, Long newid, boolean add) {
        File file = fileFacade.find(fid);
        boolean worked = false;
        if (allowedFile(fid, user)) {
            if (edit){
                User addUser = ruc.get(newid);
                if (addUser != null){
                    if (add){
                        worked = file.addCollaborator(addUser.getId());
                    }
                    else {
                        worked = file.removeCollaborator(addUser.getId());
                    }
                } else {
                    worked = false;
                }
            /*else 
                if (add)
                    file.addSpectator(newid);
                else
                    file.removeSpectator(newid);  */
            }
            fileFacade.edit(file);
        }
        return worked;
    }

    @Override
    public File getFile(Long fid) {
        return fileFacade.find(fid);
    }

}
