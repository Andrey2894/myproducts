package com.example.myproducts.dal;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Название товара не может быть пустым")
    @Size(min = 1, max = 255,  message = "Название товара должно быть длинной от 1 до 255 символов")
    @Column(name = "name", nullable = false)
    private String name;
    @Size(max = 4096,  message = "Описание товара должно быть длинной до 4096 символов")
    private String description;
    @Min(value = 0, message = "Цена товара не может быть меньше 0")
    private Long price = 0L;
    private Boolean available = false;

    public Product(Long id, String name, String description, Long price, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    public Product() {
        //default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPrice(Long price) {
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
