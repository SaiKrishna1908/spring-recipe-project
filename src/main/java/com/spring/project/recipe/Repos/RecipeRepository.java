package com.spring.project.recipe.Repos;

import com.spring.project.recipe.Model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
