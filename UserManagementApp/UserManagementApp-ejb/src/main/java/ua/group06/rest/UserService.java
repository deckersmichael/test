/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ua.group06.logic.UserRegistrationServiceLocal;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
//@Path("/user")
@ApplicationPath("/resources")
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends Application {
    @EJB
    private UserRegistrationServiceLocal regService;
    
    @POST
    public Response register(User user) {
        User createdUser = regService.register(user);
        return Response.ok(createdUser).build();
    }

//    @POST
//    public boolean register(String firstName, String lastName, String email, String password) {
//        return true;
//    }

}
