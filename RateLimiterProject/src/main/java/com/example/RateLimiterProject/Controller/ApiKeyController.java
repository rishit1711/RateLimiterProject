package com.example.RateLimiterProject.Controller;


import com.example.RateLimiterProject.Service.ApiService;
import com.example.RateLimiterProject.dto.ApiKeyResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiKey")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiService apiService;
    private final ModelMapper modelMapper;


    @PostMapping("/generate")
    public ResponseEntity<ApiKeyResponseDto> apiKey(){
        ApiKeyResponseDto dto=apiService.GenerateApiKey();



    }
}
