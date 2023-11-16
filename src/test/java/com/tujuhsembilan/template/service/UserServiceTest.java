package com.tujuhsembilan.template.service;

import com.tujuhsembilan.template.dto.RegisterDTO;
import com.tujuhsembilan.template.model.*;
import com.tujuhsembilan.template.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { UserServiceTest.class })
class UserServiceTest {
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

    @InjectMocks
    private UserService userService;
    @Mock
    private RegisterDTO user;

    @Test
    void testCreateUser() {
        RegisterDTO request = new RegisterDTO();
        request.setFirstName("namadepan");
        request.setLastName("namabelakang");
        request.setEmail("namanama@gmail.com");
        request.setPassword("abcd1234");
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
        System.out.println("req: " + request);
//        assertEquals(request.getFirstName(), userService.createUser(request).getFirstName());


        assertNotNull(userService.createUser(request));
//        userService = new UserService();
//
//        user = new RegisterDTO();
//        user.setFirstName("front");
//        user.setLastName("back");
//        user.setEmail("ab@mail.com");
//        user.setPassword("1234abcd");
//        user.setSex("p");
//        user.setBirthDate(LocalDate.parse("2000-01-01"));
//        user.setAgencyAddress("jl jl");
//        user.setAgencyName("pt");
//        user.setClientPositionId(1);
//
//        User savedUser = User.builder()
//                .email(user.getEmail())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .password(user.getPassword())
//                .isActive(true)
//                .createdBy(user.getEmail())
//                .createdTime(null)
//                .build();
//
//        User emailExisted = userRepository.findUserByEmail(user.getEmail());
////        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//
//        Mockito.lenient().when(userRepository.findUserByEmail(user.getEmail())).thenReturn(emailExisted);
////        Mockito.when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
//        Mockito.lenient().when(userRepository.save(savedUser)).thenReturn(savedUser);
//
//        Client savedClient = Client.builder()
//                .email(user.getEmail())
//                .clientName(user.getFirstName()+user.getLastName())
//                .sex(user.getSex())
//                .birthDate(user.getBirthDate())
//                .agencyName(user.getAgencyName())
//                .agencyAddress(user.getAgencyAddress())
//                .isActive(true)
//                .createdBy(user.getEmail())
//                .createdTime(null) // Mock LocalDateTime as needed
//                .build();
//
//        Mockito.lenient().when(clientRepository.save(savedClient)).thenReturn(savedClient);
//
//        UserClientKey userClientKey = new UserClientKey(savedUser.getUserId(), savedClient.getClientId());
//        UserClient savedUserClient = UserClient.builder()
//                .id(userClientKey)
//                .user(savedUser)
//                .client(savedClient)
//                .createdBy("system")
//                .createdTime(LocalDateTime.now())
//                .build();
//
//        Mockito.lenient().when(userClientRepository.save(savedUserClient)).thenReturn(savedUserClient);
//
//        UserRoleKey userRoleKey = new UserRoleKey(savedUser.getUserId(), 1);
//        UserRole savedUserRole = UserRole.builder()
//                .id(userRoleKey)
//                .user(savedUser)
//                .createdBy("system")
//                .createdTime(LocalDateTime.now())
//                .build();
//
//        Mockito.lenient().when(userRoleRepository.save(savedUserRole)).thenReturn(savedUserRole);
//
//        assertEquals(user.getFirstName(), userService.createUser(user).getFirstName());
//        assertNotNull(userService.createUser(user));
    }

    @Test
    void testPasswordLength(){
        int passwordLength;
        user = new RegisterDTO();
        user.setPassword("abcd1234");

        passwordLength = user.getPassword().length();
        assertEquals(8, passwordLength);
    }

}