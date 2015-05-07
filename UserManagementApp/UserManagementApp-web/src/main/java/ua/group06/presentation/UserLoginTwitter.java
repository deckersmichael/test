/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.presentation;


import java.io.IOException;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.group06.logic.ExternalUserServiceLocal;
import ua.group06.logic.RestUserClient;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import ua.group06.persistence.AbstractUser;
import ua.group06.persistence.ExternalUser;

/**
 *
 * @author Yves Maris
 */
@WebServlet(name = "UserLoginTwitter", urlPatterns = {"/loginTwitter"})
public class UserLoginTwitter extends HttpServlet {
    final String CONSUMER_KEY="xYANYnNIbrnOH2XREtJgK4Iki";
    final String CONSUMER_SECRET="WmNSUtkJAjZAGTSsRRdlROQqnztGeRHQGbzqzIyxekCj5tFqO8";
    private Twitter twitter;
    private RequestToken requestToken;
    
    @EJB
    private ExternalUserServiceLocal userService;
     /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
        //RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        System.err.println(twitter+ " "+requestToken+ " " +verifier);
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            System.err.println(accessToken.getScreenName());
            User twitterUser = twitter.showUser(accessToken.getUserId());
            String fullName = twitterUser.getName();
            String fName="";
            String lName="";
            String[] names = fullName.split(lName);
            if(names.length==1){
                fName=names[0];
            }
            else if(names.length==2){
                fName=names[0];
                lName=names[1];
            }
            ExternalUser user = userService.authenticateOrCreate(accessToken.getScreenName(), fName, lName, "");
            
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
            }
            //request.getSession().removeAttribute("requestToken");
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
        response.sendRedirect("homepage");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String email    = request.getParameter("email");
        //String password = request.getParameter("password");
        //System.err.println("test");

        ConfigurationBuilder cb = new ConfigurationBuilder();

       // cb.setApplicationOnlyAuthEnabled(true);

        //cb.setOAuth2TokenType(token.getTokenType());
        //cb.setOAuth2AccessToken(token.getAccessToken());
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET);
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        this.twitter=twitter;
        //request.setAttribute("twitter", twitter);
        try {
            StringBuffer callbackURL = request.getRequestURL();
            RequestToken requestToken = twitter.getOAuthRequestToken(/*callbackURL.toString()*/);
            this.requestToken = requestToken;
            //request.getSession().setAttribute("requestToken", requestToken);
            response.sendRedirect(requestToken.getAuthenticationURL());
            
        } catch (TwitterException ex) {
            System.err.println("Could not access twitter user");
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
