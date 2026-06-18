package com.example.RateLimiterProject.Controller;

import com.example.RateLimiterProject.AuthenticationService.AuthService;
import com.example.RateLimiterProject.Entity.User;
import com.example.RateLimiterProject.Security.JwtService;
import com.example.RateLimiterProject.dto.LoginResponseDto;
import com.example.RateLimiterProject.dto.SignInRequest;
import com.example.RateLimiterProject.dto.SignUpRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody SignUpRequest request){
        User user = authService.signup(request);
        return ResponseEntity.ok(user);


    }
    public ResponseEntity<LoginResponseDto> LoginUser(@RequestBody SignInRequest request, HttpServletResponse httpServletResponse){

        String[] arr = authService.signIn(request);
        Cookie cookie = new Cookie("refreshToken",arr[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDto(arr[0]));
    }
}
