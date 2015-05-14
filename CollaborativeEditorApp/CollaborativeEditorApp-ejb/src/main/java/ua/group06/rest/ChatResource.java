/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import ua.group06.entities.ChatUpdate;
import ua.group06.entities.FileUpdate;
import ua.group06.logic.ChatServiceLocal;
import ua.group06.logic.FileServiceLocal;
import ua.group06.logic.SessionServiceLocal;
import ua.group06.persistence.Chat;

/**
 *
 * @author Michael Deckers
 */
@Stateless
@Path("chats")
public class ChatResource extends RestResource {
    @EJB
    private ChatServiceLocal chatService;
    @EJB
    private SessionServiceLocal session;
    
    @POST
    @Path("update")
    // TODO: handle errors
    public Response update(ChatUpdate chat) {
        JSONArray ja = new JSONArray();
        if (chat.getOption().equals("findforfile")){
            List<Chat> ret = chatService.findForFile(chat.getFileid());
            for (Chat c : ret)
                ja.put(chatService.toJson(c));
        } else if (chat.getOption().equals("findrecentforfile")){
            List<Chat> ret = chatService.findRecentForFile(chat.getFileid(), chat.getTime());
            for (Chat c : ret)
                ja.put(chatService.toJson(c));
        } else if (chat.getOption().equals("send")){
            Chat message = new Chat(session.findByToken(chat.getSenderid()).getUserId(), chat.getFileid(), chat.getTime(), chat.getContent());
            chatService.create(message);
        }
        return Response.ok(ja.toString()).build();
    }

}
