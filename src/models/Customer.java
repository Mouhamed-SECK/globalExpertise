/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mouhamed SECK
 */
public class Customer extends User {
    
    private String phoneNumber;
    private String cni;
    private ObservableList<Address> addresses;

    public ObservableList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ObservableList<Address> addresses) {
        this.addresses = addresses;
    }
    
    
   
    public Customer () {
        
    }
 
    public Customer(String phoneNumber, String cni, int id, String name, String firstname, String email) {
        super(id, name, firstname, email);
        this.phoneNumber = phoneNumber;
        this.cni = cni;
    }

    public Customer(String phoneNumber, String cni, String name, String firstname, String email) {
        super(name, firstname, email);
        this.phoneNumber = phoneNumber;
        this.cni = cni;
        this.addresses = FXCollections.observableArrayList();
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
    
    public void addAdress(Address a) {
        addresses.add(a);
    }

    
    @Override
    public String toString() {
        return "Customer{" +  super.toString() + "phoneNumber=" + phoneNumber + ", cni=" + cni + ", addresses="  + '}';
    }
    
    
    
      
}
