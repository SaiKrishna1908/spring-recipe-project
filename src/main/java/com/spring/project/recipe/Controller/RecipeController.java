package com.spring.project.recipe.Controller;

import com.spring.project.recipe.Model.Recipe;
import com.spring.project.recipe.Service.RecipeService;
import com.spring.project.recipe.commands.RecipeCommand;
import com.spring.project.recipe.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@Slf4j
//@RequestMapping("recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    @RequestMapping(value = "/recipe/{id}/show", method = RequestMethod.GET)
    public String findById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping(value = "/recipe/new")
    public String getRecipeCommand(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveRecipe(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
            });
            return "recipe/recipeform";
        }
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping(value = "recipe/{id}/update")
    public String updateRecipeCommand(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.getById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipeCommand(@PathVariable String id) {
        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    ModelAndView exceptionNotFound(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        log.error("Not Found Exception");
        modelAndView.setViewName("error/404NotFound");
        modelAndView.addObject("exception",e);
        return modelAndView;
    }

}
