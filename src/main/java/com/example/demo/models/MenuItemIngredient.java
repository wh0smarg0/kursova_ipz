package com.example.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(MenuItemIngredientId.class)
public class MenuItemIngredient {

    @Id
    private Long menuItemsId;

    @Id
    private Long ingredientId;

    // Конструкторы, геттеры, сеттеры
    public MenuItemIngredient() {}

    public MenuItemIngredient(Long menuItemsId, Long ingredientId) {
        this.menuItemsId = menuItemsId;
        this.ingredientId = ingredientId;
    }

    public Long getMenuItemsId() {
        return menuItemsId;
    }

    public void setMenuItemsId(Long menuItemsId) {
        this.menuItemsId = menuItemsId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
