package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.exceptions.NotFoundException;
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


import javax.swing.text.html.Option;

import java.util.Optional;

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
    void getRecipeCommandNotFound() throws  Exception{
        //given
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        Recipe recipe = Recipe.builder().id(1L).build();

        //when
        when(recipeService.findById(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/4/show")).andExpect(status().isNotFound());
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
        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "").param("description", "some string")
                .param("directions" , "some xyz")).
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

    @Test
    void PageNotFound() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.findById(any())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/recipe/1/show")).andExpect(status().isNotFound())
                .andExpect(view().name("error/404NotFound"));
    }

    @Test
    void NumberFormatException() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController)
                .setControllerAdvice(new GlobalExceptionController()).build();

        when(recipeService.findById(any())).thenThrow(NumberFormatException.class);

        mockMvc.perform(get("/recipe/1/show")).andExpect(status().isBadRequest())
            .andExpect(view().name("error/400BadRequest"));
    }
}