/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.Stateless;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
public class PasswordEncryptionService implements PasswordEncryptionServiceLocal {

    @Override
    public User encrypt(User user) {
        String encrypted = "secret";
        user.setPassword(encrypted);
        return user;
    }
    
}
