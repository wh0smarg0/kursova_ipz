package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    private String category; // Наприклад, "кухня" або "бар"

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    // Конструктор за замовчуванням
    public MenuItem() {
    }

    // Конструктор із параметрами
    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Getter для ID
    public Long getId() {
        return id;
    }

    // Setter для ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter для Name
    public String getName() {
        return name;
    }

    // Setter для Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter для Price
    public double getPrice() {
        return price;
    }

    // Setter для Price
    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    // Setter для Description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter для Category
    public String getCategory() {
        return category;
    }

    // Setter для Category
    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    // Перевизначення toString() для зручного виводу інформації
    @Override
    public String toString () {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}