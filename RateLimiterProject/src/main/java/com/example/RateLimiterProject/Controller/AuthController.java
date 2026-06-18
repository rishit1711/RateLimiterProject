package com.example.RateLimiterProject.Controller;

import com.example.RateLimiterProject.AuthenticationService.AuthService;
import com.example.RateLimiterProject.Entity.User;
import com.example.RateLimiterProject.Security.JwtService;
import com.example.RateLimiterProject.dto.SignInRequest;
import com.example.RateLimiterProject.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody SignUpRequest request){
        User user = authService.registerUser(request);
        return ResponseEntity.ok(user);


    }
    public ResponseEntity<String> LoginUser(@RequestBody SignInRequest request){

        String token = jwtService.GenerateAccessToken()
    }
}
