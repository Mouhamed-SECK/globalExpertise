/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import utilities.Utilities;

/**
 *
 * @author Mouhamed SECK
 */
public class Employes extends User {
    private String login;
    private String password;
    private String departement;
    private String role;
    private String matricule;

    public Employes() {
    }
    
    

    public Employes(String login, String password, String departement, String role, int id, String name, String firstname, String email, String matricule) {
        super(id, name, firstname, email);
        this.login = login;
        this.password = password;
        this.departement = departement;
        this.role = role;
        this.matricule = matricule;
    }

    public Employes(String login, String password, String departement, String role, String name, String firstname, String email) {
        super(name, firstname, email);
        this.login = login;
        this.password = password;
        this.departement = departement;
        this.role = role;
        this.matricule = Utilities.generateRandomStringAlphaNumericString(8);
    }

    public String getLogin() {
        return login;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return super.toString() + "Employes{" + "login=" + login + ", password=" + password + ", departement=" + departement + ", role=" + role + ", matricule=" + matricule + '}';
    }
    
    
    
    
    
}
