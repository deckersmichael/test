/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author michaeldeckers
 */
@Entity
public class PersistString implements Serializable{
    @Id
    private String content;
    
    public PersistString(){
        
    }
    public PersistString(String content){
        this.content = content;
    }
    
    public String getContent(){
        return this.content;
    }
    
    @Override
    public boolean equals(Object object){
        if (object instanceof PersistString){
            if (((PersistString)object).getContent().equals(this.getContent())){
                return true;
            }
        }
        return false;
    }
}
