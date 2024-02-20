package com.example.furrytales.entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private int cartID;
    private int customerID;
    private int productID;
    private int quantity;
    private float mrp;
    private float discountedPrice;
    private float total;
    private String title;
    private String image;
    private String category;
    private String company;


    public Cart() {
    }

    public Cart(int cartID, int customerID, int productID, int quantity, float mrp, float discountedPrice, float total, String title, String image, String category, String company) {
        this.cartID = cartID;
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.mrp = mrp;
        this.discountedPrice = discountedPrice;
        this.total = total;
        this.title = title;
        this.image = image;
        this.category = category;
        this.company = company;
    }



    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", customerID=" + customerID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", mrp=" + mrp +
                ", discountedPrice=" + discountedPrice +
                ", total=" + total +
                ", title=" +title +
                ", image" +image+
                ", company" +image+
                ", category"+category+
                '}';
    }
}
