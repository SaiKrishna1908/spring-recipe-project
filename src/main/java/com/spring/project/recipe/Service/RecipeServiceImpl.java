package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Repos.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {


    private final RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipe() {
        Set<Recipe> set = new HashSet<>();

        recipeRepository.findAll().forEach(set::add);
        return set;
    }
}
