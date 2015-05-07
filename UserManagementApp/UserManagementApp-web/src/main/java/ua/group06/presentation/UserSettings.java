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
import ua.group06.logic.UserAuthenticationServiceLocal;
import ua.group06.logic.UserSettingsServiceLocal;
import ua.group06.persistence.AbstractUser;
import ua.group06.persistence.ExternalUser;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@WebServlet(name = "UserSettings", urlPatterns = {"/settings"})
public class UserSettings extends HttpServlet {
    @EJB
    private UserSettingsServiceLocal userService;
    @EJB
    private UserAuthenticationServiceLocal authService;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("settings.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        AbstractUser user = (AbstractUser) session.getAttribute("user");
        String fname    = request.getParameter("firstName");
        String lname    = request.getParameter("lastName");
        if(user instanceof ExternalUser){
            changeExternalUser((ExternalUser) user, fname, lname);
        }
        else if(user instanceof User){
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            String currentPassword = request.getParameter("currentPassword");
            changeStandardUser((User)user, fname, lname, newPassword, confirmPassword, currentPassword);
        }
        response.sendRedirect("settings");
    }
    
    void changeStandardUser(User user, String fname,String lname , String newPassword, String confirmPassword ,String currentPassword){
         //user = authService.authenticate(email, currentPassword);
        if(userService.checkPassword(currentPassword ,user.getPassword())){
            //User newInfo = new User();
            //newInfo.setEmail(email);
            //newInfo.setId(user.getId());

            if(fname!=null && !fname.isEmpty()){
                user.setFirstName(fname);
            }
            /*else{
                newInfo.setFirstName(user.getFirstName());
            }*/

            if(lname!=null && !lname.isEmpty()){
                user.setLastName(lname);
            }
            /*else{
                newInfo.setLastName(user.getLastName());
            }*/
            if((newPassword!=null && confirmPassword!=null) && (!newPassword.isEmpty() && !confirmPassword.isEmpty())){
                if(newPassword.equals(confirmPassword)){
                    user.setPassword(newPassword);
                    userService.editPassword(user);
                }
                else{
                    //request.setAttribute("message", "The new passwords do not match");
                }
            }
            else{
               userService.edit(user);
            }
        }
    }
    
    void changeExternalUser(ExternalUser user, String fname,String lname){
         
            if(fname!=null && !fname.isEmpty()){
                user.setFirstName(fname);
            }

            if(lname!=null && !lname.isEmpty()){
                user.setLastName(lname);
            }
            userService.edit(user);
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
