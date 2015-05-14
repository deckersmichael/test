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
import org.json.JSONArray;
import org.json.JSONException;
import ua.group06.business.UserFacadeLocal;
import ua.group06.logic.ExternalUserServiceLocal;
import ua.group06.logic.RestUserClient;
import ua.group06.logic.UserAuthenticationServiceLocal;
import ua.group06.logic.UserRegistrationServiceLocal;
import ua.group06.persistence.AbstractUser;
import ua.group06.persistence.ExternalUser;
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
    @EJB
    private ExternalUserServiceLocal externalUserService;

    @GET
    @Path("/{id}")
    public Response find(@PathParam("id") Long id) {
        AbstractUser user = userFacade.find(id);
        if (user instanceof User)
            return Response.ok((User) user).build();
        else
            return Response.ok((ExternalUser) user).build();
    }
    
    @GET
    @Path("/findByEmail")
    public Response findByEmail(@QueryParam("email") String email) {
        User user = (User) userFacade.findByEmail(email);
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
    
    @GET
    @Path("loginLDAB")
    public Response loginLDAB(@QueryParam("login") String login,
                          @QueryParam("password") String password) {
        
        RestUserClient restClient=new RestUserClient();
        JSONArray userinfo = restClient.LDABlogin(login, password);
        //System.err.println(login +" "+ password);
        //System.err.println(parser);
        ExternalUser foundUser = null;
        if(userinfo!=null){
            
            try {
                if(userinfo.get(0).toString().equals("01")){
                    String fname = userinfo.get(2).toString();
                    String lname = userinfo.get(3).toString();
                    String email = userinfo.get(4).toString();
                    foundUser = externalUserService.authenticateOrCreate(login, email, fname, lname);
                }
            } catch (JSONException ex) {
                //response.sendRedirect("homepage");
            }
        }
        return Response.ok(foundUser).build();
    }
    
    @GET
    @Path("loginTwitter")
    public Response loginTwitter(@QueryParam("username") String username) {
        ExternalUser foundUser = externalUserService.authenticateOrCreate(username, "", "", "");
        return Response.ok(foundUser).build();
    }

}
