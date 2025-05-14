package com.example.demo.models;

import java.io.Serializable;
import java.util.Objects;

public class MenuItemIngredientId implements Serializable {

    private Long menuItemsId;
    private Long ingredientId;

    public MenuItemIngredientId() {
    }

    public MenuItemIngredientId(Long menuItemsId, Long ingredientId) {
        this.menuItemsId = menuItemsId;
        this.ingredientId = ingredientId;
    }

    // Геттери та сеттери
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

    // Перевизначення equals і hashCode для коректної роботи комбінованого ключа
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemIngredientId that = (MenuItemIngredientId) o;
        return Objects.equals(menuItemsId, that.menuItemsId) &&
                Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemsId, ingredientId);
    }
}

