package com.example.furrytales.entity;

import java.io.Serializable;

public class Product implements Serializable {

    private int productID;
    private String title;
    private String description;
    private String image;
    private float mrp;
    private float discount;
    private String category;
    private String company;

    public Product() {
    }

    public Product(int productID, String title, String description, String image, float mrp, float discount, String category, String company) {
        this.productID = productID;
        this.title = title;
        this.description = description;
        this.image = image;
        this.mrp = mrp;
        this.discount = discount;
        this.category = category;
        this.company = company;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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
        return "Product{" +
                "productID=" + productID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", mrp=" + mrp +
                ", discount=" + discount +
                ", category='" + category + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}

