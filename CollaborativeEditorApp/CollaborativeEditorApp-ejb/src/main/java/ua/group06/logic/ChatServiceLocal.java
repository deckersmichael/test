/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.List;
import javax.ejb.Local;
import org.json.JSONArray;
import ua.group06.persistence.Chat;

/**
 *
 * @author Michael
 */
@Local
public interface ChatServiceLocal {
    Chat create(Chat file);
    
    Chat getFile(Long fid);
    
    List<Chat> findForFile(Long fid);
    
    List<Chat> findRecentForFile(Long fid, Long time);
    
    JSONArray toJson(Chat c);
}
