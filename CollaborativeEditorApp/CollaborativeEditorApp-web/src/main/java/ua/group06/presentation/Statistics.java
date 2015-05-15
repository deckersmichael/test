/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.group06.entities.User;
import ua.group06.logic.FileServiceLocal;
import ua.group06.logic.RestUserClient;
import ua.group06.logic.StatisticsBeanLocal;
import ua.group06.persistence.File;
import ua.group06.util.ServletUtil;

/**
 *
 * @author Michael
 */
@WebServlet(name = "Statistics", urlPatterns = {"/Statistics"})
public class Statistics extends HttpServlet {
    @EJB
    private FileServiceLocal fileService;
    @EJB
    private StatisticsBeanLocal statBean;
    
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
            
            request.setAttribute("name", file.getName());
            request.setAttribute("ownername", ruc.get(file.getUserId()).getFirstName() + " " + ruc.get(file.getUserId()).getLastName());
            request.setAttribute("owneremail", ruc.get(file.getUserId()).getEmail());
            if (!file.getRecentChanges_timestamps().isEmpty()){
                request.setAttribute("creationdate", new Date(file.getRecentChanges_timestamps().get(0)).toLocaleString());
                request.setAttribute("lastmodified", new Date(file.getRecentChanges_timestamps().get(file.getRecentChanges_timestamps().size()-1)).toLocaleString());
            } else {
                request.setAttribute("creationdate", "n/a");
                request.setAttribute("lastmodified", "n/a");
            }
            request.setAttribute("contentsize", file.getContent().length());
            
            request.setAttribute("collabJSON", statBean.getCollaboratorMap(fid));
            request.setAttribute("changesJSON", statBean.getChangesTime(fid, 100));
            request.setAttribute("file", file);
            request.getRequestDispatcher("statistics.jsp").forward(request, response);
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
        String fidString = request.getParameter("id");
        if (fidString != null) {
            Long fid = Long.parseLong(fidString);
            User user = ServletUtil.currentUser(request);
            File file = fileService.show(fid, user);
            request.setAttribute("file", file);
            request.getRequestDispatcher("files.jsp").forward(request, response);
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
