package com.spring.project.recipe.Service;

import com.spring.project.recipe.commands.UnitOfMeasureCommand;

import java.util.List;
import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getUomList();
}
