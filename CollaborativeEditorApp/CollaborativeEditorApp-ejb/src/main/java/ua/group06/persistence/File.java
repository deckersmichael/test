/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matson
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name="File.findAllForUser",
            query="SELECT f FROM File f WHERE f.ownerId = :uid")
})
public class File implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long ownerId;
    @NotNull
    private List<String> collabIds;
    @NotNull
    private List<String> spectatorIds;
    @NotNull
    private String name;
    @NotNull
    private String content;

    public File () {}
    
    public File(Long userId, String name, String content) {
        this.ownerId = userId;
        this.name = name;
        this.content = content;
        this.collabIds = new ArrayList<>();
        this.spectatorIds = new ArrayList<>();
    }
    
    public void addCollaborator(String email) {
        this.collabIds.add(email);
    }
    
    public boolean removeCollaborator(String email) {
        return collabIds.remove(email);
    }
    
    public List<String> getCollaborators() {
        return this.collabIds;
    }
    
    public void addSpectator(String id) {
        this.spectatorIds.add(id);
    }
    
    public boolean removeSpectator(String email) {
        return spectatorIds.remove(email);
    }
        
    public List<String> getSpectators() {
        return this.spectatorIds;
    }
    
    public Long getUserId() {
        return ownerId;
    }

    public void setUserId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof File)) {
            return false;
        }
        File other = (File) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.group06.persistence.File[ id=" + id + " ]";
    }
    
}
