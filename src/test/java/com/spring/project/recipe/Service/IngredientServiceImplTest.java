package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Repos.RecipeRepository;
import com.spring.project.recipe.commands.IngredientCommand;
import com.spring.project.recipe.transformer.IngredientCommandTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientCommandTransformer ingredientCommandTransformer;


    IngredientServiceImpl ingredientService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository,ingredientCommandTransformer);
    }

    @Test
    void findByRecipeId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setRecipe(recipe);


        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(2L);
        ingredient1.setRecipe(recipe);


        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(3L);
        ingredient2.setRecipe(recipe);


        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient1.getId());

        //when
        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));

        when(ingredientCommandTransformer.convert(any())).thenReturn(ingredientCommand);

        IngredientCommand ingredientCommand1 = ingredientService.findByRecipeId(1L,2L);

        //then
        assertEquals(ingredient1.getId(), ingredientCommand1.getId());

    }
}