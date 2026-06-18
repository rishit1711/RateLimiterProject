package com.example.RateLimiterProject.AuthenticationService;

import com.example.RateLimiterProject.Entity.User;
import com.example.RateLimiterProject.Repository.UserRepository;
import com.example.RateLimiterProject.Security.JwtService;
import com.example.RateLimiterProject.dto.SignInRequest;
import com.example.RateLimiterProject.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;




    @Service
    @RequiredArgsConstructor
    public class AuthService {
        private final UserRepository userRepository;
        private final ModelMapper modelMapper;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;
        public User signup(@RequestBody SignUpRequest requestDto){
            User user = userRepository.findByEmail(requestDto.getEmail());
            if(user!=null){
                throw new RuntimeException("Email Already Exists");
            }
            User newUser = modelMapper.map(requestDto, User.class);

            newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            newUser=userRepository.save(newUser);

            return modelMapper.map(newUser, User.class);
        }
        public String[] signIn(@RequestBody SignInRequest request){


            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

            User user = (User) authentication.getPrincipal();
            String[] arr = new String[2];
            arr[0]= jwtService.GenerateAccessToken(user);
            arr[1]= jwtService.GenerateRefreshToken(user);

            return  arr;

        }

}
