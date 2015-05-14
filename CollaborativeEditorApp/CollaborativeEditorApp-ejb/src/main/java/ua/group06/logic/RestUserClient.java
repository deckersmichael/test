/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

//import java.util.Base64;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import ua.group06.entities.User;

/**
 *
 * @author matson
 */
public class RestUserClient {
    private final String ROOT = "http://localhost:8080/UserManagementApp-web/resources/users";
    
    public User login(String email, String password) {
        Client client = ClientBuilder.newClient();
        User user = client
                .target(ROOT + "/login")
                .queryParam("email", email)
                .queryParam("password", password)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }
    
    
    
    public User loginLDAB(String login, String password) {
        Client client = ClientBuilder.newClient();
        User user = client
                .target(ROOT + "/loginLDAB")
                .queryParam("login", login)
                .queryParam("password", password)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }
    
    public User loginTwitter(String username) {
        Client client = ClientBuilder.newClient();
        User user = client
                .target(ROOT + "/loginTwitter")
                .queryParam("username", username)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }
        
        /*Client client = ClientBuilder.newClient();
        User user = client
                .target(ROOT + "/login")
                .queryParam("email", email)
                .queryParam("password", password)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);*/
        //return user;
}
