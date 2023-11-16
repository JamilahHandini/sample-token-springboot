package com.tujuhsembilan.template.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;




@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AutocompleteSearchControllerTest {

    @InjectMocks
    private AutocompleteSearchService service;

    @Mock
    private MostFrequentSkillsetRepository mostFrequentSkillsetRepository;
    @Mock
    private SkillsetRepository skillsetRepository;
    @Mock
    private SkillsetTypeRepository skillsetTypeRepository;

    @Test
    public void searchPopularTagsSuccess() {
        Skillset skillset = new Skillset();

        Mockito.when(mostFrequentSkillsetRepository.findAll()).thenReturn(Stream
                .of(new MostFrequentSkillset(10000, skillset, 31, "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new MostFrequentSkillset(10001, skillset, 35, "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()))
                .collect(Collectors.toList()));

        assertEquals(2, service.searchPopularTags().size());
    }

    @Test
    public void searchPopularTagsFailed() {
        Skillset skillset = new Skillset();

        Mockito.when(mostFrequentSkillsetRepository.findAll()).thenReturn(Stream
                .of(new MostFrequentSkillset(10000, skillset, 31, "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new MostFrequentSkillset(10001, skillset, 35, "active", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()))
                .collect(Collectors.toList()));

        assertEquals(25, service.searchPopularTags().size());
    }

    @Test
    public void searchTagsSuccess() {
        SkillsetType skillsetType = new SkillsetType();
        List<MostFrequentSkillset> mostFrequentSkillsetList = new ArrayList<MostFrequentSkillset>();

        Mockito.when(skillsetRepository.findAll()).thenReturn(Stream
                .of(new Skillset(1000, skillsetType, "ReactJs", "active", "sistem",LocalDateTime.now(), "sistem", LocalDateTime.now(), mostFrequentSkillsetList),
                       new Skillset(1000, skillsetType, "ReactJs", "active", "sistem",LocalDateTime.now(), "sistem", LocalDateTime.now(), mostFrequentSkillsetList))
                .collect(Collectors.toList()));

        assertEquals(2, service.searchTags().size());
    }

    @Test
    public void searchTagsFailed() {
        SkillsetType skillsetType = new SkillsetType();
        List<MostFrequentSkillset> mostFrequentSkillsetList = new ArrayList<MostFrequentSkillset>();

        Mockito.when(skillsetRepository.findAll()).thenReturn(Stream
                .of(new Skillset(1000, skillsetType, "ReactJs", "active", "sistem",LocalDateTime.now(), "sistem", LocalDateTime.now(), mostFrequentSkillsetList),
                        new Skillset(1000, skillsetType, "ReactJs", "active", "sistem",LocalDateTime.now(), "sistem", LocalDateTime.now(), mostFrequentSkillsetList))
                .collect(Collectors.toList()));

        assertEquals(5, service.searchTags().size());
    }


}
