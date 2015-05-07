/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.presentation;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxSessionStore;
import com.dropbox.core.DbxStandardSessionStore;
import com.dropbox.core.DbxWebAuth;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Yves Maris
 */
@WebServlet(name = "LoginDropox", urlPatterns = {"/loginDropbox"})
public class DropboxAuthentication extends HttpServlet {
    private DbxWebAuth webAuth;
    private DbxRequestConfig config;
    private final String APP_KEY = "dwlj8aa85r8swy0";
    private final String APP_SECRET = "7fbbp5dtqakitbs";
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
        // Load the request token we saved in part 1.
        //String token = (String)session.getAttribute("dropbox-auth-csrf-token");
        //System.err.println(token);

        DbxAuthFinish authFinish;
        try {
            authFinish = webAuth.finish(request.getParameterMap());
            DbxClient client = new DbxClient(config, authFinish.accessToken);
            session.setAttribute("dbxClient", client);
        } catch (DbxException ex) {
            Logger.getLogger(DropboxAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxWebAuth.BadRequestException ex) {
            Logger.getLogger(DropboxAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxWebAuth.BadStateException ex) {
            Logger.getLogger(DropboxAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxWebAuth.CsrfException ex) {
            Logger.getLogger(DropboxAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxWebAuth.NotApprovedException ex) {
            Logger.getLogger(DropboxAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbxWebAuth.ProviderException ex) {
            Logger.getLogger(DropboxAuthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("http://127.0.0.1:8080/CollaborativeEditorApp-web/exportFile");
        // Now use the access token to make Dropbox API calls.
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
        session.setAttribute("export_id", request.getParameter("export"));
        if(session.getAttribute("dbxClient")!=null){
            response.sendRedirect("http://127.0.0.1:8080/CollaborativeEditorApp-web/exportFile");
        }
        else{
            DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
            
            String sessionKey = "dropbox-auth-csrf-token";
            DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(session, sessionKey);

            config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
            webAuth = new DbxWebAuth(config, appInfo, "http://127.0.0.1:8080/CollaborativeEditorApp-web/loginDropbox", csrfTokenStore);
            response.sendRedirect(webAuth.start());
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
