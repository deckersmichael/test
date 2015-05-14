/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ua.group06.entities.User;
import ua.group06.logic.MergeBeanLocal.Change;
import ua.group06.persistence.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Michael
 */
@Stateless
public class StatisticsBean implements StatisticsBeanLocal {

    @EJB
    private FileServiceLocal fsl;
    
    @Override
    public String getCollaboratorMap(Long fid){    
        ArrayList<Long> l = new ArrayList<>();
        ArrayList<Integer> i = new ArrayList<>();
        
        File file = fsl.getFile(fid);
        ArrayList<Change> list = file.getAllChanges();
        
        RestUserClient ruc = new RestUserClient();
        
        for (Change c : list){
            if (l.contains(c.uid)){
                i.set(l.indexOf(c.uid), i.get(l.indexOf(c.uid)) + 1);
            } else {
                l.add(c.uid);
                i.add(1);
            }
        }
        
        JSONArray array = new JSONArray();
        for (int j = 0; j < l.size(); j++){
            try {
                JSONObject object = new JSONObject();
                User u = ruc.get(l.get(j));
                if (u.getEmail() == null || u.getEmail().isEmpty())
                    object.put("category", u.getFirstName().concat(" ").concat(u.getLastName()));
                else 
                    object.put("category", u.getEmail());
                object.put("column-1", i.get(j));
                array.put(object);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        
        return array.toString();
    }

    @Override
    public String getChangesTime(Long fid, int amount) {
        File file = fsl.getFile(fid);
        JSONArray array = new JSONArray();
        if (file.getRecentChanges_timestamps().size() < 2){
            return array.toString();
        }
        
        ArrayList<Long> timestamps = file.getRecentChanges_timestamps();
        ArrayList<Integer> numberchanges = new ArrayList<>();
        for (ArrayList<Change> list : file.getRecentChanges_changesList())
            numberchanges.add(list.size());
        
        Long firsttime = timestamps.get(0);
        Long lasttime = timestamps.get(timestamps.size()-1);
        Long slot = (lasttime - firsttime) / amount;
        
        for (int i = 0; i < amount; i++){
            try {
                JSONObject object = new JSONObject();
                int count = 0;
                Long begintime = firsttime + (i * slot);
                Long endtime = firsttime + ((i+1) * slot);
                for (int j = 0; j < timestamps.size(); j++){
                    if (i == amount-1){
                        if (timestamps.get(j) >= begintime)
                            count += numberchanges.get(j);
                    } else {
                        if (timestamps.get(j) >= begintime && timestamps.get(j) < endtime)
                            count += numberchanges.get(j);
                    }
                }
                Date begindate = new Date(begintime);
                String period = begindate.toLocaleString();
                
                object.put("category", period);
                object.put("column-1", count);
                
                array.put(object);
            } catch (JSONException ex) {
                Logger.getLogger(StatisticsBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return array.toString();
    }
}
