package com.spring.project.recipe.bootstrap;

import com.spring.project.recipe.Model.*;
import com.spring.project.recipe.Repos.CategoryRepository;
import com.spring.project.recipe.Repos.RecipeRepository;
import com.spring.project.recipe.Repos.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;

@Slf4j
@Service
public class Dataloader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Dataloader(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        log.debug("loading data...");
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(5);
        guacamoleRecipe.setDescription("How to Make Perfect Guacamole Recipe");
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("Qwerty");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDifficulty(Difficulty.EASY);

        StringBuilder guacamoleDirections = new StringBuilder();

        guacamoleDirections.append("Cut the avocado, remove flesh\n");
        guacamoleDirections.append("Mash with a fork\n");
        guacamoleDirections.append("Add salt, lime juice, and the rest\n");
        guacamoleDirections.append("Add the chopped onion, cilantro, black pepper, and chiles\n");
        guacamoleDirections.append(" Serve immediately\n");
        guacamoleRecipe.setDirections(guacamoleDirections.toString());

        guacamoleRecipe.getCategories().add(categoryRepository.findByDescription("Mexican").get());



        Ingredient guacamoleIngredient = new Ingredient();
        guacamoleIngredient.setAmount(new BigDecimal(1));
        guacamoleIngredient.setDescription("Ripe avacados");
        guacamoleIngredient.setUnitOfMeasure(unitOfMeasureRepository.findByUom("whole").get());


        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setNotes("All you really need to make guacamole is ripe avocados and salt. After that, a " +
                "little lime or lemon juice—a splash of acidity—will help to balance the richness of the avocado." +
                " Then if you want, add chopped cilantro, chiles, onion, and/or tomato.");


        guacamoleRecipe.getIngredients().add(guacamoleIngredient);
        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleNotes.setRecipe(guacamoleRecipe);
        guacamoleIngredient.setRecipe(guacamoleRecipe);
        recipeRepository.save(guacamoleRecipe);


        /*----------------------------------------------------------------------------------------------------------------- */

        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setPrepTime(30);
        tacoRecipe.setCookTime(10);
        tacoRecipe.setDescription("How to Make Spicy Grilled Chicken Taco's");
        tacoRecipe.setServings(4);
        tacoRecipe.setSource("Sally Vargas");
        tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setDifficulty(Difficulty.MODERATE);

        StringBuilder tacoDirections = new StringBuilder();

        tacoDirections.append("Prepare a gas or charcoal grill for medium-high, direct heat.\n");
        tacoDirections.append("Make the marinade and coat the chicken\n");
        tacoDirections.append("Grill the chicken\n");
        tacoDirections.append("Warm the tortillas\n");
        tacoDirections.append("Assemble the tacos\n");
        tacoRecipe.setDirections(tacoDirections.toString());

        tacoRecipe.getCategories().add(categoryRepository.findByDescription("Homemade").get());


        Ingredient tacoIngredient = new Ingredient();
        tacoIngredient.setAmount(new BigDecimal(2));
        tacoIngredient.setDescription("ancho chilli powder");
        tacoIngredient.setUnitOfMeasure(unitOfMeasureRepository.findByUom("tablespoons").get());


        Notes tacoNotes = new Notes();
        tacoNotes.setNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n"+
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled\n" +
                "jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot\n" +
                "pan on the stove comes wafting through the house.\n");


        tacoRecipe.getIngredients().add(tacoIngredient);
        tacoRecipe.setNotes(tacoNotes);

        tacoNotes.setRecipe(tacoRecipe);
        tacoIngredient.setRecipe(tacoRecipe);
        recipeRepository.save(tacoRecipe);

        /*------------------------------------------------------------------------------------------------------------*/
        System.out.println("Data Initialized... ");



    }
}
