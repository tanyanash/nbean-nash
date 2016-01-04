/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voteapplication.business;

import java.io.Serializable;

/**
 *
 * @author tanya
 */
public class CrowdEvent implements Serializable {
    
    private String id;
    private String host;
    private String date;
    private String description;

    public CrowdEvent() {
        
    }
    
    public CrowdEvent(String id, String host, String date, String description) {
        
        this.id = id;
        this.host = host;
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CrowdEvent{" + "id=" + id + ", host=" + host + ", date=" + date + '}';
    }


      
}
