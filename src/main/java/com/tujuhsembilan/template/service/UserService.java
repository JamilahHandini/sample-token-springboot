package com.tujuhsembilan.template.service;

import com.tujuhsembilan.template.dto.request.SignInDTO;
import com.tujuhsembilan.template.dto.response.SignInResponseDTO;
import com.tujuhsembilan.template.model.*;
import com.tujuhsembilan.template.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private  JwtEncoder encoder;



    private  String RESPONSE_MESSAGE; //dapat menggunakan global exception message

    @Transactional
    public SignInResponseDTO signIn(SignInDTO request){
        SignInResponseDTO data = new SignInResponseDTO();
        try {
            User user = userRepository.findUserByEmail(request.getEmail());
            if (user == null) {
                RESPONSE_MESSAGE = "Email Not Found";
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.RESPONSE_MESSAGE);
            }

            //enkripsi password
            BCryptPasswordEncoder encoderPw = new BCryptPasswordEncoder();
            String encryptedPasswordInput = user.getPassword();
            if(user.getPassword()==null){
                RESPONSE_MESSAGE = "Password Do Not Empty";
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.RESPONSE_MESSAGE);
            }

            String password = request.getPassword();
            if(request.getPassword()==null){
                RESPONSE_MESSAGE = "Password Do Not Empty";
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.RESPONSE_MESSAGE);
            }

            //Check apakah password sesuai
            if(encoderPw.matches(password, encryptedPasswordInput)){
                Instant now = Instant.now();
                long expiry = 36000L;

                JwtClaimsSet claims = JwtClaimsSet
                        .builder()
                        .issuer("self")
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(expiry))
                        .subject(user.getFirstName())
                        .build();
                String token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
                data.setUsername(user.getFirstName());
                data.setToken(token);
                return data;
            }else{
                RESPONSE_MESSAGE = "Password Incorrect";
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.RESPONSE_MESSAGE);
            }

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.RESPONSE_MESSAGE + " on Data: Sign In User");
        }

    }



}
