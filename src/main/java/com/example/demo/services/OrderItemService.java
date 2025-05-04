package com.example.demo.services;

import com.example.demo.OrderItemRequest;
import com.example.demo.config.ResourceNotFoundException;
import com.example.demo.models.MenuItem;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItem;
import com.example.demo.repositories.MenuItemRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public OrderItem createOrderItem(OrderItemRequest request) {
        // Проверяем наличие Order ID
        if (request.getOrderId() == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        // Проверяем наличие MenuItem ID
        if (request.getMenuItem() == null) {
            throw new IllegalArgumentException("Menu Item ID cannot be null");
        }

        // Находим заказ по его ID
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + request.getOrderId()));

        // Находим пункт меню по его ID
        MenuItem menuItem = menuItemRepository.findById(request.getMenuItem())
                .orElseThrow(() -> new ResourceNotFoundException("Menu Item not found with ID: " + request.getMenuItem()));

        // Создаём новый пункт заказа
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order); // Здесь передаётся объект Order
        orderItem.setMenuItem(menuItem); // Здесь передаётся объект MenuItem
        orderItem.setQuantity(request.getQuantity());

        // Сохраняем OrderItem
        return orderItemRepository.save(orderItem);
    }


    public List<OrderItem> getItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public void deleteOrderItem(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("OrderItem not found with ID: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}
