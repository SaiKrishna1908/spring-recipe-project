package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Category;
import com.spring.project.recipe.commands.CategoryCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTransformerTest {


    CategoryTransformer categoryTransformer;

    @BeforeEach
    void setUp() {
        categoryTransformer = new CategoryTransformer();
    }

    @Test
    void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription("Test CategoryTransformer");
        categoryCommand.setId(1L);
        //when
        Category category = categoryTransformer.convert(categoryCommand);

        assertEquals(categoryCommand.getId(), category.getId());


    }
    @Test
    void nullTest() throws Exception{
        assertNull(categoryTransformer.convert(null));
    }

    @Test
    void emptyTest() throws  Exception{
        assertNotNull(categoryTransformer.convert(new CategoryCommand()));
    }
}