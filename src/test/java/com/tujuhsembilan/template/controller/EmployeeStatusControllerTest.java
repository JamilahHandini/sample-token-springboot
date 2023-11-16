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
public class EmployeeStatusControllerTest {


    @InjectMocks
    private EmployeeStatusService employeeStatusService;

    @Mock
    private EmployeeStatusRepository employeeStatusRepository;

    @Test
    public void getEmployeeStatusSuccess() {

        Mockito.when(employeeStatusRepository.findAll()).thenReturn(Stream
                .of(new EmployeeStatus(10000, "Junior", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new EmployeeStatus(10001, "Senior", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now() ))
                .collect(Collectors.toList()));

        assertEquals(2, employeeStatusService.getEmployeeStatus().size());
    }

    @Test
    public void getEmployeeStatusFailed() {

        Mockito.when(employeeStatusRepository.findAll()).thenReturn(Stream
                .of(new EmployeeStatus(10000, "Junior", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new EmployeeStatus(10001, "Senior", "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now() ))
                .collect(Collectors.toList()));

        assertEquals(9, employeeStatusService.getEmployeeStatus().size());
    }

}
