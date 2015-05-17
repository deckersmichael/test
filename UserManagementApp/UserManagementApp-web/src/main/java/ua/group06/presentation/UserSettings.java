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
        String email    = request.getParameter("email");
        if(user instanceof ExternalUser){
            String output = changeExternalUser((ExternalUser) user, fname, lname, email);
            session.setAttribute("message", output);
        }
        else if(user instanceof User){
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            String currentPassword = request.getParameter("currentPassword");
            String output = changeStandardUser((User)user, fname, lname, email, newPassword, confirmPassword, currentPassword);
            session.setAttribute("message", output);
        }
        response.sendRedirect("settings");
    }
    
    String changeStandardUser(User user, String fname,String lname, String email , String newPassword, String confirmPassword ,String currentPassword){
        if(userService.checkPassword(currentPassword ,user.getPassword())){

            if(fname!=null && !fname.isEmpty()){
                user.setFirstName(fname);
            }
           
            if(lname!=null && !lname.isEmpty()){
                user.setLastName(lname);
            }
            
            if(user.getEmail().equals(email) || userService.checkEmailAvailible(email)){
                if(email!=null && !email.isEmpty()){
                    user.setEmail(email);
                }
            }
            else{//if the email is changed and unavailible
                return "Email is already in use";
            }
            
            if((newPassword!=null && confirmPassword!=null) && (!newPassword.isEmpty() && !confirmPassword.isEmpty())){
                if(newPassword.equals(confirmPassword)){
                    user.setPassword(newPassword);
                    userService.editPassword(user);
                }
                else{
                    return "The new passwords do not match";
                }
            }
            else{
               userService.edit(user);
            }
            return "Editing settings succesfull!";
        }
        return "Current password was incorrect";
    }
    
    String changeExternalUser(ExternalUser user, String fname,String lname, String email){
         
            if(fname!=null && !fname.isEmpty()){
                user.setFirstName(fname);
            }

            if(lname!=null && !lname.isEmpty()){
                user.setLastName(lname);
            }
            if(user.getEmail().equals(email) || userService.checkEmailAvailible(email)){
                if(email!=null && !email.isEmpty()){
                    user.setEmail(email);
                }
            }
            else if(!user.getEmail().equals(email)){//if the email is changed and unavailible
                return "Email is already in use";
            }
            userService.edit(user);
            return "Editing settings succesfull";
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
