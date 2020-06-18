package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Repos.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Slf4j
class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);

    }

    @Test
    void getRecipe() {

        Recipe recipe = new Recipe();
        Set<Recipe> recipes= new HashSet<>();
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> recipeSet = recipeService.getRecipe();
        log.debug(Integer.toString(recipeSet.size()));
        assertEquals(recipeSet.size(), 1);
    }

    @Test
    void findById(){
        Recipe recipe = Recipe.builder().id(1L).build();

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        Recipe idrecipe = recipeRepository.findById(1L).get();
        assertEquals(1L, idrecipe.getId());
    }
}