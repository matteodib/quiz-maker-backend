package com.quizmaker.controllers;

import com.quizmaker.models.dtos.IsUserLoggedDTO;
import com.quizmaker.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/is-logged")
    public Boolean isUserLogged(@RequestBody IsUserLoggedDTO request) {
        return userService.isUserLogged(request);
    }
}
