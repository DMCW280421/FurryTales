package com.example.furrytales.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private int orderID;
    private int productId;
    private int quantity;
    private int mrp;
    private int price;
    private int total;

    public Order() {
    }

    public Order(int orderID, int productId, int quantity, int mrp, int price, int total) {
        this.orderID = orderID;
        this.productId = productId;
        this.quantity = quantity;
        this.mrp = mrp;
        this.price = price;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", mrp=" + mrp +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
