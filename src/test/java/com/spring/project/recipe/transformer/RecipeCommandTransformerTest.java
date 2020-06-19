package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.*;
import com.spring.project.recipe.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RecipeCommandTransformerTest {

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

    NotesCommandTransformer notesCommandTransformer;
    CategoryCommandTransformer categoryCommandTransformer;
    IngredientCommandTransformer ingredientCommandTransformer;


    RecipeCommandTransformer recipeCommandTransformer;

    @BeforeEach
    void setUp() {
        notesCommandTransformer = new NotesCommandTransformer();
        categoryCommandTransformer = new CategoryCommandTransformer();
        ingredientCommandTransformer = new IngredientCommandTransformer(new UnitOfMeasureCommandTransformer());
    recipeCommandTransformer = new RecipeCommandTransformer(notesCommandTransformer,categoryCommandTransformer,ingredientCommandTransformer);
    }

    @Test
    void testNullObject(){
        assertNull(recipeCommandTransformer.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(recipeCommandTransformer.convert(new Recipe()));
    }

    @Test
    void convert() {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);
        Ingredient ingredient1 = new Ingredient();
        ingredient.setId(INGRED_ID_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient1);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        RecipeCommand command = recipeCommandTransformer.convert(recipe);

        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotesCommand().getId());
        assertEquals(2, command.getCategoryCommands().size());
        assertEquals(2, command.getIngredientCommands().size());
    }
}