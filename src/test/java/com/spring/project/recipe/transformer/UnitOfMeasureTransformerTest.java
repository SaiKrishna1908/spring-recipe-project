package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.commands.UnitOfMeasureCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureTransformerTest {

    Long ID = 1L;
    String uom = "tablespoon";

    UnitOfMeasureTransformer unitOfMeasureTransformer;
    @BeforeEach
    void setUp() {
        unitOfMeasureTransformer = new UnitOfMeasureTransformer();
    }

    @Test
    void convert() {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setUom(uom);

        UnitOfMeasure unitOfMeasure = unitOfMeasureTransformer.convert(unitOfMeasureCommand);

        assertNotNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(uom, unitOfMeasure.getUom());
    }

    @Test
    void testNullObject(){
        assertNull(unitOfMeasureTransformer.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(unitOfMeasureTransformer.convert(new UnitOfMeasureCommand()));
    }
}