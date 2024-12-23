package com.api.ecommerce.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.ecommerce.models.User;

public interface UserRepository extends JpaRepository<User, UUID>{
  UserDetails findByEmail(String email);
}
