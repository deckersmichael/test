/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import javax.persistence.Entity;

/**
 *
 * @author Yves Maris
 */
@Entity
public class ExternalUser extends AbstractUser {
    public ExternalUser(){}
    public ExternalUser(String fname, String lname, String email) {
        super(fname, lname, email);
    }
}
