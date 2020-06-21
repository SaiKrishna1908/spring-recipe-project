package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Ingredient;
import com.spring.project.recipe.Service.IngredientService;
import com.spring.project.recipe.Service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService ;
    private  final IngredientService ingredientService;
    @GetMapping
    @RequestMapping(value = "/recipe/{id}/ingredient")
    public String getRecipeIngredient(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.getById(Long.parseLong(id)));
        return "recipe/ingredient/ingredients";
    }

    @GetMapping
    @RequestMapping(value= "recipe/{rid}/ingredient/{id}")
    public String getRecipeIdIngredientId(@PathVariable  String rid, @PathVariable String id, Model model){
            model.addAttribute("ingredient" , ingredientService.findByRecipeId(Long.valueOf(rid), Long.valueOf(id)));
            return "recipe/ingredient/show";
    }
}
