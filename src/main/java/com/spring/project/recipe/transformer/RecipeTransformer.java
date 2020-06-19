package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.commands.CategoryCommand;
import com.spring.project.recipe.commands.IngredientCommand;
import com.spring.project.recipe.commands.RecipeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RecipeTransformer implements Converter<RecipeCommand, Recipe> {


    private final NotesTransformer notesTransformer;
    private final CategoryTransformer categoryTransformer;
    private final IngredientTransformer ingredientTransformer;

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand==null)
            return null;

        final Recipe recipe = new Recipe();

        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDifficulty(recipeCommand.getDifficulty());

        recipe.setNotes(notesTransformer.convert(recipeCommand.getNotesCommand()));

        for(CategoryCommand cc : recipeCommand.getCategoryCommands()){
            recipe.getCategories().add(categoryTransformer.convert(cc));
        }

        for(IngredientCommand ic: recipeCommand.getIngredientCommands()){
            recipe.getIngredients().add(ingredientTransformer.convert(ic));
        }

        return recipe;
    }
}
