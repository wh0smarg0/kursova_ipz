package com.example.demo;

public class OrderItemRequest {
    private Long orderId;   // ID заказа
    private Long menuItem;  // Объект пункта меню
    private int quantity;  // Количество

    // Геттеры и сеттеры
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(Long menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
