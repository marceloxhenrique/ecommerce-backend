package com.api.ecommerce.dtos;

import com.api.ecommerce.enums.UserRole;

public record LoginResponseDto(String token, UserRole role) {

}
