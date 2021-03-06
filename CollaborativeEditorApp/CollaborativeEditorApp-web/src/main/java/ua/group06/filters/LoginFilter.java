/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author matson
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String requri = request.getRequestURI().substring(request.getContextPath().length() + 1);
        
        if (publicURI(requri)) {
            chain.doFilter(req, res); // Allow public uri
        }
        else if (notLogged(session)) {
            response.sendRedirect(request.getContextPath() + "/login"); // No logged-in user found, so redirect to login page.
        }
        else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }
    
    private boolean publicURI(String uri) {
        return uri.startsWith("login")
                || uri.startsWith("resource")
                || uri.startsWith("styles")
                || uri.startsWith("scripts");
    }
    
    private boolean notLogged(HttpSession session) {
        return session == null || session.getAttribute("user") == null;
    }
    
}
