/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.ArrayList;
import java.util.Queue;
import javax.ejb.Local;
import ua.group06.entities.User;

/**
 *
 * @author michaeldeckers
 */
@Local
public interface MergeBeanLocal {

    public String getUpdatedFile(Long fid, String token, String email, String content);
    
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
    
    public abstract class Change {
        protected int startLine, startColumn;
        protected int endLine, endColumn;

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
        
        
    }
    
    public class Addition extends Change {
        private String changes;
        public Addition(int startLine, int startColumn, int endLine, int endColumn, String changes){
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
            this.changes = changes;
        }

        public String getChanges() {
            return changes;
        }
    }
    
    public class Deletion extends Change {
        public Deletion(int startLine, int startColumn, int endLine, int endColumn){
            this.startLine = startLine;
            this.startColumn = startColumn;
            this.endLine = endLine;
            this.endColumn = endColumn;
        }
    }
    
    
    public String addRequest(Long uid, Long fid, ArrayList<Change> changes);
    
    public void initiateMerge();
    
    public void performMerge();

}
