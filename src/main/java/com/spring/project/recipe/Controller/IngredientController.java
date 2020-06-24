package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Service.IngredientService;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.Service.UnitOfMeasureService;
import com.spring.project.recipe.commands.IngredientCommand;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.commands.UnitOfMeasureCommand;
import com.spring.project.recipe.transformer.IngredientTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService ;
    private  final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final IngredientTransformer ingredientTransformer;

    @GetMapping
    @RequestMapping(value = "/recipe/{id}/ingredients")
    public String getRecipeIngredient(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.getById(Long.parseLong(id)));
        return "recipe/ingredient/ingredients";
    }

    @GetMapping
    @RequestMapping(value= "/recipe/{rid}/ingredient/{id}/view")
    public String getRecipeIdIngredientId(@PathVariable  String rid, @PathVariable String id, Model model){
            model.addAttribute("ingredient" , ingredientService.findByRecipeId(Long.valueOf(rid), Long.valueOf(id)));
            return "recipe/ingredient/show";
    }

    @PostMapping
    @RequestMapping("recipe/{rid}/ingredient")
    public String updateRecipeIngredient(@ModelAttribute IngredientCommand ingredientCommand){
//        ingredientCommand.setRecipeId(Long.valueOf(rid));
        IngredientCommand ingredientCommand1 = ingredientService.updateIngredient(ingredientCommand);
//        return "redirect:/recipe/"+ingredientCommand1.getRecipeId()+"/ingredient/"+ingredientCommand1.getId()+"/view";
            return "redirect:/recipe/"+ingredientCommand.getRecipeId()+"/ingredients";
    }

    @GetMapping
    @RequestMapping("/recipe/{rid}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String rid, @PathVariable String id, Model model){
        model.addAttribute("ingredients", ingredientService.findByRecipeId(Long.valueOf(rid),Long.valueOf(id)));
        model.addAttribute("uoms", unitOfMeasureService.getUomList());
        return "recipe/ingredient/IngredientForm";
    }

    @GetMapping
    @RequestMapping("/recipe/{rid}/ingredient/new")
    public String addNewIngredient(@PathVariable String rid, Model model){

        RecipeCommand recipeCommand = recipeService.getById(Long.valueOf(rid));

        //todo add validation for checking if recipe id is valid recipeId

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(rid));
        model.addAttribute("ingredients", ingredientCommand);

        ingredientCommand.setUnitOfMeasureCommand(new UnitOfMeasureCommand());
        model.addAttribute("uoms", unitOfMeasureService.getUomList());

        return "recipe/ingredient/IngredientForm";
    }

}
