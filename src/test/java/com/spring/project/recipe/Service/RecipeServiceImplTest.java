package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Repos.RecipeRepository;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.transformer.RecipeCommandTransformer;
import com.spring.project.recipe.transformer.RecipeTransformer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@Slf4j
class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandTransformer recipeCommandTransformer;

    @Mock
    RecipeTransformer recipeTransformer;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeTransformer, recipeCommandTransformer);

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

    @Test
    void saveCommandRecipe(){
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setCookTime(5);
        recipeCommand.setDescription("Hello world");
        recipeCommand.setId(1L);

        Recipe recipe = new Recipe();
        recipe.setCookTime(5);
        recipe.setDescription("Hello world");
        recipe.setId(1L);

        when(recipeTransformer.convert(any())).thenReturn(recipe);

        Recipe detached = recipeTransformer.convert(recipeCommand);
        assertNotNull(detached);

        when(recipeRepository.save(any())).thenReturn(recipe);

        Recipe savedRecipe = recipeRepository.save(detached);

        verify(recipeRepository,times(1)).save(any());

        when(recipeCommandTransformer.convert(any())).thenReturn(recipeCommand);

        RecipeCommand recipeCommand1 = recipeCommandTransformer.convert(recipe);

        assertEquals(savedRecipe.getId(), recipeCommand1.getId());
    }

    @Test
    void testGetById(){
      Recipe recipe = new Recipe();
      recipe.setId(1L);

      Optional<Recipe> optionalRecipe = Optional.of(recipe);

      when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

      RecipeCommand recipeCommand = new RecipeCommand();
      recipeCommand.setId(1L);

      when(recipeCommandTransformer.convert(any())).thenReturn(recipeCommand);

      RecipeCommand recipeCommand1 = recipeService.getById(1L);

      verify(recipeRepository,times(1)).findById(any());

    }

    @Test
    void testDeleteById(){
        recipeService.deleteById(any());
        verify(recipeRepository, times(1)).deleteById(any());
    }
    //Todo Testing on getById and deleteById
}