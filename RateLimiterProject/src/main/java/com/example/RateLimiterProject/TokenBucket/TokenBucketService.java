package com.example.RateLimiterProject.TokenBucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service

public class TokenBucketService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public boolean AllowRequest(String apiKey){
        return  false;

    }
}
