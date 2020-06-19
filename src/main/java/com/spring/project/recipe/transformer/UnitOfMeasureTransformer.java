package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.commands.UnitOfMeasureCommand;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureTransformer implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    @Synchronized
    @Nullable
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand == null)
        return null;

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(unitOfMeasureCommand.getId());
        unitOfMeasure.setUom(unitOfMeasureCommand.getUom());

        return unitOfMeasure;
    }
}
