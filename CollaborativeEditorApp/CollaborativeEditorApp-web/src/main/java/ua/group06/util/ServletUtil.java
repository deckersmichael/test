/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.group06.entities.User;

/**
 *
 * @author matson
 */
public class ServletUtil {
    
    public static User currentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }
    
    public static Long currentUserId(HttpServletRequest request) {
        User user = currentUser(request);
        return user.getId();
    }
    
}
