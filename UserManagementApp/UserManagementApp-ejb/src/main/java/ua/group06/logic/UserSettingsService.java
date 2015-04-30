/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import ua.group06.persistence.User;
import ua.group06.business.UserFacadeLocal;

/**
 *
 * @author Yves Maris
 */
@Stateless
public class UserSettingsService implements UserSettingsServiceLocal {
    @EJB
    private UserFacadeLocal userFacade;
    @EJB
    private PasswordEncryptionServiceLocal passwordService;
    @Override
    public User edit(User user) {
        try {
            userFacade.edit(user);
        } catch (EntityExistsException e) {
            // TODO: Should we change the return type or should we throw exception?
            user = null;
        }
        return user;
    }

    @Override
    public User editPassword(User user) {
        try {
            userFacade.edit(passwordService.encrypt(user));
        } catch (EntityExistsException e) {
            // TODO: Should we change the return type or should we throw exception?
            user = null;
        }
        return user;
    }
    
}
