/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import ua.group06.entities.User;

/**
 *
 * @author matson
 */
public class RestUserClient {
    private final String ROOT = "http://localhost:8080/UserManagementApp-web/resources";
    
    public User register(User user){
        Client client = ClientBuilder.newClient();
        User createdUser;
        createdUser = client
                .target(ROOT + "/users")
                .request("application/json")
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), User.class);
        return createdUser;
    }
    
}
