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
    private String idAdress;
    private String city;
    private String location;
    private String district;

    public Adress(String idAdress, String city, String location, String district) {
        this.idAdress = idAdress;
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

    public String getIdAdress() {
        return idAdress;
    }
    
}
