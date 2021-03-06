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
import ua.group06.logic.UserRegistrationServiceLocal;
import ua.group06.persistence.User;

/**
 *
 * @author matson
 */
@WebServlet(name = "UserRegistration", urlPatterns = {"/registration"})
public class UserRegistration extends HttpServlet {
    @EJB
    private UserRegistrationServiceLocal userService;


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
        request.getRequestDispatcher("registration.jsp").forward(request, response);
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
        String fname    = request.getParameter("firstName");
        String lname    = request.getParameter("lastName");
        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(fname, lname, email, password);
        User createdUser = userService.register(user);
        if (createdUser != null) {
            session.setAttribute("user", user);
            response.sendRedirect("homepage");
        }
        else{
            session.setAttribute("message", "The email you entered is already in use");
            response.sendRedirect("registration");
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
    }// </editor-fold>

}
