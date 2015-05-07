/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.io.StringReader;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author matson
 */
public class RestUserClient {
    private final String ROOT = "http://143.129.83.25:8080/LDAPQuery/resources/DC2015";
    
    
    
    public JSONArray LDABlogin(String user, String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedUser = encoder.encodeToString(user.getBytes());
        String encodedPassword = encoder.encodeToString(password.getBytes());
        //Create the rest client
        Client client= ClientBuilder.newClient();
        WebTarget resourceTarget = client.target(ROOT);
        Invocation inv = resourceTarget.queryParam("user", encodedUser).queryParam("password", encodedPassword).request("application/json").buildGet();
        String response = inv.invoke(String.class);
        JSONArray  result = null;
        try {
            result = new JSONArray(response);
        } catch (JSONException ex) {
            Logger.getLogger(RestUserClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
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
