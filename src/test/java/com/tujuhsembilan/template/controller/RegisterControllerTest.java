package com.tujuhsembilan.template.controller;

import com.tujuhsembilan.template.dto.RegisterDTO;
import com.tujuhsembilan.template.model.Client;
import com.tujuhsembilan.template.model.User;
import com.tujuhsembilan.template.repository.UserRepository;
import com.tujuhsembilan.template.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RegisterControllerTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserClientRepository userClientRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void registerTestSuccess() {

        RegisterDTO request = new RegisterDTO();
        request.setFirstName("namadepan");
        request.setLastName("namabelakang");
        request.setEmail("namanama@gmail.com");
        request.setPassword(bCryptPasswordEncoder.encode("abcd1234"));
        request.setSex("male");
        request.setBirthDate(LocalDate.now());
        request.setClientPositionId(1);
        request.setAgencyName("padepokan");
        request.setAgencyAddress("cicendo");


        String mockEncodedPassword = "mockEncodedPassword";
        when(bCryptPasswordEncoder.encode("abcd1234")).thenReturn(mockEncodedPassword);

        User mockUser = User.builder().userId(1).build();
        Mockito.when(userRepository.save(mockUser)).thenReturn(mockUser);

        Client mockClient = Client.builder().clientId(2).build();
        Mockito.when(clientRepository.save(mockClient)).thenReturn(mockClient);

        assertEquals(request.getFirstName(), userService.createUser(request).getFirstName());
        assertNotNull(userService.createUser(request));
    }

    @Test
    public void registerTestFailed() {

        RegisterDTO request = new RegisterDTO();
        request.setFirstName("namadepan");
        request.setLastName("namabelakang");
        request.setEmail("namanama@gmail.com");
        request.setPassword(bCryptPasswordEncoder.encode("abcd1234"));
        request.setSex("male");
        request.setBirthDate(LocalDate.now());
        request.setClientPositionId(1);
        request.setAgencyName("padepokan");
        request.setAgencyAddress("cicendo");


        String mockEncodedPassword = "mockEncodedPassword";
        when(bCryptPasswordEncoder.encode("abcd1234")).thenReturn(mockEncodedPassword);

        User mockUser = User.builder().userId(1).build();
        Mockito.when(userRepository.save(mockUser)).thenReturn(mockUser);

        Client mockClient = Client.builder().clientId(2).build();
        Mockito.when(clientRepository.save(mockClient)).thenReturn(mockClient);

        assertEquals("siapa", userService.createUser(request).getFirstName());
    }

}
