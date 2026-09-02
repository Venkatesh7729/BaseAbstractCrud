package com.example.microservice.user.controller;

import com.example.microservice.common.abstracts.AbstractController;
import com.example.microservice.user.dto.UserRequest;
import com.example.microservice.user.dto.UserResponse;
import com.example.microservice.user.entity.User;
import com.example.microservice.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController
        extends AbstractController<User, UUID, UserRequest, UserResponse> {

    public UserController(UserService userService) {
        super(userService);
    }
}