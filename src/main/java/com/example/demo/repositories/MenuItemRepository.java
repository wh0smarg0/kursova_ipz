package com.example.demo.repositories;

import com.example.demo.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<Object> findByName(String itemName);
}

