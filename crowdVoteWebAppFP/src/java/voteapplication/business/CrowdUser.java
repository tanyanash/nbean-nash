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
public class CrowdUser implements Serializable {
    
    private String email;
    private String firstName;
    private String lastName;

    public CrowdUser() {
        
        email = "";
        firstName = "";
        lastName = "";
    }
    
    public CrowdUser(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
     
    
}
