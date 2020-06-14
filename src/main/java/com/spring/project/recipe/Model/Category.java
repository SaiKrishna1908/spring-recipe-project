package com.spring.project.recipe.Model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(exclude = "recipes")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();


    public Set<Recipe> getRecipeSet() {
        return recipes;
    }

    public void setRecipeSet(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Category;
    }

}
