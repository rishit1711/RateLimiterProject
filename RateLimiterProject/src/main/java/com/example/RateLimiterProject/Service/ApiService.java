package com.example.RateLimiterProject.Service;

import com.example.RateLimiterProject.dto.ApiKeyResponseDto;

import java.util.List;

public interface ApiService {


    ApiKeyResponseDto GenerateApiKey();

    List<ApiKeyResponseDto> getmyKeys();

    void deleteKey(Long id);
}
