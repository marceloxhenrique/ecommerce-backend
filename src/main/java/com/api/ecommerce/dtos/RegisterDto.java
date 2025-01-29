package com.api.ecommerce.dtos;

import java.util.Optional;

import com.api.ecommerce.enums.UserRole;

public record RegisterDto(String username, String email, String password, Optional<UserRole> role) {}
