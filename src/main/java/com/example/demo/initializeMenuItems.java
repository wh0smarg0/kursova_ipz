package com.example.demo;

import com.example.demo.models.MenuItem;
import com.example.demo.repositories.MenuItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class initializeMenuItems {
    @Bean
    CommandLineRunner commandLineRunner(MenuItemRepository menuItemRepository) {
        return args -> {
            new MenuItem("Caesar Salad", 150.00, "Кухня");
            new MenuItem("Espresso", 50.00, "Бар");

            menuItemRepository.saveAll(
                    List.of()
            );
        };
    }
}
