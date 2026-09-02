package com.example.microservice.user.dto;

import java.util.UUID;

public record UserRequest(String firstName, String lastName, String email, UUID departmentId, String status) {

}