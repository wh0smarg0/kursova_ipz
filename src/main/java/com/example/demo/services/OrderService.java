package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(String customerName, String deliveryMethod) {
        Customer customer = customerRepository.findByName(customerName)
                .orElseThrow(() -> new RuntimeException("Клієнт не знайдений: " + customerName));

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus("Pending");

        if ("inrest".equalsIgnoreCase(deliveryMethod)) {
            int randomTable = new Random().nextInt(30) + 1;
            order.setTableNumber(randomTable);
        } else {
            order.setTableNumber(null);
        }

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
