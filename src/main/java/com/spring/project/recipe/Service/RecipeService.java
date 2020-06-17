package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipe();

    //Todo : implement getRecipeById

    public Recipe findById(Long id);
    //Todo: implement CRUD service for recipes

 }
