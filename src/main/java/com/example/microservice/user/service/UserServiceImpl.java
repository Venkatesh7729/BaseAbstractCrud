package com.example.microservice.user.service;

import com.example.microservice.common.abstracts.AbstractService;
import com.example.microservice.common.exception.BadRequestException;
import com.example.microservice.user.dto.UserRequest;
import com.example.microservice.user.dto.UserResponse;
import com.example.microservice.user.entity.User;
import com.example.microservice.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends AbstractService<User, UUID, UserRequest, UserResponse> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository, "User");
        this.userRepository = userRepository;
    }

    @Override
    protected User toEntity(UserRequest dto) {

        User user = new User();

        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setDepartmentId(dto.departmentId());
        user.setStatus(dto.status());

        return user;
    }

    @Override
    protected UserResponse toDto(User user) {

        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getTenantId(),
                user.getDepartmentId(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getCreatedBy(),
                user.getUpdatedBy(),
                user.getVersion()
        );
    }

    @Override
    protected void updateEntityFromDto(User user, UserRequest dto) {

        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setDepartmentId(dto.departmentId());
        user.setStatus(dto.status());
    }


}