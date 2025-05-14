package com.example.demo.services;

import com.example.demo.models.Ingredient;
import com.example.demo.models.IngredientDto;
import com.example.demo.models.MenuItemIngredient;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.MenuItemIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final MenuItemIngredientRepository menuItemIngredientRepository;

    // Получить ингредиенты для конкретного menuItemId
    public List<IngredientDto> getIngredientsByMenuItemId(Long menuItemId) {
        // Получаем все связи для menuItemId
        List<MenuItemIngredient> links = menuItemIngredientRepository.findByMenuItemsId(menuItemId);

        // Преобразуем связи в список ингредиентов
        return links.stream()
                .map(link -> ingredientRepository.findById(link.getIngredientId()).orElse(null))  // Получаем ингредиенты
                .filter(Objects::nonNull) // Фильтруем null
                .map(ingredient -> new IngredientDto(ingredient.getName())) // Преобразуем в DTO
                .collect(Collectors.toList());
    }

    // Добавить ингредиент в элемент меню
    public void addIngredientToMenuItem(Long menuItemId, String ingredientName) {
        // Ищем или создаем новый ингредиент
        Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                .orElseGet(() -> ingredientRepository.save(new Ingredient(ingredientName)));

        // Создаем связь между menuItemId и ингредиентом
        MenuItemIngredient link = new MenuItemIngredient(menuItemId, ingredient.getId());
        menuItemIngredientRepository.save(link);
    }
}
