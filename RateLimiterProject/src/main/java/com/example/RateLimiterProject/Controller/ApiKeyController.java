package com.example.RateLimiterProject.Controller;


import com.example.RateLimiterProject.Service.ApiService;
import com.example.RateLimiterProject.dto.ApiKeyResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiKey")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiService apiService;
    private final ModelMapper modelMapper;


    @PostMapping("/generate")
    public ResponseEntity<ApiKeyResponseDto> apiKey(){
        ApiKeyResponseDto dto=apiService.GenerateApiKey();

        return ResponseEntity.ok(dto);

    }

    @GetMapping("/myKeys")
    public ResponseEntity<List<ApiKeyResponseDto>> getKey(){
        return ResponseEntity.ok(apiService.getmyKeys());
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> delKey(@PathVariable Long Id){
        System.out.println("Delete Called");
        apiService.deleteKey(Id);
        return ResponseEntity.noContent().build();
    }


}
