package com.tujuhsembilan.template.controller;

import com.tujuhsembilan.library.service.MinioSrvc;
import com.tujuhsembilan.template.dto.CreateTalentDTO;
import com.tujuhsembilan.template.dto.DisplayTalentDTO;
import com.tujuhsembilan.template.dto.TalentSearchBody;
import com.tujuhsembilan.template.model.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TalentControllerTest {

    @InjectMocks
    private TalentService talentService;

    @Mock
    private TalentLevelRepository talentLevelRepository;

    @Mock
    private EmployeeStatusRepository employeeStatusRepository;

    @Mock
    private TalentStatusRepository talentStatusRepository;

    @Mock
    private MinioSrvc minio;


    @Mock
    private TalentRepository talentRepository;

    @Mock
    private SkillsetRepository skillsetRepository;

    @Mock
    private TalentSkillsetRepository talentSkillsetRepository;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private TalentPositionRepository talentPositionRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTalentTestSuccess() {

        MockMultipartFile photo = new MockMultipartFile(
                "file",                   // Parameter name
                "photo.txt",           // Original file name
                "text/plain",             // Content type
                "file content".getBytes() // Content bytes
        );

        MockMultipartFile cv = new MockMultipartFile(
                "file",                   // Parameter name
                "cv.txt",           // Original file name
                "text/plain",             // Content type
                "file content".getBytes() // Content bytes
        );

        CreateTalentDTO request = new CreateTalentDTO();
        request.setTalentName("Ucup");
        request.setNip("201511045");
        request.setSex("M");
        request.setDob(LocalDate.EPOCH);
        request.setTalentDescription("aku talent");
        request.setExperience(2);
        request.setEmail("ucup@gmail.com");
        request.setCellphone("089876578897");
        request.setVideoUrl("linkVideo");
        request.setCv(cv);
        request.setTalentPhoto(photo);
        request.setLevelId(1);
        request.setEmployeeStatusId(1);

        TalentLevel mockTalentLevel = new TalentLevel();
        EmployeeStatus mockEmployeeStatus = new EmployeeStatus();
        TalentStatus mockTalentStatus = new TalentStatus();

        Mockito.when(talentLevelRepository.findByTalentLevelId(anyInt())).thenReturn(mockTalentLevel);
        Mockito.when(employeeStatusRepository.findByEmployeeStatusId(anyInt())).thenReturn(mockEmployeeStatus);
        Mockito.when(talentStatusRepository.findByTalentStatusId(anyInt())).thenReturn(mockTalentStatus);

        Talent result = talentService.createTalent(request);

        assertNotNull(result);
    }

    @Test
    public void testGetAllTalentDataSuccess() {
        Talent mockTalent = new Talent();
        mockTalent.setTalentId(1);
        mockTalent.setTalentName("Jujun");
        mockTalent.setTalentLevel(TalentLevel.builder().talentLevelId(1).build());
        mockTalent.setEmployeeStatus(EmployeeStatus.builder().employeeStatusId(1).build());
        mockTalent.setTalentStatus(TalentStatus.builder().talentStatusId(1).build());

        Mockito.when(talentRepository.findAll()).thenReturn(Arrays.asList(mockTalent));

        List<DisplayTalentDTO> result = talentService.getAllTalentData();

        assertEquals(1, result.size());
    }

    @Test
    public void testSearchTalentsByNameSuccess() {

        List<TalentPosition> talentPositions = new ArrayList<>();
        List<TalentSkillset> talentSkillsets = new ArrayList<>();
        List<TalentWishlist> talentWishlist = new ArrayList<>();



        Talent talent1 = new Talent(10000,
                TalentLevel.builder().talentLevelId(1).build(),
                TalentStatus.builder().talentStatusId(1).build(),
                EmployeeStatus.builder().employeeStatusId(1).build(),
                "John",
                "photo_url",
                "201511045",
                "M", LocalDate.now(),
                "aku talent",
                "cv_url",
                2,
                "john@gmail.com",
                "087867676767",
                "video_url",
                10,
                true,
                "sistem",
                LocalDateTime.now(),
                "sistem",
                LocalDateTime.now(),
                "aku talent",
                2,
                "089067676767",
                talentPositions,
                talentSkillsets,
                talentWishlist);

        Talent talent2 = new Talent(10001,
                TalentLevel.builder().talentLevelId(1).build(),
                TalentStatus.builder().talentStatusId(1).build(),
                EmployeeStatus.builder().employeeStatusId(1).build(),
                "Jaka",
                "photo_url",
                "201511045",
                "M", LocalDate.now(),
                "aku talent",
                "cv_url",
                2,
                "john@gmail.com",
                "087867676767",
                "video_url",
                10,
                true,
                "sistem",
                LocalDateTime.now(),
                "sistem",
                LocalDateTime.now(),
                "aku talent",
                2,
                "089067676767",
                talentPositions,
                talentSkillsets,
                talentWishlist);



        TalentSearchBody searchRequest = new TalentSearchBody();
        searchRequest.setTalentName("John");
        searchRequest.setSortBy("newest");
        Pageable pageable = PageRequest.of(0, 10);

        Page<Talent> mockTalentPage = new PageImpl<>(Arrays.asList(talent1));

        Mockito.when(talentRepository.findByTalentNameContainingIgnoreCase(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(mockTalentPage);

        Page<DisplayTalentDTO> result = talentService.searchTalents(searchRequest, pageable);


        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

}
