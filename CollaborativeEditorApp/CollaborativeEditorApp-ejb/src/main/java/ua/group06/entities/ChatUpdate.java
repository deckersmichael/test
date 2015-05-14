/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.entities;

import java.io.Serializable;

/**
 *
 * @author Michael
 */
public class ChatUpdate implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long fileid;
    private String senderid;
    private String content;
    private Long time;
    private String option;

    public ChatUpdate() {
    }

    public ChatUpdate(Long fileid, String senderid, String content, Long time, String option) {
        this.fileid = fileid;
        this.senderid = senderid;
        this.content = content;
        this.time = time;
        this.option = option;
    }

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
    
    
    
}
