package com.example.RateLimiterProject.Service;

import com.example.RateLimiterProject.dto.ApiKeyResponseDto;

public interface ApiService {


    ApiKeyResponseDto GenerateApiKey();
}
