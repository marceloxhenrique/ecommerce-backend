package com.api.ecommerce.dtos;

import java.util.UUID;

import com.api.ecommerce.enums.UserRole;

public record UserAuthDto(UUID id, String username, String email, UserRole role) {

}
