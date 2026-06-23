package com.example.RateLimiterProject.Service;

import com.example.RateLimiterProject.Entity.ApiKey;
import com.example.RateLimiterProject.Entity.User;
import com.example.RateLimiterProject.Exceptions.ResourceNotFoundException;
import com.example.RateLimiterProject.Exceptions.UnauthorizedException;
import com.example.RateLimiterProject.Repository.ApiKeyRepository;
import com.example.RateLimiterProject.dto.ApiKeyResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

        apiKey1.setActive(true);

        apiKeyRepository.save(apiKey1);


        return modelMapper.map(apiKey1,ApiKeyResponseDto.class);





    }

    @Override
    public List<ApiKeyResponseDto> getmyKeys() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ApiKey> keys = apiKeyRepository.findByUser(user);
        return keys.stream()
                .map(element ->modelMapper.map(element, ApiKeyResponseDto.class)).toList();
    }

    @Override
    public void deleteKey(Long id) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApiKey key = apiKeyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("The given key does not exist with Id : "+id ));
        if(!key.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("Not Authorized to Delete the given Key");
        }
        key.setActive(false);
        apiKeyRepository.save(key);
        return;
    }
}
