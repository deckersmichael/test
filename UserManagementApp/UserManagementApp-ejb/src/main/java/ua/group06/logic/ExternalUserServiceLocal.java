/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.Local;
import ua.group06.persistence.AbstractUser;
import ua.group06.persistence.ExternalUser;
import ua.group06.persistence.User;

/**
 *
 * @author Yves Maris
 */
@Local
public interface ExternalUserServiceLocal {
     ExternalUser authenticateOrCreate(String username, String email, String firstName, String lastName);
}
