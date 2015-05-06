/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

enum Platform{ LDAB, Twitter};

/**
 *
 * @author Yves Maris
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name="ExternalUser.findByUsername",
            query="SELECT u FROM ExternalUser u WHERE u.username LIKE :username")
})
public class ExternalUser extends AbstractUser {
    @NotNull
    //@Column(name="USERNAME")
    private String username;
    /*@Column(name="PLATFORM")
    @Enumerated(EnumType.ORDINAL)
    private Platform platform;*/
    
    /*@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)*+[a-z0-9]"
            + "(?:[a-z0-9-]*[a-z0-9])?",
            message = "{invalid.email}")*/
    //@Column(name="EMAIL")
    private String email;
    
    public ExternalUser(){}
    public ExternalUser(String username, String fname, String lname, String email) {
        super(fname, lname/*, email*/);
        this.username=username;
        this.email=email;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
