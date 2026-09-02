package com.example.microservice.user.service;

import com.example.microservice.common.abstracts.BaseService;
import com.example.microservice.user.dto.UserRequest;
import com.example.microservice.user.dto.UserResponse;
import com.example.microservice.user.entity.User;

import java.util.UUID;

public interface UserService extends BaseService<User, UUID, UserRequest, UserResponse> {
}