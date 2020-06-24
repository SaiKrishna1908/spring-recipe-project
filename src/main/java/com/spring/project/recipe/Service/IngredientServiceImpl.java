package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.Repos.RecipeRepository;
import com.spring.project.recipe.Repos.UnitOfMeasureRepository;
import com.spring.project.recipe.commands.IngredientCommand;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.transformer.IngredientCommandTransformer;
import com.spring.project.recipe.transformer.IngredientTransformer;
import com.spring.project.recipe.transformer.UnitOfMeasureTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientCommandTransformer ingredientCommandTransformer;
    private  final IngredientTransformer ingredientTransformer;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    @Transactional
    public IngredientCommand findByRecipeId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if(!recipe.isPresent()){
            log.debug("no Recipe found with id "+ recipeId);
        }

        Optional<IngredientCommand> ingredientCommand = recipe.get().getIngredients().stream().filter(ingredient ->
            ingredient.getId().equals(ingredientId)).map(ingredient -> ingredientCommandTransformer.convert(ingredient))
                .findAny();


        if(! ingredientCommand.isPresent()){
            log.debug("No ingredient found with id"+ ingredientId);
        }
        return ingredientCommand.get();
    }

    @Override
    @Transactional
    public IngredientCommand updateIngredient(IngredientCommand ingredientCommand) {
        Optional<Recipe> detachedRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());


        if(!detachedRecipe.isPresent()){
            log.error("Recipe not found with id"+ ingredientCommand.getRecipeId()+"for Ingredient with id"+
                    ingredientCommand.getId());
            return new IngredientCommand();
        }
        else {
            Recipe recipe = detachedRecipe.get();

            Optional<Ingredient> ingredient = recipe.getIngredients().stream().filter(ingredient1
                    -> ingredient1.getId().equals(ingredientCommand.getId())).findFirst();

            if(!ingredient.isPresent()){
                //new Ingredient
                log.error("Ingredient not found");
                Ingredient toSaveIngredient= ingredientTransformer.convert(ingredientCommand);
                toSaveIngredient.setRecipe(recipe);
                recipe.addIngredient(toSaveIngredient);


            }

            else{
                Ingredient ingredientFound = ingredient.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.
                        findById(ingredientCommand.getUnitOfMeasureCommand()
                                .getId()).orElseThrow( () ->  new RuntimeException("No Such UOM Exists")));

            }




            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream().filter(ingredient1
                    -> ingredient1.getId().equals(ingredientCommand.getId())).findFirst();

            if(!savedIngredientOptional.isPresent()){
                //this is the case when new Ingredient is added
                savedIngredientOptional =savedRecipe.getIngredients().stream().filter(ingredient1 -> ingredient1.getDescription()
                        .toLowerCase().equals(ingredientCommand.getDescription().toLowerCase()))
                        .filter(ingredient1 -> ingredient1.getUnitOfMeasure().getId()
                                .equals(ingredientCommand.getUnitOfMeasureCommand().getId()))
                        .filter(ingredient1 -> ingredient1.getAmount().equals(ingredientCommand.getAmount())).findFirst();
            }
            return ingredientCommandTransformer.convert(savedIngredientOptional.get());

        }
    }


}
