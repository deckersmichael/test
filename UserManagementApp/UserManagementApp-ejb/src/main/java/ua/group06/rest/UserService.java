/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import ua.group06.logic.UserRegistrationServiceLocal;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@Stateless
@Path("users")
public class UserService extends RestResource {
    @EJB
    private UserRegistrationServiceLocal regService;
    
    @POST
    public Response register(User user) {
        User createdUser = regService.register(user);
        return Response.ok(createdUser).build();
    }

}
