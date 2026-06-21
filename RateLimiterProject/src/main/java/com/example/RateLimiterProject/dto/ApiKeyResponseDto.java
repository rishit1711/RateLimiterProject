package com.example.RateLimiterProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiKeyResponseDto {

    private Long id;

    private String apiKey;

    private Integer rateLimit;

    private Boolean isActive;
}
