/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import org.json.JSONArray;
import ua.group06.business.ChatFacadeLocal;
import ua.group06.persistence.Chat;

/**
 *
 * @author Michael
 */
@Stateless
public class ChatService implements ChatServiceLocal {
    @EJB
    private ChatFacadeLocal chatFacade;

    private RestUserClient ruc = new RestUserClient();
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Chat create(Chat chat) {
        try {
            chatFacade.create(chat);
        } catch (EntityExistsException e) {
            // TODO: Should we change the return type or should we throw exception?
            chat = null;
        }
        return chat;
    }

    @Override
    public Chat getFile(Long fid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chat> findForFile(Long fid) {
        return chatFacade.findForFile(fid);
    }

    @Override
    public List<Chat> findRecentForFile(Long fid, Long time) {
        return chatFacade.findRecentForFile(fid, time);
    }
    
    @Override
    public JSONArray toJson(Chat c) {
        JSONArray ret = new JSONArray();
        ret.put(c.getContent());
        String sender = ruc.get(c.getSenderid()).getEmail();
        if (sender != null && sender.isEmpty()){
            sender = ruc.get(c.getSenderid()).getFirstName().concat(" ").concat(ruc.get(c.getSenderid()).getLastName());
            ret.put(sender);
        }
        else 
            ret.put("Unknown");
        ret.put(c.getTime());
        return ret;
    }
}
