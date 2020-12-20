package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utilities.Utilities;

/**
 *
 * @author Mouhamed SECK
 */
public class Order {
    private int orderId;
    private String orderNumber;
    private Status status;
    private Address deleveryAdress;
    private LocalDate orderedDate;
    
    public static enum Status  {
        INPROGRESS, UNDELIVERED, WAITING, DELIVERED
    }

    private Customer customer;
    private List<Product> products;
    
    public Order(int orderId, String orderNumber, Status status, Address deleveryAdress, LocalDate orderedDate) {
        this.orderId = orderId;
        this.status = status;
        this.deleveryAdress = deleveryAdress;
        this.orderedDate = orderedDate;
    }

    public Order(String orderNumber, String status, Address deleveryAdress, LocalDate orderedDate, Customer customer) {
        this.orderNumber =  Utilities.generateRandomStringAlphaNumericString(10);
        this.status = Status.UNDELIVERED;
        this.deleveryAdress = deleveryAdress;
        this.orderedDate = LocalDate.now();
        this.customer = customer;
        this.products = new ArrayList(); 
        
    }

    public Order(Status status) {
        this.status = status;
    }
    
     
    public int getOrderId() {
        return orderId;
    }
    

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getDeleveryAdress() {
        return deleveryAdress;
    }

    public void setDeleveryAdress(Address deleveryAdress) {
        this.deleveryAdress = deleveryAdress;
    }

    public LocalDate getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDate orderedDate) {
        this.orderedDate = orderedDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderNumber=" + orderNumber + ", status=" + status + ", deleveryAdress=" + deleveryAdress + ", orderedDate=" + orderedDate + ", customer=" + customer + ", products=" + products + '}';
    }
    
}
