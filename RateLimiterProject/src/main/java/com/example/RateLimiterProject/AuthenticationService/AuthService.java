package com.example.RateLimiterProject.AuthenticationService;

import com.example.RateLimiterProject.Entity.User;
import com.example.RateLimiterProject.Repository.UserRepository;
import com.example.RateLimiterProject.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public User registerUser(SignUpRequest request) {
       User user = userRepository.findByEmail(request.getEmail());
       if(user!=null){
           throw new IllegalCallerException("User Already Exists");
       }
       return modelMapper.map(request,User.class);

    }

}
