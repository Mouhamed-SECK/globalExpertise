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
    private int billId;
    private String billNumber;
    private String type;
    private String status;
    private Double totalAmount;
    private LocalDate date;
    
    private Order order;
    
    public static enum BillType {
        PROFORMA, 
        FINAL
    }
    public static enum BillStatus {
        PAID,
        UNPAID   
    }
    
    public Bill(int billId, String billNumber, String type, String status, Double totalAmount, LocalDate date) {
        this.billId = billId;
        this.billNumber = billNumber;
        this.type = type;
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public Bill(String type, String status, Double totalAmount, LocalDate date) {
        this.type = type;
        this.billNumber = Utilities.generateRandomStringAlphaNumericString(10);
        this.status = status;
        this.totalAmount = totalAmount;
        this.date = LocalDate.now();
    }
    
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
        return billId;
    }
    

    @Override
    public String toString() {
        return "billId=" + "Bill{" + billId + ", billNumber=" + billNumber + ", type=" + type + ", status=" + status + ", totalAmount=" + totalAmount + ", date=" + date + ", order=" + order + '}';
    }
    
    
}
