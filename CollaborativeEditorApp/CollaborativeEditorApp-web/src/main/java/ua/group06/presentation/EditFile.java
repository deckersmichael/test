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
import ua.group06.entities.User;
import ua.group06.logic.FileServiceLocal;
import ua.group06.persistence.File;
import ua.group06.util.ServletUtil;

/**
 *
 * @author matson
 */
@WebServlet(name = "EditFile", urlPatterns = {"/file"})
public class EditFile extends HttpServlet {

    @EJB
    private FileServiceLocal fileService;

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
        String fidString = request.getParameter("id");
        if (fidString != null) {
            Long fid = Long.parseLong(fidString);
            User user = ServletUtil.currentUser(request);
            File file = fileService.show(fid, user);
            request.setAttribute("file", file);
            request.getRequestDispatcher("file.jsp").forward(request, response);
        } else {
            response.sendRedirect("homepage");
        }
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
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String fidString = request.getParameter("id");
        if (fidString != null) {
            Long id = Long.parseLong(fidString);
            User user = ServletUtil.currentUser(request);
            fileService.update(id, user, name, title, content);
        }
        response.sendRedirect("files");
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