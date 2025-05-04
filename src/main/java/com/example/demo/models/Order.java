package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; // Статус замовлення: Pending, In Progress, Delivered, тощо

    @ManyToOne // Зв'язок "багато до одного" з Customer
    @JoinColumn(name = "customer_name")
    private Customer customer;

    public Order() {
        this.status = "Pending"; // За замовчуванням
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
