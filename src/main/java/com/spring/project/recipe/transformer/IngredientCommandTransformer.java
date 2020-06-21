package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.commands.IngredientCommand;
import org.springframework.lang.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//takes a Note's and returns a Note's Command
@Component
@RequiredArgsConstructor
public class IngredientCommandTransformer implements
        Converter<Ingredient, IngredientCommand> {


    private final UnitOfMeasureCommandTransformer unitOfMeasureCommandTransformer;

    @Override
    @Synchronized
    @Nullable
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

         IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        if (ingredient.getRecipe() != null) {
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }
        ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommandTransformer.convert(ingredient.getUnitOfMeasure()));

        return ingredientCommand;
    }


}
