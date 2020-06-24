package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.Repos.UnitOfMeasureRepository;
import com.spring.project.recipe.commands.UnitOfMeasureCommand;
import com.spring.project.recipe.transformer.UnitOfMeasureCommandTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private  final UnitOfMeasureCommandTransformer unitOfMeasureCommandTransformer;

    @Override

    public Set<UnitOfMeasureCommand> getUomList() {
        Set<UnitOfMeasureCommand> unitOfMeasures = StreamSupport.stream(
                unitOfMeasureRepository.findAll().spliterator(), false
        ).map(unitOfMeasureCommandTransformer::convert).collect(Collectors.toSet());

        return unitOfMeasures;
    }
}
