package com.quizmaker.controllers;

import com.quizmaker.models.dtos.AuthenticationResponse;
import com.quizmaker.models.dtos.RegisterAuthenticateDTO;
import com.quizmaker.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterAuthenticateDTO request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login (@RequestBody RegisterAuthenticateDTO request) {
        return userService.login(request);
    }
}
