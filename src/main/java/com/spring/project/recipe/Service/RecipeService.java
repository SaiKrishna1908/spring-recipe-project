package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.commands.RecipeCommand;

import java.util.Set;

public interface RecipeService {

     Set<Recipe> getRecipe();
     Recipe findById(Long id);
     RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
     RecipeCommand getById(Long id);
     void deleteById(Long id);
    //Todo: implement CRUD service for recipes
}
