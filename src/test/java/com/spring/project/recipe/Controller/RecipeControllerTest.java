package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.commands.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {


    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    RecipeController recipeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);

    }

    @Test
    void findById() throws Exception {
        Recipe recipe = Recipe.builder().id(1L).build();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        when(recipeService.findById(any())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().
                name("recipe/show")).andExpect(model().attributeExists("recipe"));

    }

    @Test
    void getRecipeCommand() throws Exception {
        //given
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        //no when

        //then
        mockMvc.perform(get("/recipe/new")).andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform")).
                andExpect(model().attributeExists("recipe"));
    }

    @Test
    void saveRecipe() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(3L);

        //when
        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        //then
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED)).
                andExpect(status().is3xxRedirection()).
                andExpect(view().name("redirect:/recipe/3/show"));
    }

    @Test
    void updateRecipeCommand() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        //given
        Long id = 1L;

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(id);
        //when
        when(recipeService.getById(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe/1/update").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    void deleteRecipeCommand() throws  Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipe/1/delete")).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        //given
        Long id = 1L;

        verify(recipeService, times(1)).deleteById(any());
    }
}