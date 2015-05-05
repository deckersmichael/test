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

import ua.group06.logic.ExternalUserServiceLocal;
import ua.group06.logic.RestUserClient;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Yves Maris
 */
@WebServlet(name = "UserLoginFB", urlPatterns = {"/loginTwitter"})
public class UserLoginTwitter extends HttpServlet {
    RestUserClient restClient;
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        OAuth2Token token;
        //token = getOAuth2Token();

        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey("xYANYnNIbrnOH2XREtJgK4Iki");
        cb.setOAuthConsumerSecret("WmNSUtkJAjZAGTSsRRdlROQqnztGeRHQGbzqzIyxekCj5tFqO8");
 //       cb.setOAuth2TokenType(token.getTokenType());
 //       cb.setOAuth2AccessToken(token.getAccessToken());

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
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
