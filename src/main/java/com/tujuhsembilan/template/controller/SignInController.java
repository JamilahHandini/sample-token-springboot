package com.tujuhsembilan.template.controller;

import com.tujuhsembilan.template.dto.request.SignInDTO;
import com.tujuhsembilan.template.dto.response.ResponseGlobal;
import com.tujuhsembilan.template.dto.response.SignInResponseDTO;
import com.tujuhsembilan.template.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignInController {

    @Autowired
    private UserService userService;


    // request : [email : sampleemail@gmail.com, password: password123]
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseGlobal> signInUser(@RequestBody @Valid SignInDTO request) {
        try {
            SignInResponseDTO data = userService.signIn(request);
            ResponseGlobal response = new ResponseGlobal(HttpStatus.OK.value(), "Successfully Login",data);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ResponseGlobal response = new ResponseGlobal(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

}
