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
public class ClientPositionControllerClass {

    @InjectMocks
    private ClientPositionService clientPositionService;

    @Mock
    private ClientPositionRepository clientPositionRepository;

    @Test
    public void getClientPositionSuccess() {

        Mockito.when(clientPositionRepository.findAll()).thenReturn(Stream
                .of(new ClientPosition(10000, "Junior", true, "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new ClientPosition(10001, "Senior", true,"sistem", LocalDateTime.now(), "sistem", LocalDateTime.now() ))
                .collect(Collectors.toList()));

        assertEquals(2, clientPositionService.getClientPosition().size());
    }

    @Test
    public void getClientPositionFailed() {

        Mockito.when(clientPositionRepository.findAll()).thenReturn(Stream
                .of(new ClientPosition(10000, "Junior", true, "sistem", LocalDateTime.now(), "sistem", LocalDateTime.now()),
                        new ClientPosition(10001, "Senior", true,"sistem", LocalDateTime.now(), "sistem", LocalDateTime.now() ))
                .collect(Collectors.toList()));

        assertEquals(3, clientPositionService.getClientPosition().size());
    }
}
