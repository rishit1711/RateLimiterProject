package com.example.RateLimiterProject.TokenBucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service

public class TokenBucketService {

    private static  Long CAPACITY;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public boolean AllowRequest(String apiKey){

        String redisKey = "bucket :"+ apiKey;
        Bucket bucket =
                (Bucket)
                        redisTemplate.opsForValue().get(redisKey);


        if(bucket==null){

            bucket = new Bucket();
            bucket.setTokens(CAPACITY);
            bucket.setLastRefillTime(Instant.now().getEpochSecond());

        }


        return  false;

    }
}
