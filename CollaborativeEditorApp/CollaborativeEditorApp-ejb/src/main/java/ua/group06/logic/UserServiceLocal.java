/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.logic;

import javax.ejb.Local;

/**
 *
 * @author matson
 */
@Local
public interface UserServiceLocal {

    boolean register(String firstName, String lastName, String email, String password);
    
}
