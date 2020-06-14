package com.spring.project.recipe.Model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }
    @Test
    void getId() {
        Long id = 4l;
        category.setId(id);
       assertEquals(id, category.getId());
    }

    @Test
    void getDescription() {
        String description = "Hello world";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }


}