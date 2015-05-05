/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matson
 */
@Entity
public class User extends AbstractUser implements Serializable {
    @NotNull
    // TODO: find an annotation to exclude this from JSON.
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User(){}
    
    public User(String fname, String lname, String email, String password) {
        super(fname, lname, email);
        this.password = password;
    }  
}
