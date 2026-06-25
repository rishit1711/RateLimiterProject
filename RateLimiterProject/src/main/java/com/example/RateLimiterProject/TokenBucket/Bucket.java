package com.example.RateLimiterProject.TokenBucket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Bucket {

    private Long tokens;
    private long lastRefillTime;

}
