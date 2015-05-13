/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import ua.group06.logic.MergeBeanLocal.Change;

/**
 *
 * @author matson
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name="File.findAllForUser",
            query="SELECT f FROM File f WHERE f.ownerId = :uid"),
    @NamedQuery(
            name="File.findAllForCollabs",
            query="SELECT f FROM File f WHERE :uemail MEMBER OF f.collabIds")
})
public class File implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long ownerId;
    @OneToMany
    private Collection<PersistString> collabIds;
    private List<String> spectatorIds;
    @NotNull
    private String name;
    @NotNull
    private String content;
    private ArrayList<String> RecentChanges_tokens;

    public ArrayList<String> getCollabIds() {
        ArrayList<String> ret = new ArrayList<String>();
        for (PersistString ps : this.collabIds){
            ret.add(ps.getContent());
        }
        return ret;
    }

    public void _persistency_set_collabIds() {
        this.collabIds = collabIds;
    }
    
    public File () {}
    
    public File(Long userId, String name, String content) {
        this.ownerId = userId;
        this.name = name;
        this.content = content;
        this.collabIds = new ArrayList<PersistString>();
        this.spectatorIds = new ArrayList<>();
        this.RecentChanges_changesList = new ArrayList<>();
        this.RecentChanges_users = new ArrayList<>();
        this.RecentChanges_tokens = new ArrayList<>();
        this.RecentChanges_timestamps = new ArrayList<>();
        this.RecentChanges_userTimes = new HashMap<>();
    }
    
    public void addCollaborator(String email) {
        this.collabIds.add(new PersistString(email));
    }
    
    public boolean removeCollaborator(String email) {
        return collabIds.remove(email);
    }
    
    public Collection<String> getCollaborators() {
        return this.getCollabIds();
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
    
        
    private ArrayList<ArrayList<Change>> RecentChanges_changesList;
    private ArrayList<Long> RecentChanges_users;
    private ArrayList<Long> RecentChanges_timestamps;
    private Map<String, Long> RecentChanges_userTimes;


    /**
     * GET CHANGES FIRST!
     * @param changes
     * @param user 
     */
    public void addChanges(ArrayList<Change> changes, String token, Long user){
        this.RecentChanges_changesList.add(changes);
        this.RecentChanges_users.add(user);
        this.RecentChanges_tokens.add(token);
        this.RecentChanges_timestamps.add(System.currentTimeMillis());
    }

    public ArrayList<ArrayList<Change>> getChanges(Long user, String token){
        if (!RecentChanges_userTimes.containsKey(token)){
            RecentChanges_userTimes.put(token, 0L);
        }
        ArrayList<ArrayList<Change>> ret = new ArrayList<>();
        Long sessiontime = RecentChanges_userTimes.get(token);
        for (int i = 0; i < RecentChanges_changesList.size(); i++){
            if (RecentChanges_timestamps.get(i) > sessiontime){
                if (!RecentChanges_tokens.get(i).equals(token)){
                    ret.add(RecentChanges_changesList.get(i));
                }
            }
        }
        this.RecentChanges_userTimes.put(token, System.currentTimeMillis());
        //cleanlist();      todo
        return ret;
    }
    
    public ArrayList<ArrayList<Change>> getVersion(Long time){
        ArrayList<ArrayList<Change>> ret = new ArrayList<>();
        int cutoff = 0;
        for (int i = 0; i < RecentChanges_timestamps.size(); i++){
            System.err.println((new Date(time)).toString() + " --- " + (new Date(RecentChanges_timestamps.get(i)).toString()));
            if (RecentChanges_timestamps.get(i) > time){
                cutoff = i;
            }
        }
        
        for (int i = 0; i < cutoff; i++){
            ret.add(RecentChanges_changesList.get(i));
        }
        
        return ret;
    }
    
    public ArrayList<Integer> getCreationTime(){
        Date date;
        if (RecentChanges_timestamps.isEmpty())
            date = new Date(System.currentTimeMillis());
        else
            date = new Date(RecentChanges_timestamps.get(0));
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(date.getYear());
        ret.add(date.getMonth());
        ret.add(date.getDay());
        ret.add(date.getHours());
        ret.add(date.getMinutes());
        ret.add(date.getSeconds());
        return ret;
    }

    /**`
     * TODO
     */
    private void cleanlist(){
        Long minimum = Long.MAX_VALUE;
        for (int i = 0; i < RecentChanges_userTimes.size(); i++){
            if (RecentChanges_userTimes.get(i) < minimum) {
                minimum = RecentChanges_userTimes.get(i);
            }
        }

        ArrayList<ArrayList<Change>> changesListCopy;
        ArrayList<Long> usersCopy;
        ArrayList<Long> timestampsCopy;

        //TODO

    }
        
        
        
    
}
