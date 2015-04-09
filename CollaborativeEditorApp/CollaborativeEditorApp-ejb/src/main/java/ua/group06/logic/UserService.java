/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.Stateless;
import ua.group06.entities.User;

/**
 *
 * @author matson
 */
@Stateless
public class UserService implements UserServiceLocal {
    private RestUserClient restClient = new RestUserClient();

    @Override
    public User register(User user) {
        return restClient.register(user);
    }

    @Override
    public User login(String email, String password) {
        return new User("a", "b", email, "c");
    }
    
}
