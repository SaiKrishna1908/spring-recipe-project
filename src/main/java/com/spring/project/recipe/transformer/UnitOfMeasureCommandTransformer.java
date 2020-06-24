package com.spring.project.recipe.transformer;

import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.commands.UnitOfMeasureCommand;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UnitOfMeasureCommandTransformer implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    @Synchronized
    @Nullable
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure == null)
            return null;

        final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(unitOfMeasure.getId());
        unitOfMeasureCommand.setUom(unitOfMeasure.getUom());

        return unitOfMeasureCommand;
    }
}
