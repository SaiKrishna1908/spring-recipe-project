package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Category;
import com.spring.project.recipe.commands.CategoryCommand;
import com.spring.project.recipe.commands.RecipeCommand;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryTransformer implements Converter<CategoryCommand, Category> {


    @Override
    @Nullable
    @Synchronized
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand==null)
            return null;

        final Category category = new Category();

        category.setId(categoryCommand.getId());

        category.setDescription(categoryCommand.getDescription());

        return category;
    }
}
