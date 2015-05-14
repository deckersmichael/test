/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.json.JSONArray;
import ua.group06.entities.User;
import ua.group06.logic.RestUserClient;

/**
 *
 * @author Michael
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name="Chat.findForFile",
            query="SELECT c FROM Chat c WHERE c.fileid = :fid"),
    @NamedQuery(
            name="Chat.findRecentForFile",
            query="SELECT c FROM Chat c WHERE c.fileid = :fid AND c.time > :time")
})
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long senderid;
    @NotNull
    private Long fileid;
    @NotNull
    private Long time;
    @NotNull
    @Lob
    private String content;
    
    @EJB
    private transient RestUserClient ruc;

    public Chat() {
    }
    
    public Chat(Long senderid, Long fileid, Long time, String content) {
        this.senderid = senderid;
        this.fileid = fileid;
        this.time = time;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderid() {
        return senderid;
    }

    public void setSenderid(Long senderid) {
        this.senderid = senderid;
    }

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
