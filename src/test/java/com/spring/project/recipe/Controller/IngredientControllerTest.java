package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {


    @Mock
    RecipeService recipeService;


    IngredientController ingredientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService);
    }

    @Test
    void getRecipeIngredient() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.getById(any())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredient")).andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/ingredients"));

    }
}