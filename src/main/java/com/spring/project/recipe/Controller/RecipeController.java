package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
