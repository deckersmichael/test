/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author matson
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name="User.findByEmail",
            query="SELECT u FROM User u WHERE u.email LIKE :email")
})
public class User extends AbstractUser implements Serializable {
    @NotNull
    // TODO: find an annotation to exclude this from JSON.
    private String password;
    
    @NotNull
    /*@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)*+[a-z0-9]"
            + "(?:[a-z0-9-]*[a-z0-9])?",
            message = "{invalid.email}")*/
    private String email;
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User(){}
    
    public User(String fname, String lname, String email, String password) {
        super(fname, lname/*, email*/);
        this.email=email;
        this.password = password;
    }  
}
