/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.entities;

import java.io.Serializable;

/**
 *
 * @author matson
 *
 * This class is used when transferring update info with json from Javascript.
 */
public class FileUpdate implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long fileId;
    private String token;
    private String content;
    private String email;
    private String changes;
    private String browserID;

    public FileUpdate() {
    }

    public FileUpdate(Long id, String token, String email, String content, String changes) {
        this.fileId = id;
        this.token = token;
        this.content = content;
        this.email = email;
        this.changes = changes;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBrowserID() {
        return browserID;
    }

    public void setBrowserID(String browserID) {
        this.browserID = browserID;
    }

}
