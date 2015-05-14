/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ua.group06.business.FileFacadeLocal;
import ua.group06.persistence.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONString;
import ua.group06.business.SessionFacadeLocal;

/**
 *
 * @author michaeldeckers
 */
@Stateless
public class MergeBean implements MergeBeanLocal {

    private LinkedBlockingQueue<Request> requestQueue;
    private Boolean mergingEnabled;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private FileFacadeLocal fileFacade;
    @EJB 
    private SessionFacadeLocal sessionFacade;

    public MergeBean() {
        this.requestQueue = new LinkedBlockingQueue<>();
        this.mergingEnabled = false;
    }
    
    @Override
    public String addRequest(Long uid, Long fid, String token, String browserID, ArrayList<Change> changes) {
        Request req = new Request(uid, fid, changes);
        requestQueue.add(req);
        
        if (!mergingEnabled){
            initiateMerge();
        }
        /*while (!req.isFinished()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {}
        }*/
        
        ArrayList<ArrayList<Change>> recentChanges = fileFacade.find(fid).getChanges(uid, browserID);
        
        if (!changes.isEmpty())
            fileFacade.find(fid).addChanges(changes, browserID, uid);
        
        JSONArray jsonarray = new JSONArray();
        
        for (ArrayList<Change> ac : recentChanges){
            for (Change c : ac){
                jsonarray.put(c.toJson());
            }
        }
        return jsonarray.toString();
    }
    
    

    @Override
    public void initiateMerge() {
        mergingEnabled = true;
        
        class MergeRunnable implements Runnable{  
            public void run(){  
                while (true){
                    if (!requestQueue.isEmpty()){
                        performMerge();
                    } else {
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException ex) {}
                    }
                }
            }  
        }
  
