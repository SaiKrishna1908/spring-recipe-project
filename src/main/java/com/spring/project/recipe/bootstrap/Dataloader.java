package com.spring.project.recipe.bootstrap;

import com.spring.project.recipe.Model.*;
import com.spring.project.recipe.Repos.CategoryRepository;
import com.spring.project.recipe.Repos.RecipeRepository;
import com.spring.project.recipe.Repos.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class Dataloader implements ApplicationListener<ContextRefreshedEvent> {

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
    public void onApplicationEvent(ContextRefreshedEvent event) {

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

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2),
                unitOfMeasureRepository.findByUom("each").get()));
        guacamoleRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"),
                unitOfMeasureRepository.findByUom("teaspoon").get()));
        guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2)
                , unitOfMeasureRepository.findByUom("tablespoon").get()));
        guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",
                new BigDecimal(2), unitOfMeasureRepository.findByUom("tablespoon").get()));
        guacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced",
                new BigDecimal(2), unitOfMeasureRepository.findByUom("each").get()));
        guacamoleRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2),
                unitOfMeasureRepository.findByUom("tablespoon").get()));
        guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2),
                unitOfMeasureRepository.findByUom("dash").get()));
        guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped",
                new BigDecimal(".5"), unitOfMeasureRepository.findByUom("each").get()));

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




        tacoRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2),
                unitOfMeasureRepository.findByUom("tablespoon").get()));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1),
                unitOfMeasureRepository.findByUom("teaspoon").get()));
        tacoRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1),
                unitOfMeasureRepository.findByUom("teaspoon").get()));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1),
                unitOfMeasureRepository.findByUom("teaspoon").get()));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"),
                unitOfMeasureRepository.findByUom("teaspoon").get()));
        tacoRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1),
                unitOfMeasureRepository.findByUom("each").get()));
        tacoRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1),
                unitOfMeasureRepository.findByUom("tablespoon").get()));
        tacoRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3),
                unitOfMeasureRepository.findByUom("tablespoon").get()));
        tacoRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2),
                unitOfMeasureRepository.findByUom("tablespoon").get()));
        tacoRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4),
                unitOfMeasureRepository.findByUom("tablespoon").get()));
        tacoRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8),
                unitOfMeasureRepository.findByUom("each").get()));
        tacoRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3),
                unitOfMeasureRepository.findByUom("cups").get()));
        tacoRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2),
                unitOfMeasureRepository.findByUom("each").get()));
        tacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4),
                unitOfMeasureRepository.findByUom("each").get()));
        tacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"),
                unitOfMeasureRepository.findByUom("pint").get()));
        tacoRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"),
                unitOfMeasureRepository.findByUom("each").get()));
        tacoRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4),
                unitOfMeasureRepository.findByUom("each").get()));
        tacoRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4),
                unitOfMeasureRepository.findByUom("cups").get()));
        tacoRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4),
                unitOfMeasureRepository.findByUom("each").get()));


        Notes tacoNotes = new Notes();
        tacoNotes.setNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled\n" +
                "jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot\n" +
                "pan on the stove comes wafting through the house.\n");



        tacoRecipe.setNotes(tacoNotes);

        tacoNotes.setRecipe(tacoRecipe);

        recipeRepository.save(tacoRecipe);

        /*------------------------------------------------------------------------------------------------------------*/
        System.out.println("Data Initialized... ");


    }
}
