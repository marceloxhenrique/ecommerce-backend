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

import com.api.ecommerce.dtos.LoginDto;
import com.api.ecommerce.dtos.LoginResponseDto;
import com.api.ecommerce.dtos.RegisterDto;
import com.api.ecommerce.dtos.UserAuthDto;
import com.api.ecommerce.enums.UserRole;
import com.api.ecommerce.infra.TokenService;
import com.api.ecommerce.models.User;
import com.api.ecommerce.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid LoginDto data){
    var emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    // User user = (User) this.userRepository.findByEmail(data.email());
    var auth = this.authenticationManager.authenticate(emailPassword);
    var token = tokenService.generateToken((User) auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponseDto(token));
  }
  @PostMapping("/register")
  public ResponseEntity<User> regsiter(@RequestBody @Valid RegisterDto data){
    if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
    UserRole role = data.role().orElse(UserRole.USER);
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data.username(), data.email(), encryptedPassword, role);
    this.userRepository.save(newUser);
    return ResponseEntity.ok().build();
  }
  @PostMapping("/authenticate")
  public ResponseEntity<Object> authenticate(@RequestBody String token) {
    var isUserAuthtenticated = this.tokenService.validateToken(token);
    User user = (User) this.userRepository.findByEmail(isUserAuthtenticated);
    UserAuthDto userData = new UserAuthDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole()); 
    return ResponseEntity.ok(userData);
  }
  
  @PostMapping("/delete")
  public ResponseEntity<Object> deleteAccount(@RequestBody String token) {
    var isUserAuthtenticated = this.tokenService.validateToken(token);
    User user = (User) this.userRepository.findByEmail(isUserAuthtenticated);
    this.userRepository.deleteById(user.getId());  
    return ResponseEntity.ok("User Delete succesfully");
  }
  
}
