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
 */
public class FileUpdate implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long fileId;
    private Long userId;
    private String content;

    public FileUpdate() {
    }

    public FileUpdate(Long id, Long uid, String content) {
        this.fileId = id;
        this.userId = uid;
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}
