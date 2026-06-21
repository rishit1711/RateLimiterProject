package com.example.RateLimiterProject.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ApiKeyResponseDto {

    private Long id;

    private String apiKey;

    private Integer rateLimit;

    private Boolean active;
}
