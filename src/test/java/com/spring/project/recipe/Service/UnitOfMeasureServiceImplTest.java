package com.spring.project.recipe.Service;

import com.spring.project.recipe.Model.UnitOfMeasure;
import com.spring.project.recipe.Repos.UnitOfMeasureRepository;
import com.spring.project.recipe.commands.UnitOfMeasureCommand;
import com.spring.project.recipe.transformer.UnitOfMeasureCommandTransformer;
import com.spring.project.recipe.transformer.UnitOfMeasureTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;


    private UnitOfMeasureCommandTransformer unitOfMeasureCommandTransformer  = new UnitOfMeasureCommandTransformer();

    private UnitOfMeasureService unitOfMeasureService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureCommandTransformer);
    }

    @Test
    void getUomList() throws Exception {

        //given
        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        unitOfMeasure1.setUom("tb");

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        unitOfMeasure2.setUom("ts");

        UnitOfMeasure unitOfMeasure3 = new UnitOfMeasure();
        unitOfMeasure3.setId(3L);
        unitOfMeasure3.setUom("each");

        UnitOfMeasure unitOfMeasure4 = new UnitOfMeasure();
        unitOfMeasure4.setId(4L);
        unitOfMeasure4.setUom("dash");

        Set<UnitOfMeasure> uoms = new HashSet<>();
        uoms.add(unitOfMeasure1);
        uoms.add(unitOfMeasure2);
        uoms.add(unitOfMeasure3);
        uoms.add(unitOfMeasure4);

        //when
        Iterable<UnitOfMeasure> iterable =uoms;

        when(unitOfMeasureRepository.findAll()).thenReturn(iterable);

        Set<UnitOfMeasureCommand> unitOfMeasureCommands = unitOfMeasureService.getUomList();


        assertEquals(uoms.size(), unitOfMeasureCommands.size());
    }
}