/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.business;

import java.util.List;
import javax.ejb.Local;
import ua.group06.persistence.AbstractUser;
import ua.group06.persistence.ExternalUser;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Local
public interface UserFacadeLocal {

    void create(AbstractUser user);

    void edit(AbstractUser user);

    void remove(AbstractUser user);

    AbstractUser find(Object id);

    List<AbstractUser> findAll();

    List<AbstractUser> findRange(int[] range);

    int count();

    AbstractUser findByEmail(String email);
    
    ExternalUser findByUsername(String username);
    
}
