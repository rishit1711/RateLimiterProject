package com.example.RateLimiterProject.TokenBucket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Bucket implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long tokens;
    private long lastRefillTime;

}
