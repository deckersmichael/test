/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Michael
 */
@Local
public interface StatisticsBeanLocal {
    
    public String getCollaboratorMap(Long fid);
    
    public String getChangesTime(Long fid, int amount);
}
