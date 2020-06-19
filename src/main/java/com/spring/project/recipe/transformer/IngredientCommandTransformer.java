package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Ingredient;
import org.springframework.lang.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//takes a Note's and returns a Note's Command
@Component
@RequiredArgsConstructor
public class IngredientCommandTransformer implements Converter<Ingredient, com.spring.project.recipe.commands.IngredientCommand> {


    private final UnitOfMeasureCommandTransformer unitOfMeasureCommandTransformer;

    @Override
    @Synchronized
    @Nullable
    public com.spring.project.recipe.commands.IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null)
            return null;

        final com.spring.project.recipe.commands.IngredientCommand ingredientCommand = new com.spring.project.recipe.commands.IngredientCommand();

        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommandTransformer.convert(ingredient.getUnitOfMeasure()));

        return ingredientCommand;
    }


}
