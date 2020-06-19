package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Ingredient;
import org.springframework.lang.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientTransformer implements Converter<com.spring.project.recipe.commands.IngredientCommand, Ingredient> {

    private final UnitOfMeasureTransformer unitOfMeasureTransformer;


    @Override
    @Nullable
    @Synchronized
    public Ingredient convert(com.spring.project.recipe.commands.IngredientCommand ingredientCommand) {
        if (ingredientCommand == null)
            return null;

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setAmount(ingredientCommand.getAmount());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setUnitOfMeasure(unitOfMeasureTransformer.convert(ingredientCommand.getUnitOfMeasureCommand()));


        return ingredient;
    }
}
