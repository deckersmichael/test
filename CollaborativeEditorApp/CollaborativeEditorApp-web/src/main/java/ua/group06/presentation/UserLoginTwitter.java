/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.presentation;

import java.io.IOException;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import ua.group06.entities.User;
import ua.group06.logic.SessionServiceLocal;
import ua.group06.logic.TwitterAuthentication;
import ua.group06.logic.UserServiceLocal;

/**
 *
 * @author Yves Maris
 */
@WebServlet(name = "UserLoginTwitter", urlPatterns = {"/loginTwitter"})
public class UserLoginTwitter extends HttpServlet {
    TwitterAuthentication twitterauth;
    @EJB
    UserServiceLocal userService;
    @EJB
    private SessionServiceLocal sessionService;
    
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
        Twitter twitter = twitterauth.getTwitter();
        RequestToken requestToken = twitterauth.getRequestToken();
        //RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        System.err.println(twitter+ " "+requestToken+ " " +verifier);
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            /*System.err.println(accessToken.getScreenName());
            User twitterUser = twitter.showUser(accessToken.getUserId());
            String fullName = twitterUser.getName();
            String fName="";
            String lName="";
            String[] names = fullName.split(" ");
            if(names.length==1){
                fName=names[0];
            }
            else if(names.length>=2){
                fName=names[0];
                lName=names[names.length-1];
            }*/
            User user = userService.loginTwitter(accessToken.getScreenName());
            
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("session", sessionService.create(user.getId()));
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
        twitterauth=new TwitterAuthentication();
        try {
            response.sendRedirect(twitterauth.getauthenticationURL(/*"http://127.0.0.1:8080/UserManagementApp-web/loginTwitter"*/));
        } catch (TwitterException ex) {
            java.util.logging.Logger.getLogger(UserLoginTwitter.class.getName()).log(Level.SEVERE, null, ex);
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