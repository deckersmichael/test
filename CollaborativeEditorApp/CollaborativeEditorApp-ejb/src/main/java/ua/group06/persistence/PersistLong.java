/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.group06.persistence;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author michaeldeckers
 */
@Entity
public class PersistLong implements Serializable{
    @Id
    private Long content;
    
    public PersistLong(){
        
    }
    public PersistLong(Long content){
        this.content = content;
    }
    
    public Long getContent(){
        return this.content;
    }
    
    @Override
    public boolean equals(Object object){
        if (object instanceof PersistLong){
            if (((PersistLong)object).getContent().equals(this.getContent())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.content);
        return hash;
    }
}
