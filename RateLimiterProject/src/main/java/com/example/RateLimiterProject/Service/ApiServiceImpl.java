package com.example.RateLimiterProject.Service;

import com.example.RateLimiterProject.Entity.ApiKey;
import com.example.RateLimiterProject.Entity.User;
import com.example.RateLimiterProject.Repository.ApiKeyRepository;
import com.example.RateLimiterProject.dto.ApiKeyResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;



@Service
@RequiredArgsConstructor
@Slf4j
public class ApiServiceImpl implements ApiService {
    private final ApiKeyRepository apiKeyRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiKeyResponseDto GenerateApiKey() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String apiKey = "rr_live_" + UUID.randomUUID().toString().replace("-", "");
        ApiKey apiKey1 =ApiKey.builder()
                        .key(apiKey)
                                .user(user)
                                        .createdAt(LocalDateTime.now())
                                                .rateLimit(50).build();

        apiKeyRepository.save(apiKey1);


        return modelMapper.map(apiKey1,ApiKeyResponseDto.class);





    }
}
