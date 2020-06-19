package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTransformerTest {

    Long ID = 1L;
    String description = "Ingredient Transformer Test";
    IngredientTransformer ingredientTransformer;
    UnitOfMeasureTransformer unitOfMeasureTransformer;

    @BeforeEach
    void setUp() {
        unitOfMeasureTransformer = new UnitOfMeasureTransformer();
        ingredientTransformer = new IngredientTransformer(unitOfMeasureTransformer);

    }

    @Test
    void convert() {

        com.spring.project.recipe.commands.IngredientCommand ingredientCommand = new com.spring.project.recipe.commands.IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(description);

        Ingredient ingredient = ingredientTransformer.convert(ingredientCommand);

        assertEquals(ingredientCommand.getId(),ingredient.getId() );

    }

    @Test
    void testEmptyObject(){
        assertNotNull(ingredientTransformer.convert(new com.spring.project.recipe.commands.IngredientCommand()));
    }

    @Test
    void testNullObject(){
        assertNull(ingredientTransformer.convert(null));
    }
}