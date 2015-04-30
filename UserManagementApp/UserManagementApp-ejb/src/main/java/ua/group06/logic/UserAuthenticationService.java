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
    @EJB
    private PasswordEncryptionServiceLocal passwordService;

    @Override
    // Exceptions for errors? Constant null cecking is bad.
    public User authenticate(String email, String password) {
        User user = userFacade.findByEmail(email);
        if (user == null) { return null; }
        String encryptedPassword = user.getPassword();
        boolean matching = passwordService.checkPassword(password, encryptedPassword);
        return matching ? user : null;
    }

}
