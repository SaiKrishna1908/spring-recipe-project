package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class IngredientCommandTransformerTest {

     Long id = 1L;
     String description = "ingredient test";

    private IngredientCommandTransformer ingredientCommandTransformer;
    private UnitOfMeasureCommandTransformer unitOfMeasureCommandTransformer;
    @BeforeEach
    void setUp(){
        unitOfMeasureCommandTransformer = new UnitOfMeasureCommandTransformer();
        ingredientCommandTransformer = new IngredientCommandTransformer(unitOfMeasureCommandTransformer);
    }

    @Test
    void convert() {

        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setDescription(description);

        com.spring.project.recipe.commands.IngredientCommand ingredientCommand = ingredientCommandTransformer.convert(ingredient);
        assertEquals(ingredient.getId(), ingredientCommand.getId());
    }

    @Test
    void testEmptyObject(){
        assertNotNull(ingredientCommandTransformer.convert(new Ingredient()));
    }

    @Test
    void testNullObject(){
        assertNull(ingredientCommandTransformer.convert(null));
    }
}