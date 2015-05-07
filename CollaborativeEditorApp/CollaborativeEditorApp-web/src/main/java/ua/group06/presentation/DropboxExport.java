/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.presentation;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWriteMode;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.group06.logic.FileServiceLocal;
import ua.group06.persistence.File;
import ua.group06.util.ServletUtil;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yves Maris
 */
@WebServlet(name = "ExportFile", urlPatterns = {"/exportFile"})
public class DropboxExport extends HttpServlet {
    //DropboxExportService export= new DropboxExportService();
    @EJB
    FileServiceLocal fileService;
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
        HttpSession session = request.getSession();
        long fid = Long.parseLong((String) session.getAttribute("export_id"),10);
        DbxClient client = (DbxClient) session.getAttribute("dbxClient");
        File inputFile = fileService.getFile(fid);
        String content = inputFile.getContent();
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        try {
            DbxEntry.File uploadedFile = client.uploadFile("/"+inputFile.getName()+".txt",
            DbxWriteMode.add(), content.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } catch (DbxException ex) {
            Logger.getLogger(DropboxExport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            inputStream.close();
        }
       response.sendRedirect("http://127.0.0.1:8080/CollaborativeEditorApp-web/files");
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
