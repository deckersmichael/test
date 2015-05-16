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
import ua.group06.logic.RestUserClient;
import ua.group06.persistence.File;
import ua.group06.util.ServletUtil;

/**
 *
 * @author Michael Deckers
 */
@WebServlet(name = "FileShares", urlPatterns = {"/shares"})
public class FileShares extends HttpServlet {

    @EJB
    private FileServiceLocal fileService;

    private RestUserClient ruc = new RestUserClient();

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
            request.setAttribute("worked", true);
            request.getRequestDispatcher("fileshares.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        String type = request.getParameter("type");
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        
        User user = ServletUtil.currentUser(request);
        boolean worked = false;
        if (name != null && !name.isEmpty()) {
            worked = fileService.updateCollabs(Long.parseLong(id), user, /*type.equals("collab")*/true, ruc.getByEmail(name).getId(), action.equals("add"));  
        }
        Long fid = Long.parseLong(id);
        File file = fileService.show(fid, user);
        request.setAttribute("file", file);
        request.setAttribute("worked", worked);
        request.getRequestDispatcher("fileshares.jsp").forward(request, response);
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
