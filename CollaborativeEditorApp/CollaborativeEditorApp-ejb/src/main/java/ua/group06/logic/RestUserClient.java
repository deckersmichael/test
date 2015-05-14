/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import ua.group06.entities.User;
import ua.group06.filters.ClientAuthenticationFilter;

/**
 *
 * @author matson
 */
public class RestUserClient {
    private final String ROOT = "http://localhost:8080/UserManagementApp-web/resources/users";

    // Add authentication filter to default client.
    private Client getClient() {
        Client client = ClientBuilder.newClient();
        client.register(new ClientAuthenticationFilter());
        return client;
    }

    public User login(String email, String password) {
        Client client = getClient();
        User user = client
                .target(ROOT + "/login")
                .queryParam("email", email)
                .queryParam("password", password)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }


    public User loginLDAB(String login, String password) {
        Client client = getClient();
        User user = client
                .target(ROOT + "/loginLDAB")
                .queryParam("login", login)
                .queryParam("password", password)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }

    public User loginTwitter(String username) {
        Client client = getClient();
        User user = client
                .target(ROOT + "/loginTwitter")
                .queryParam("username", username)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }

    public User get(Long id) {
        Client client = getClient();
        User user = client
                .target(ROOT + "/" + id.toString())
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }

    public User getByEmail(String email) {
        Client client = getClient();
        User user = client
                .target(ROOT + "/findByEmail")
                .queryParam("email", email)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
        return user;
    }

}
