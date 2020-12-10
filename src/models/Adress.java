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
public class Adress {
    private String addressId;
    private String city;
    private String location;
    private String district;

    public Adress(String addressId, String city, String location, String district) {
        this.addressId = addressId;
        this.city = city;
        this.location = location;
        this.district = district;
    }

    public Adress(String city, String location, String district) {
        this.city = city;
        this.location = location;
        this.district = district;
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

    public String getAddressId() {
        return addressId;
    }

    @Override
    public String toString() {
        return "Adress{" + "addressId=" + addressId + ", city=" + city + ", location=" + location + ", district=" + district + '}';
    }
    
    
    
}
