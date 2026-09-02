package com.example.microservice.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(UUID id, String firstName, String lastName, String email, UUID tenantId, UUID departmentId,
      String status, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy, Long version) {

}