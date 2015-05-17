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
import java.util.Map;
import javax.ejb.EJB;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import ua.group06.entities.User;
import ua.group06.logic.MergeBeanLocal.Change;
import ua.group06.logic.MergeBeanLocal.Clear;
import ua.group06.logic.RestUserClient;

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
            query="SELECT f FROM File f WHERE :uid MEMBER OF f.collabIds")
})
public class File implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PersistLong ownerId;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<PersistLong> collabIds;
    
    //private Collection<PersistLong> spectatorIds;
    @NotNull
    private String name;
    @Lob
    private String content;
    
    private ArrayList<String> RecentChanges_tokens;

    public ArrayList<PersistLong> getCollabIds() {
        return new ArrayList(this.collabIds);
    }
    
    public File () {}
    
    public File(Long userId, String name, String content) {
        this.ownerId = new PersistLong(userId);
        this.name = name;
        this.content = content;
        //this.spectatorIds = new ArrayList<>();
        this.RecentChanges_changesList = new ArrayList<>();
        this.RecentChanges_users = new ArrayList<>();
        this.RecentChanges_tokens = new ArrayList<>();
        this.RecentChanges_timestamps = new ArrayList<>();
        this.RecentChanges_userTimes = new HashMap<>();
        this.latestVersionGetTime = new HashMap<>();
    }
    
    public boolean addCollaborator(Long id) {
        this.collabIds.add(new PersistLong(id));
        return true;
    }
    
    public boolean removeCollaborator(Long id) {
        return (this.collabIds.remove(new PersistLong(id)));
    }
    
    public Collection<Long> getCollaborators() {
        Collection<Long> c = new ArrayList();
        for (PersistLong pl : this.collabIds)
            c.add(pl.getContent());
        return c;
    }
    
    /*public void addSpectator(Long id) {
        this.spectatorIds.add(new PersistLong(id));
    }*/
    
    /*public boolean removeSpectator(Long id) {
        return spectatorIds.remove(new PersistLong(id));
    }*/
        
    /*public ArrayList<Long> getSpectators() {
        ArrayList<Long> c = new ArrayList();
        for (PersistLong pl : this.spectatorIds)
            c.add(pl.getContent());
        return c;
    }*/
    
    public Long getUserId() {
        return ownerId.getContent();
    }

    public void setUserId(Long ownerId) {
        this.ownerId = new PersistLong(ownerId);
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
    private Map<String, Long> latestVersionGetTime;


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
    
    public ArrayList<Change> getAllChanges(){
        ArrayList<Change> ret = new ArrayList<>();
        for (ArrayList<Change> list : this.RecentChanges_changesList){
            for (Change c : list){
                if (!(c instanceof Clear)){
                    ret.add(c);
                }
            }
        }
        return ret;
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
    
    public ArrayList<ArrayList<Change>> getVersion(Long time, String token){
        ArrayList<ArrayList<Change>> ret = new ArrayList<>();
        int cutoff = -1;
        for (int i = 0; i < RecentChanges_timestamps.size(); i++){
            if (RecentChanges_timestamps.get(i) > time){
                cutoff = i;
                break;
            }
        }
        if (cutoff == -1)
            cutoff = RecentChanges_timestamps.size();
        
        for (int i = 0; i < cutoff; i++){
            ret.add(RecentChanges_changesList.get(i));
        }
        
        latestVersionGetTime.put(token, time);
        return ret;
    }

    public ArrayList<ArrayList<Change>> getRecentChanges_changesList() {
        return RecentChanges_changesList;
    }

    public ArrayList<Long> getRecentChanges_timestamps() {
        return RecentChanges_timestamps;
    }
    
    public void revertVersion(String token, Long user){
        int cutoff = -1;
        if (!latestVersionGetTime.containsKey(token))
            return;
        
        ArrayList<ArrayList<Change>> ret1 = new ArrayList<>();
        ArrayList<Long> ret2 = new ArrayList<>();
        ArrayList<String> ret3 = new ArrayList<>();
        ArrayList<Long> ret4 = new ArrayList<>();
        for (int i = 0; i < RecentChanges_timestamps.size(); i++){
            if (RecentChanges_timestamps.get(i) > latestVersionGetTime.get(token)){
                cutoff = i;
                break;
            }
        }
        if (cutoff == -1)
            cutoff = RecentChanges_timestamps.size();
        
        for (int i = 0; i < cutoff; i++){
            ret1.add(RecentChanges_changesList.get(i));
            ret2.add(RecentChanges_users.get(i));
            ret3.add(RecentChanges_tokens.get(i));
            ret4.add(RecentChanges_timestamps.get(i));
        }
        
        this.RecentChanges_changesList = ret1;
        this.RecentChanges_users = ret2;
        this.RecentChanges_tokens = ret3;
        this.RecentChanges_timestamps = ret4;
        
        ArrayList<Change> clear = new ArrayList<>();
        clear.add(new Clear());
     
        Long firstTime = RecentChanges_timestamps.get(0)-1;
        this.RecentChanges_changesList.add(0, clear);
        this.RecentChanges_users.add(0, user);
        this.RecentChanges_tokens.add(0, token);
        this.RecentChanges_timestamps.add(0, firstTime);
        
        
        
        
        /*for (int i = 0; i < RecentChanges_timestamps.size(); i++){
            if (RecentChanges_timestamps.get(i) > latestVersionGetTime.get(token)){
                cutoff = i+1;
                break;
            }
        }
        if (cutoff == -1)
            cutoff = 1;
        
        ArrayList<Change> clear = new ArrayList<>();
        clear.add(new Clear());
     
        Long firstTime = RecentChanges_timestamps.get(0)-1;
        this.RecentChanges_changesList.add(0, clear);
        this.RecentChanges_users.add(0, user);
        this.RecentChanges_tokens.add(0, token);
        this.RecentChanges_timestamps.add(0, firstTime);
        
        int size = RecentChanges_changesList.size();
        this.RecentChanges_changesList.subList(size - (cutoff - 1), size).clear();
        this.RecentChanges_users.subList(size - (cutoff - 1), size).clear();
        this.RecentChanges_tokens.subList(size - (cutoff - 1), size).clear();
        this.RecentChanges_timestamps.subList(size - (cutoff - 1), size).clear();*/
        
        RecentChanges_userTimes.clear();
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
