package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeId(Long recipeId, Long ingredientId);

    IngredientCommand updateIngredient(IngredientCommand ingredientCommand);

    void deleteByRecipeIdIngredientId(Long rid, Long id);
}
