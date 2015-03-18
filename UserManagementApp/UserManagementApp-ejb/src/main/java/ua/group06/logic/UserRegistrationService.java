/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import ua.group06.storage.business.UserFacadeLocal;
import ua.group06.storage.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
public class UserRegistrationService implements UserRegistrationServiceLocal {
    
    @EJB
    private UserFacadeLocal userFacade;

    @Override
    public User register(final String firstName, final String lastName, 
            final String email, final String password) {
        User user = new User(firstName, lastName, email, password);
        try {
            userFacade.create(user);
        } catch (EntityExistsException e) {
            // TODO: Should we change the return type or should we throw exception?
            user = null;
        }
        return user;
    }
    
}
