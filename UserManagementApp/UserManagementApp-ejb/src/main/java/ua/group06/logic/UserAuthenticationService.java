/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import ua.group06.business.UserFacadeLocal;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
public class UserAuthenticationService implements UserAuthenticationServiceLocal {
    @EJB
    private UserFacadeLocal userFacade;

    @Override
    public User authenticate(String email, String password) {
        // TODO: this uses just email and doesn't care about errors.
        return userFacade.findByEmail(email);
    }
    
}
