/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.Stateless;
import org.mindrot.jbcrypt.BCrypt;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
public class PasswordEncryptionService implements PasswordEncryptionServiceLocal {
    // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
    private static final int workload = 12;

    @Override
    public User encrypt(User user) {
        String encrypted = encryptPassword(user.getPassword());
        user.setPassword(encrypted);
        return user;
    }
    
    @Override
    public boolean checkPassword(String cleartext, String encrypted) {
        if (null == encrypted || !encrypted.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }
        return BCrypt.checkpw(cleartext, encrypted);
    }

    private static String encryptPassword(String plaintext) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(plaintext, salt);
    }
    
}
