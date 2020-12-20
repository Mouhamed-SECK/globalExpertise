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
public class Address {
    private int addressId;
    private String city;
    private String location;
    private String district;
    private User addressCustomer;

    public Address(int addressId, String city, String location, String district) {
        this.addressId = addressId;
        this.city = city;
        this.location = location;
        this.district = district;
    }

    public Address(String city, String location, String district) {
        this.city = city;
        this.location = location;
        this.district = district;
    }

    public Address(String city, String location, String district, User addressCustomer) {
        this.city = city;
        this.location = location;
        this.district = district;
        this.addressCustomer = addressCustomer;
    }
    
    

    public Address() {
    }
    

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getAddressId() {
        return addressId;
    }
    
    public int setAddressId(int addressId) {
       return addressId;
    }

    public User getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(User addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    @Override
    public String toString() {
        return "Address{" + "addressId=" + addressId + ", city=" + city + ", location=" + location + ", district=" + district + ", addressCustomer=" + addressCustomer + '}';
    }

    
}
