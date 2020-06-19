package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.commands.RecipeCommand;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
//@RequestMapping("recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @RequestMapping(value = "/recipe/show/{id}",method = RequestMethod.GET)
    public String findById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }


    @RequestMapping(value = "recipe/new")
    public String getRecipeCommand(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveRecipe(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/show/"+savedCommand.getId();
    }
}
