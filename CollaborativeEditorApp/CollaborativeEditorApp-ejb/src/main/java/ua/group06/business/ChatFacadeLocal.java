/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.business;

import java.util.List;
import javax.ejb.Local;
import ua.group06.persistence.Chat;

/**
 *
 * @author Michael
 */
@Local
public interface ChatFacadeLocal {
    List<Chat> findForFile(Long fid);
    
    void create(Chat chat);
    
    List<Chat> findRecentForFile(Long fid, Long time);
    
    Chat find(Object id);
}
