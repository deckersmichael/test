/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import java.util.List;
import javax.ejb.Local;
import ua.group06.entities.User;
import ua.group06.persistence.File;

/**
 *
 * @author matson
 */
@Local
public interface FileServiceLocal {

    File create(File file);
    
    File getFile(Long fid);

    int fileCount();

    List<File> filesForUser(User user);

    File show(Long fid, User user);

    void update(Long id, User user, String content);

    void delete(Long fid, User user);

    String updateContent(Long fid, String token, String browserID, String content, String changes, String timeDate);
    
    boolean updateCollabs(Long fid, User user, boolean edit, Long newid, boolean add);
    
}
