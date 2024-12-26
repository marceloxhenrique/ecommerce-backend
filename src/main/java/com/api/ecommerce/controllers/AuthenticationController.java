package com.api.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ecommerce.dtos.AuthenticationDto;
import com.api.ecommerce.dtos.RegisterDto;
import com.api.ecommerce.models.User;
import com.api.ecommerce.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;
  
  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data){
    var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    this.authenticationManager.authenticate(emailPassword);

    return ResponseEntity.ok("Login successful");
  }

  @PostMapping("/register")
  public ResponseEntity<User> regsiter(@RequestBody @Valid RegisterDto data){
    if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
    
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.username(), data.email(), encryptedPassword, data.role());
    this.userRepository.save(newUser);
    return ResponseEntity.ok().build();
  }
}
