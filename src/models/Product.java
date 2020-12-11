/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class Product {
    
    private int productId;
    private String code;
    private String name;
    private double price;
    private int quantityInStock;
    private Category productCategory;

    public Product(int productId, String code, String name, double price, int quantityInStock) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public Product(String code, String name, double price, int quantityInStock, Category category) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.productCategory = category;
    }

    public Product() {
    }
    
    

    public int getProductId() {
        return productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", code=" + code + ", name=" + name + ", price=" + price + ", quantityInStock=" + quantityInStock + ", productCategory=" + productCategory + '}';
    }

   
    
    
}
