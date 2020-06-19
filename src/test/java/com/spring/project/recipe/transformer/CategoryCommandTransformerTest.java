package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.Category;
import com.spring.project.recipe.commands.CategoryCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CategoryCommandTransformerTest {

    CategoryCommandTransformer categoryCommandTransformer;

    @BeforeEach
    void setUp(){
        categoryCommandTransformer= new CategoryCommandTransformer();
    }
    @Test
    void convert() {


        Category category = Category.builder().id(1L).description("Hello world").build();


        CategoryCommand categoryCommand1= categoryCommandTransformer.convert(category);

        assertEquals(category.getId(), categoryCommand1.getId());


    }

    @Test
    void testNullObject() throws Exception{
        assertNull(categoryCommandTransformer.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception{
        assertNotNull(categoryCommandTransformer.convert(new Category()));
    }
}