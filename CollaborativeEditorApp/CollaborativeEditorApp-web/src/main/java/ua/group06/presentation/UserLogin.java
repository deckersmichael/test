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
import ua.group06.entities.User;
import ua.group06.logic.SessionServiceLocal;
import ua.group06.logic.UserServiceLocal;

/**
 *
 * @author matson
 */
@WebServlet(name = "UserLogin", urlPatterns = {"/login"})
public class UserLogin extends HttpServlet {
    @EJB
    private UserServiceLocal userService;
    @EJB
    private SessionServiceLocal sessionService;


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
        String type = request.getParameter("confirm");
        User user = null;
        if(type.equals("Standard")){
            user = userService.login(email, password);
        }
        else if(type.equals("LDAB")){
            user = userService.loginLDAB(email, password);
        }
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("session", sessionService.create(user.getId()));
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
