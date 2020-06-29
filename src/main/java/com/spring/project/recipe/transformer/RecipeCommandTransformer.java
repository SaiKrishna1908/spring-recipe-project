package com.spring.project.recipe.transformer;


import com.spring.project.recipe.Model.Category;
import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.commands.RecipeCommand;
import org.springframework.lang.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



// convert's a model object to pojo
@RequiredArgsConstructor
@Component
public class RecipeCommandTransformer implements Converter<Recipe,RecipeCommand> {

    private final NotesCommandTransformer notesCommandTransformer;
    private  final CategoryCommandTransformer categoryCommandTransformer;
    private  final IngredientCommandTransformer ingredientCommandTransformer;

    @Nullable
    @Synchronized
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null)
        return null;

        final RecipeCommand recipeCommand= new RecipeCommand();

        recipeCommand.setId(recipe.getId());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setDifficulty(recipe.getDifficulty());

        recipeCommand.setNotesCommand(notesCommandTransformer.convert(recipe.getNotes()));

        for(Category c :recipe.getCategories()){
            recipeCommand.getCategoryCommands().add(categoryCommandTransformer.convert(c));
        }

        for(Ingredient i : recipe.getIngredients()){
            recipeCommand.getIngredientCommands().add(ingredientCommandTransformer.convert(i));
        }

        return recipeCommand;
    }

}
