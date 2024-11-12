package com.example.myproducts.dal;




public class Product {
//    private Long id;
    private String name;
    private String description;
    private long price;
    private boolean available;

    public Product(String name, String description, long price, boolean available) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
