package com.example.RateLimiterProject.TokenBucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service

public class TokenBucketService {

    private static  Long CAPACITY = 5L;
    private static final long REFILL_RATE = 0;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public boolean allowRequest(String apiKey) {

        String redisKey = "bucket:" + apiKey;

        Bucket bucket = (Bucket) redisTemplate
                .opsForValue()
                .get(redisKey);
        System.out.println("Bucket from Redis = " + bucket);

        if (bucket == null) {
            System.out.println("New Bucket Created");

            bucket = new Bucket();

            bucket.setTokens(CAPACITY);

            bucket.setLastRefillTime(
                    Instant.now().getEpochSecond());

        }

        long currentTime = Instant.now().getEpochSecond();

        long elapsedTime =
                currentTime - bucket.getLastRefillTime();

        long tokensToAdd =
                elapsedTime * REFILL_RATE;

        bucket.setTokens(
                Math.min(
                        CAPACITY,
                        bucket.getTokens() + tokensToAdd));

        bucket.setLastRefillTime(currentTime);

        if (bucket.getTokens() > 0) {

            bucket.setTokens(
                    bucket.getTokens() - 1);

            redisTemplate
                    .opsForValue()
                    .set(redisKey, bucket);

            return true;
        }
        redisTemplate.opsForValue().set(redisKey, bucket);

        return false;
    }
}