        MergeRunnable m1=new MergeRunnable();  
        Thread t1 =new Thread(m1);  
        t1.start();       
    }

    @Override
    public void performMerge() {
        Request current = requestQueue.poll();
        if (current != null){
            File file = fileFacade.find(current.getFid());
            String content = file.getContent();    
            ArrayList<String> lines = new ArrayList<>();
            String bufcont = content;
            while (bufcont.contains("\n") && bufcont.length() > 0){
                int index = bufcont.indexOf("\n");
                if (index == -1)
                    break;
                if (index == 0)
                    lines.add("");
                else {
                    lines.add(bufcont.substring(0, index));
                }
                bufcont = bufcont.substring(index+1);
            }
            if (bufcont.length() != 0)
                lines.add(bufcont);
            if (lines.isEmpty() || (content.lastIndexOf("\n") != -1) && (content.lastIndexOf("\n") == content.length()-1))
                lines.add("");
            
            for (Change c : current.getChanges()){
                if (c instanceof Clear){
                    lines = new ArrayList<>();
                }
                if (c instanceof Addition){
                    Addition a = (Addition) c;
                    String newline = lines.get(a.getStartLine()).substring(0, a.getStartColumn())
                            .concat(a.getChanges())
                            .concat(lines.get(a.getStartLine()).substring(a.getStartColumn()));
                    if (newline.contains("\n")){
                        String newLines[] = newline.split("\n");
                        boolean addnewLine = (newline.lastIndexOf("\n") ==  newline.length()-1);
                        for (int i = newLines.length; i > 0; i--){
                            if (i == 1)
                                lines.set(a.getStartLine(), newLines[i-1]);
                            else
                                lines.add(a.getEndLine(), newLines[i-1]);
                        }   
                        if (addnewLine){
                            lines.add(a.getEndLine(), "");
                        }
                    } else {
                        lines.set(a.getStartLine(), newline);
                    }
                } else {
                    Deletion d = (Deletion) c;
                    String newline;
                    int backmostline = min(d.getEndLine(), d.getStartLine());
                    int frontmostline = max(d.getEndLine(), d.getStartLine());
                    int linediff = frontmostline - backmostline;

                    newline = lines.get(d.getStartLine()).substring(0, d.getStartColumn())
                            .concat(lines.get(d.getEndLine()).substring(d.getEndColumn()));

                    for (int i = 0; i < linediff; i++){
                        lines.remove(d.getStartLine());
                    }

                    lines.set(d.getStartLine(), newline);
                }
            }
            String rebuilt = "";
            for (int i = 0; i < lines.size(); i++){
                rebuilt = rebuilt.concat(lines.get(i));
                if (i != lines.size()-1){
                    rebuilt = rebuilt.concat("\n");
                }
            }

            file.setContent(rebuilt);

            fileFacade.edit(file);
            current.setFinished();
        }
    }

    @Override
    public String getUpdatedFile(Long fid, String token, String browserID, String changes, String dateTime) {
        Long uid = sessionFacade.findByToken(token).getId();
        if (dateTime.equals("do_revert"))
            return revertVersion(fid, browserID, uid);
        else if (dateTime.length() > 0)
            return getVersion(fid, parseDate(dateTime).getTime(), browserID);
        else
            return addRequest(uid, fid, token, browserID, parseChanges(changes, uid));
    }
    
    private String getVersion(Long fid, Long time, String browserID) {
        ArrayList<ArrayList<Change>> recentChanges = fileFacade.find(fid).getVersion(time, browserID);
        
        JSONArray jsonarray = new JSONArray();
        
        for (ArrayList<Change> ac : recentChanges){
            for (Change c : ac){
                jsonarray.put(c.toJson());
            }
        }
        return jsonarray.toString();
    }
    
    private String revertVersion(Long fid, String browserID, Long uid){
        File file = fileFacade.find(fid);
        file.revertVersion(browserID, uid);
        
        ArrayList<ArrayList<Change>> recentChanges = file.getChanges(uid, browserID);
        ArrayList<Change> combined = new ArrayList<>();
        
        for (ArrayList<Change> c : recentChanges){
            combined.addAll(c);
        }
        
        Request req = new Request(uid, fid, combined);
        requestQueue.add(req);
        
        if (!mergingEnabled){
            initiateMerge();
        }
        
        JSONArray jsonarray = new JSONArray();
        
        for (ArrayList<Change> ac : recentChanges){
            for (Change c : ac){
                jsonarray.put(c.toJson());
            }
        }
        return jsonarray.toString();
    }
    
    private Date parseDate(String dateTime){
        Date date = new Date();
        try {
            JSONArray datetime = new JSONArray(dateTime);
            date.setMonth(datetime.getInt(0));
            date.setDate(datetime.getInt(1));
            date.setYear(datetime.getInt(2));
            date.setHours(datetime.getInt(3));
            date.setMinutes(datetime.getInt(4));
            date.setSeconds(0);
        } catch (JSONException ex) {}
        return date;
    }
    
    private ArrayList<Change> parseChanges(String changes, Long uid){
        ArrayList<Change> changesList = new ArrayList<>();
        if (changes.length() < 2)
            return changesList;
        try{
            JSONArray jchanges = new JSONArray(changes);
            for (int i = 0; i < jchanges.length(); i++){
                JSONArray jchange = new JSONArray(jchanges.get(i).toString());
                if (jchange.get(0).toString().equals("insert")){
                    String data = jchange.get(1).toString();
                    JSONArray start = new JSONArray(jchange.get(2).toString());
                    JSONArray end = new JSONArray(jchange.get(3).toString());
                    changesList.add(new Addition(start.getInt(1), start.getInt(0), end.getInt(1), end.getInt(0), data, uid));
                } else {
                    JSONArray start = new JSONArray(jchange.get(1).toString());
                    JSONArray end = new JSONArray(jchange.get(2).toString());
                    changesList.add(new Deletion(start.getInt(1), start.getInt(0), end.getInt(1), end.getInt(0), uid));
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return changesList;
    }
    
    
}
