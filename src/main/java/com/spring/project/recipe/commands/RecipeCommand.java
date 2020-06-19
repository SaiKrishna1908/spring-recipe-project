package com.spring.project.recipe.commands;

import com.spring.project.recipe.Model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer cookTime;
    private Integer prepTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;

    private NotesCommand notesCommand;
    private Set<IngredientCommand> ingredientCommands = new HashSet<>();
    private Set<CategoryCommand> categoryCommands = new HashSet<>();

}
