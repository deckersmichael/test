/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;
import javax.ejb.Local;
import org.json.JSONArray;
import ua.group06.entities.User;

/**
 *
 * @author michaeldeckers
 */
@Local
public interface MergeBeanLocal {

    public String getUpdatedFile(Long fid, String token, String browserID, String email, String content, String dateTime);
    
    public class Request {
        private Long uid;
        private String content;
        private Long fid;
        private ArrayList<Change> changes;
        private volatile boolean finished = false;
        
        public Request(Long user, Long fid, String content){
            this.content = content;
            this.uid = user;
            this.fid = fid;
            this.changes = new ArrayList<>();
        }
        
        public boolean isFinished(){
            return finished;
        }
        
        public void setFinished(){
            finished = true;
        }
        
        public Request(Long user, Long fid, ArrayList<Change> changes){
            this.uid = user;
            this.fid = fid;
            this.changes = changes;
        }
        
        public String getContent(){
            return this.content;
        }
        
        public Long getFid(){
            return this.fid;
        }
        
        public void setChanges(ArrayList<Change> changes){
            this.changes = changes;
        }
        
        public void addChange(Change c){
            this.changes.add(c);
        }
        
        public ArrayList<Change> getChanges(){
            return changes;
        }
    }
    
    public abstract class Change implements Serializable{
        private static final long serialVersionUID = 1L;
        protected int startLine, startColumn;
        protected int endLine, endColumn;
        protected Long uid;
        
        public long getUser(){
            return uid;
        }

        public int getStartLine() {
            return startLine;
        }

        public int getStartColumn() {
            return startColumn;
        }

        public int getEndLine() {
            return endLine;
        }

        public int getEndColumn() {
            return endColumn;
        }
        
        public abstract JSONArray toJson();
        
    }
    
    public class Addition extends Change {
        private String changes;
        public Addition(int startLine, int startColumn, int endLine, int endColumn, String changes, Long uid){
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
            this.changes = changes;
            this.uid = uid;
        }

        public String getChanges() {
            return changes;
        }

        @Override
        public JSONArray toJson() {
            JSONArray ret = new JSONArray();
            ret.put("addition");
            ret.put(changes);
            JSONArray start = new JSONArray();
            start.put(startLine);
            start.put(startColumn);
            JSONArray end = new JSONArray();
            end.put(endLine);
            end.put(endColumn);
            ret.put(start);
            ret.put(end);
            return ret;
        }
    }
    
    public class Deletion extends Change {
        public Deletion(int startLine, int startColumn, int endLine, int endColumn, Long uid){
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
            this.uid = uid;
        }

        @Override
        public JSONArray toJson() {
            JSONArray ret = new JSONArray();
            ret.put("deletion");
            JSONArray start = new JSONArray();
            start.put(startLine);
            start.put(startColumn);
            JSONArray end = new JSONArray();
            end.put(endLine);
            end.put(endColumn);
            ret.put(start);
            ret.put(end);
            return ret;
        }
    }
    
    public class Clear extends Change {

        @Override
        public JSONArray toJson() {
            JSONArray ret = new JSONArray();
            ret.put("clear");
            return ret;
        }
        
    }
    
    public String addRequest(Long uid, Long fid, String token, String browserID, ArrayList<Change> changes);
    
    public void initiateMerge();
    
    public void performMerge();

}
