package com.example.RateLimiterProject.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true,nullable = false)
    private String key;
    private boolean isActive;
    @Column(nullable = false)
    private Integer rateLimit;
    @Column(nullable = false)
    private RateLimitType Strategy;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_Api_Key")
    private User user;
}
