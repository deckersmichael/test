/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import ua.group06.business.UserFacadeLocal;
import ua.group06.persistence.AbstractUser;
import ua.group06.persistence.ExternalUser;
import ua.group06.persistence.User;

/**
 *
 * @author Yves Maris
 */
@Stateless
public class ExternalUserService implements ExternalUserServiceLocal{
    @EJB
    private UserFacadeLocal userFacade;

    @Override
    public ExternalUser authenticateOrCreate(String username, String email, String firstName, String lastName) {
        ExternalUser user = userFacade.findByUsername(username);
       if (user == null){
           System.err.println("Creating new user " + firstName + lastName);
           user = new ExternalUser(username, firstName, lastName, email);
           try {
            userFacade.create( user );
        } catch (EntityExistsException e) {
            // TODO: Should we change the return type or should we throw exception?
            user = null;
        }
       }
       return user;
    }
}
