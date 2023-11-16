package com.tujuhsembilan.template.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SkillsetControllerTest {

    @InjectMocks
    private SkillsetService skillsetService;

    @Mock
    private SkillsetRepository skillsetRepository;

    @Test
    public void getSkillsetSuccess() {
        SkillsetType skillsetType = new SkillsetType();
        List<MostFrequentSkillset> mostFrequentSkillsetList = new ArrayList<MostFrequentSkillset>();

        Mockito.when(skillsetRepository.findAll()).thenReturn(Stream
                .of(new Skillset(10000, skillsetType, "ReactJS", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now(),mostFrequentSkillsetList ),
                        new Skillset(10000, skillsetType, "ReactJS", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now(),mostFrequentSkillsetList ))
                .collect(Collectors.toList()));

        assertEquals(2, skillsetService.getSkillset().size());
    }

    @Test
    public void getSkillsetFailed() {
        SkillsetType skillsetType = new SkillsetType();
        List<MostFrequentSkillset> mostFrequentSkillsetList = new ArrayList<MostFrequentSkillset>();

        Mockito.when(skillsetRepository.findAll()).thenReturn(Stream
                .of(new Skillset(10000, skillsetType, "ReactJS", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now(),mostFrequentSkillsetList ),
                        new Skillset(10000, skillsetType, "ReactJS", "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now(),mostFrequentSkillsetList ))
                .collect(Collectors.toList()));

        assertEquals(23, skillsetService.getSkillset().size());
    }
}
