package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.commands.RecipeCommand;

import java.util.Set;

public interface RecipeService {
     Set<Recipe> getRecipe();

    //Todo : implement getRecipeById

     Recipe findById(Long id);
    //Todo: implement CRUD service for recipes

     RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
 }
