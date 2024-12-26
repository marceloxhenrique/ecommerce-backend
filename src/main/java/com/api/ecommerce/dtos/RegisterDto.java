package com.api.ecommerce.dtos;

import com.api.ecommerce.enums.UserRole;

public record RegisterDto(String username, String email, String password, UserRole role) {}
