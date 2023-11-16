package com.tujuhsembilan.template.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TalentLevelControllerTest {

    @InjectMocks
    private TalentLevelService talentLevelService;

    @Mock
    private TalentLevelRepository talentLevelRepository;

    @Test
    public void getTalentLevelSuccess() {

        Mockito.when(talentLevelRepository.findAll()).thenReturn(Stream
                .of(new TalentLevel(10000, "Junior", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new TalentLevel(10001, "Senior", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now() ))
                .collect(Collectors.toList()));

        assertEquals(2, talentLevelService.getTalentLevel().size());
    }

    @Test
    public void getTalentLevelFailed() {

        Mockito.when(talentLevelRepository.findAll()).thenReturn(Stream
                .of(new TalentLevel(10000, "Junior", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new TalentLevel(10001, "Senior", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now() ))
                .collect(Collectors.toList()));

        assertEquals(24, talentLevelService.getTalentLevel().size());
    }

}
