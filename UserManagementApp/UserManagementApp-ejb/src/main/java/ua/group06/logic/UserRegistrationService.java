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
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
public class UserRegistrationService implements UserRegistrationServiceLocal {
    @EJB
    private UserFacadeLocal userFacade;
    @EJB
    private PasswordEncryptionServiceLocal passwordService;

    @Override
    public User register(User user) {
        if (userFacade.findByEmail(user.getEmail())!=null){
            user=null;
        }
        else{
            try {
                userFacade.create( passwordService.encrypt(user) );
            } catch (EntityExistsException e) {
                // TODO: Should we change the return type or should we throw exception?
                user = null;
            }
        }
        return user;
    }
    
}
