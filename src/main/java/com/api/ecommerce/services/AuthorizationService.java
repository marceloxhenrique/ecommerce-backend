package com.api.ecommerce.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.ecommerce.repositories.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService{
  private UserRepository userRepository;

  public AuthorizationService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
