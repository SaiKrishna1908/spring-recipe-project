package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Difficulty;
import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.commands.IngredientCommand;
import com.spring.project.recipe.commands.CategoryCommand;
import com.spring.project.recipe.commands.NotesCommand;
import com.spring.project.recipe.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RecipeTransformerTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    private RecipeTransformer recipeTransformer;
    @BeforeEach
    void setUp() {
        recipeTransformer = new RecipeTransformer(new NotesTransformer(), new CategoryTransformer(), new IngredientTransformer(new UnitOfMeasureTransformer()));
    }

    @Test
    void convert() {

        RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        CategoryCommand category1 = new CategoryCommand();
        CategoryCommand category2 = new CategoryCommand();
        category1.setId(CAT_ID_1);
        category2.setId(CAT_ID2);

        recipeCommand.getCategoryCommands().add(category1);
        recipeCommand.getCategoryCommands().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);
        ingredient.setId(INGRED_ID_2);

        recipeCommand.getIngredientCommands().add(ingredient);
        recipeCommand.getIngredientCommands().add(ingredient1);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        recipeCommand.setNotesCommand(notesCommand);

        Recipe recipe = recipeTransformer.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());

    }
}