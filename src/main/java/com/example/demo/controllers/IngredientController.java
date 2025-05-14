package com.example.demo.controllers;

import com.example.demo.models.IngredientDto;
import com.example.demo.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-items")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    // Получить список ингредиентов для определенного menuItemId
    @GetMapping("/{menuItemId}/ingredients")
    public List<IngredientDto> getIngredientsForMenuItem(@PathVariable Long menuItemId) {
        return ingredientService.getIngredientsByMenuItemId(menuItemId);
    }

    // Добавить ингредиент в элемент меню
    @PostMapping("/{menuItemId}/ingredients")
    public ResponseEntity<Void> addIngredientToMenuItem(@PathVariable Long menuItemId,
                                                        @RequestBody IngredientDto ingredientDto) {
        ingredientService.addIngredientToMenuItem(menuItemId, ingredientDto.getName());
        return ResponseEntity.ok().build();
    }
}
