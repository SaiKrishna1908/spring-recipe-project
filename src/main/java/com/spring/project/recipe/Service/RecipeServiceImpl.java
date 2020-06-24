package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Repos.RecipeRepository;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.transformer.RecipeCommandTransformer;
import com.spring.project.recipe.transformer.RecipeTransformer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {



    private final RecipeRepository recipeRepository;
    private final RecipeTransformer recipeTransformer;
    private final RecipeCommandTransformer recipeCommandTransformer;


    @Override
    @Transactional
    public Set<Recipe> getRecipe() {
        Set<Recipe> set = new HashSet<>();

        recipeRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    @Transactional
    public Recipe findById(Long id) {
        Optional<Recipe> optional;
        optional = recipeRepository.findById(id);

        if(!optional.isPresent()){
            throw new RuntimeException("No Recipe with specified id found");
        }
        else return optional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detached = recipeTransformer.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detached);
        return recipeCommandTransformer.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand getById(Long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        return recipeCommandTransformer.convert(recipe);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }


}
