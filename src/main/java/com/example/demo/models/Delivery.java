package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus; // Наприклад: "В дорозі", "Доставлено"

    @Column(name = "address", nullable = false)
    private String address;

    public Delivery() {
    }

    public Delivery(Order order, String deliveryStatus, String address) {
        this.order = order;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", order=" + order +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
