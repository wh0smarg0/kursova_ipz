package com.example.demo.controllers;

import com.example.demo.models.Delivery;
import com.example.demo.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        if (delivery.getDeliveryStatus() == null || delivery.getDeliveryStatus().isEmpty()) {
            delivery.setDeliveryStatus("Очікується"); // або "В дорозі", залежить від логіки
        }
        return deliveryRepository.save(delivery);
    }
}
