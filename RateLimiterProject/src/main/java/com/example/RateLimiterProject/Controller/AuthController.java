package com.example.RateLimiterProject.Controller;

import com.example.RateLimiterProject.AuthenticationService.AuthService;
import com.example.RateLimiterProject.dto.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    public ResponseEntity<String> registerUser(@RequestBody SignInRequest request){
        authService.registerUser(request);


    }
}
