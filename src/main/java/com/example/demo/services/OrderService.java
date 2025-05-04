package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(String customerName) {
        // Отримуємо клієнта за ім'ям
        Customer customer = customerRepository.findByName(customerName)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Створення нового замовлення
        Order order = new Order();
        order.setCustomer(customer); // Прив'язуємо клієнта до замовлення
        order.setStatus("Pending");

        // Збереження замовлення в базі даних
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
