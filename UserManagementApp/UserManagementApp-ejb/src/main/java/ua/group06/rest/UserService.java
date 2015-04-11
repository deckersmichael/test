/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ua.group06.business.UserFacadeLocal;
import ua.group06.logic.UserAuthenticationServiceLocal;
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
    @EJB
    private UserAuthenticationServiceLocal authService;
    @EJB
    private UserFacadeLocal userFacade;

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        User user = userFacade.find(id);
        return Response.ok(user).build();
    }
    
    @POST
    public Response register(User user) {
        User createdUser = regService.register(user);
        return Response.ok(createdUser).build();
    }

    @GET
    @Path("login")
    public Response login(@QueryParam("email") String email,
                          @QueryParam("password") String password) {
        User foundUser = authService.authenticate(email, password);
        return Response.ok(foundUser).build();
    }

}
