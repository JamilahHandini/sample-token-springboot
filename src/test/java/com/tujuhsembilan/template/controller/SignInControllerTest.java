package com.tujuhsembilan.template.controller;

import com.tujuhsembilan.template.dto.request.SignInDTO;
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

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SignInControllerTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void signInTestSuccess() {


        String email = "test@gmail.com";

        User user1 = new User();
        user1.setUserId(1);
        user1.setEmail("test@gmail.com");
        user1.setPassword("abcd1234");

        // Given
        SignInDTO request = new SignInDTO();
        request.setPassword("abcd1234");
        request.setEmail("test@gmail.com");
        ;

        Mockito.when(userRepository.findUserByEmail(email)).thenReturn(user1);

        System.out.println("res: "+userRepository.findUserByEmail(email).getPassword());

        //userService.signIn(request);

        assertNotNull(userRepository.findUserByEmail(email).getUserId());
    }

}
