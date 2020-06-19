package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Category;
import com.spring.project.recipe.commands.CategoryCommand;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandTransformer implements Converter<Category, CategoryCommand> {

    @Override
    @Nullable
    @Synchronized
    public CategoryCommand convert(Category category) {
        if(category == null)
            return null;

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}
