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
import org.json.JSONArray;
import org.json.JSONException;
import ua.group06.logic.ExternalUserServiceLocal;
import ua.group06.logic.RestUserClient;
import ua.group06.persistence.ExternalUser;

/**
 *
 * @author Yves Maris
 */
@WebServlet(name = "UserLoginLDAB", urlPatterns = {"/loginLDAB"})
public class UserLoginLDAB extends HttpServlet {
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
        HttpSession session = request.getSession();
        String login    = request.getParameter("email");
        String password = request.getParameter("password");
        restClient=new RestUserClient();
        JSONArray userinfo = restClient.LDABlogin(login, password);
        //System.err.println(login +" "+ password);
        //System.err.println(parser);
        if(userinfo!=null){
            
            try {
                if(userinfo.get(0).toString().equals("01")){
                    String fname = userinfo.get(2).toString();
                    String lname = userinfo.get(3).toString();
                    String email = userinfo.get(4).toString();
                    ExternalUser user = userService.authenticateOrCreate(login, email, fname, lname);
                    if (user != null) {
                        session.setAttribute("user", user);
                    }
                    else{
                        session.setAttribute("message", "login failed, check your username and password.");
                    }
                }
            } catch (JSONException ex) {
                //response.sendRedirect("homepage");
            }
        }
        else{
            session.setAttribute("message", "login failed, check your username and password.");
        }
        response.sendRedirect("homepage");
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
