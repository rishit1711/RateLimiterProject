package com.example.RateLimiterProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {

    private static final int LIMIT = 10;
    private static final long WINDOW_SECONDS = 60;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean allowRequest(String apiKey) {

        String redisKey = "rate_limit:" + apiKey;

        Long count = redisTemplate.opsForValue().increment(redisKey);

        // First request
        if (count != null && count == 1) {
            redisTemplate.expire(
                    redisKey,
                    Duration.ofSeconds(WINDOW_SECONDS)
            );
        }
        System.out.println("API KEY = " + apiKey);
        System.out.println("COUNT = " + count);
        // checks redis count and matches with limit
        return count != null && count <= LIMIT;
    }
}