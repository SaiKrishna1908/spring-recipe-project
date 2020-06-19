package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.commands.UnitOfMeasureCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandTransformerTest {

    UnitOfMeasureCommandTransformer unitOfMeasureCommandTransformer;
    @BeforeEach
    void setUp() {
        unitOfMeasureCommandTransformer = new UnitOfMeasureCommandTransformer();
    }

    @Test
    void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasure.setUom("tablespoon");

        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureCommandTransformer.convert(unitOfMeasure);

        assertNotNull(unitOfMeasureCommand);
        assertEquals(unitOfMeasure.getId(), unitOfMeasureCommand.getId());
        assertEquals(unitOfMeasure.getUom(), unitOfMeasureCommand.getUom());
    }

    @Test
    void testNullObject(){
        assertNull(null);
    }

    @Test
    void testEmptyObject(){
        assertNotNull(new UnitOfMeasure());
    }
}