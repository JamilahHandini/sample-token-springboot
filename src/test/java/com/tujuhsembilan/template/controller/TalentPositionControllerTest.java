package com.tujuhsembilan.template.controller;

import com.tujuhsembilan.template.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TalentPositionControllerTest {

    @InjectMocks
    private TalentPositionService talentPositionService;

    @Mock
    private PositionRepository positionRepository;

    @Test
    public void getTalentPositionSuccess() {
        List<TalentPosition> talentPosition = new ArrayList<>();


        Mockito.when(positionRepository.findAll()).thenReturn(Stream
                .of(new Position(10000, "HRD", true ,  "sistem", LocalDate.now(), "sistem", LocalDate.now(), talentPosition),
                        new Position(10001, "Manager", true ,  "sistem", LocalDate.now(), "sistem", LocalDate.now(), talentPosition))
                .collect(Collectors.toList()));

        assertEquals(2, talentPositionService.getAllPosition().size());
    }

    @Test
    public void getTalentPositionFailed() {
        List<TalentPosition> talentPosition = new ArrayList<>();


        Mockito.when(positionRepository.findAll()).thenReturn(Stream
                .of(new Position(10000, "HRD", true ,  "sistem", LocalDate.now(), "sistem", LocalDate.now(), talentPosition),
                        new Position(10001, "Manager", true ,  "sistem", LocalDate.now(), "sistem", LocalDate.now(), talentPosition))
                .collect(Collectors.toList()));

        assertEquals(5, talentPositionService.getAllPosition().size());
    }
}
