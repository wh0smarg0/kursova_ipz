package com.example.demo.controllers;

import com.example.demo.*;
import com.example.demo.config.ResourceNotFoundException;
import com.example.demo.models.MenuItem;
import com.example.demo.models.Order;
import com.example.demo.models.OrderItem;
import com.example.demo.repositories.MenuItemRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.OrderItemService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuItemRepository menuItemRepository;

    // Конструктор
    public OrderItemController(OrderItemService orderItemService, OrderRepository orderRepository,
                               OrderItemRepository orderItemRepository, MenuItemRepository menuItemRepository) {
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemRequest request) {
        // Проверка на null для идентификатора заказа
        if (request.getOrderId() == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        // Перевірка на null
        if (request.getMenuItem() == null) {
            throw new IllegalArgumentException("Menu Item ID cannot be null");
        }

        // Перевірка замовлення
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + request.getOrderId()));

        // Перевірка пункту меню
        MenuItem menuItemId = menuItemRepository.findById(request.getMenuItem())
                .orElseThrow(() -> new ResourceNotFoundException("Menu Item not found with ID: " + request.getMenuItem()));

        // Створення OrderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItemId);
        orderItem.setQuantity(request.getQuantity());

        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemRepository.save(orderItem));
    }

    @GetMapping("/menu_items")
    public ResponseEntity<List<MenuItem>> getMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return ResponseEntity.ok(menuItems);
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        // Отримуємо всі елементи замовлення по ID
        List<OrderItem> orderItems = orderItemService.getItemsByOrderId(orderId);
        return ResponseEntity.ok(orderItems);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        // Видаляємо пункт замовлення
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
            String error = ex.getBindingResult().getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + error);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
