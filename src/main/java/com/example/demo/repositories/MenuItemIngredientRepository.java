package com.example.demo.repositories;

import com.example.demo.models.MenuItemIngredient;
import com.example.demo.models.MenuItemIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemIngredientRepository extends JpaRepository<MenuItemIngredient, MenuItemIngredientId> {

    // Явно указываем SQL запрос
    @Query("SELECT mii FROM MenuItemIngredient mii WHERE mii.menuItemsId = :menuItemsId")
    List<MenuItemIngredient> findByMenuItemsId(@Param("menuItemsId") Long menuItemsId);
}





