/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Mouhamed SECK
 */
public class User {
    
    private int userId;
    protected String name;
    protected String firstname;
    protected String email;

    public User() {
    }
    
    public User(int id, String name, String firstname, String email) {
        this.userId = id;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
    }
    
    public User(String name, String firstname, String email) {
        this.name = name;
        this.firstname = firstname;
        this.email = email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
