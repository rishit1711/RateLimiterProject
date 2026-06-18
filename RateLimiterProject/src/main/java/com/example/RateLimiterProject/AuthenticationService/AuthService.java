package com.example.RateLimiterProject.AuthenticationService;

import com.example.RateLimiterProject.Entity.User;
import com.example.RateLimiterProject.Repository.UserRepository;
import com.example.RateLimiterProject.dto.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public void registerUser(SignInRequest request) {
       User user = userRepository.findByEmail(request.getEmail());
       if(user!=null){
           throw new RuntimeException("User Already Exists");
       }

    }
}
