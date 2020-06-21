package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Repos.RecipeRepository;
import com.spring.project.recipe.commands.IngredientCommand;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.transformer.IngredientCommandTransformer;
import com.spring.project.recipe.transformer.IngredientTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientCommandTransformer ingredientCommandTransformer;
    @Override
    public IngredientCommand findByRecipeId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if(!recipe.isPresent()){
            log.debug("no Recipe found with id "+ recipeId);
        }

        Optional<IngredientCommand> ingredientCommand = recipe.get().getIngredients().stream().filter(ingredient ->
            ingredient.getId().equals(ingredientId)).map(ingredient -> ingredientCommandTransformer.convert(ingredient)).findAny();



        if(! ingredientCommand.isPresent()){
            log.debug("No ingredient found with id"+ ingredientId);
        }
        return ingredientCommand.get();
    }
}
