package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.Service.IngredientService;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.Service.UnitOfMeasureService;
import com.spring.project.recipe.commands.IngredientCommand;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.transformer.IngredientTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientController;

    @Mock
    IngredientTransformer ingredientTransformer;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService, ingredientTransformer);
    }

    @Test
    void getRecipeIngredient() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.getById(any())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredients")).andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/ingredients"));

    }

    @Test
    void getRecipeIdIngredientId() throws Exception{

        //given
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);

        //when
        when(ingredientService.findByRecipeId(any(), any())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredient/1/view")).andExpect(status().isOk()).
                andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    void updateIngredient() throws  Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        //when

        when(ingredientService.findByRecipeId(any(), any())).thenReturn(new IngredientCommand());
        mockMvc.perform(get("/recipe/1/ingredient/1/update")).andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/IngredientForm"))
                .andExpect(model().attributeExists("uoms","ingredients"));

    }

    @Test
    void updateRecipeIngredient() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setRecipeId(1L);
        when(ingredientService.updateIngredient(any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/1/ingredient").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/"
                        +ingredientCommand.getId()+"/ingredient/"+ingredientCommand.getId()+"/view"));
    }
}