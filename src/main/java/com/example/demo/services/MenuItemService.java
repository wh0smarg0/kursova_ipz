package com.example.demo.services;

import com.example.demo.models.MenuItem;
import com.example.demo.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        System.out.println("Saved MenuItem ID: " + savedMenuItem.getId());
        return savedMenuItem;
    }


    public MenuItem updateMenuItem(Long id, MenuItem updatedMenuItem) {
        MenuItem existingMenuItem = getMenuItemById(id);
        if (existingMenuItem != null) {
            existingMenuItem.setName(updatedMenuItem.getName());
            existingMenuItem.setCategory(updatedMenuItem.getCategory());
            existingMenuItem.setPrice(updatedMenuItem.getPrice());
            existingMenuItem.setDescription(updatedMenuItem.getDescription());
            existingMenuItem.setImageUrl(updatedMenuItem.getImageUrl());
            return menuItemRepository.save(existingMenuItem);
        }
        return null;
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}

