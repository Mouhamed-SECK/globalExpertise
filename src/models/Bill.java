/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import utilities.Utilities;

/**
 *
 * @author Mouhamed SECK
 */
public class Bill {
    private int bilId;
    private String billNumber;
    private String type;
    private String status;
    private Double totalAmount;
    private LocalDate date;

    public Bill(int bilId, String type, String status, Double totalAmount, LocalDate date) {
        this.bilId = bilId;
        this.type = type;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Bill(String type, String status, Double totalAmount, LocalDate date) {
        this.type = type;
        this.billNumber = Utilities.generateRandomStringAlphaNumericString(10);
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = LocalDate.now();
    }
    
    Order order;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getBilId() {
        return bilId;
    }

    @Override
    public String toString() {
        return "Bill{" + "bilId=" + bilId + ", billNumber=" + billNumber + ", type=" + type + ", status=" + status + ", totalAmount=" + totalAmount + ", date=" + date + ", order=" + order + '}';
    }
    
    
}
