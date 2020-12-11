/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mouhamed SECK
 */
public class Customer extends User {
    
    private String phoneNumber;
    private String cni;
    private List<Adress> addresses;

 
    public Customer(String phoneNumber, String cni, int id, String name, String firstname, String email) {
        super(id, name, firstname, email);
        this.phoneNumber = phoneNumber;
        this.cni = cni;
    }

    public Customer(String phoneNumber, String cni, String name, String firstname, String email) {
        super(name, firstname, email);
        this.phoneNumber = phoneNumber;
        this.cni = cni;
        this.addresses = new ArrayList<>();
    }
    
    
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCni() {
        return cni;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    @Override
    public String toString() {
        return super.toString() +  "Customer{" + "phoneNumber=" + phoneNumber + ", cni=" + cni + ", addresses=" + addresses + '}';
    }
    
    
    
      
}
