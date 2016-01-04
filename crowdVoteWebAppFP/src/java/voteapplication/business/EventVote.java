/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voteapplication.business;

/**
 *
 * @author tanya
 */
public class EventVote {
    
    private String id; //eventid
    private String crowduser; //userid

    public EventVote() {
    }

    public EventVote(String id, String crowduser) {
        this.id = id;
        this.crowduser = crowduser;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCrowduser() {
        return crowduser;
    }

    public void setCrowduser(String crowduser) {
        this.crowduser = crowduser;
    }
    
    
    
}
