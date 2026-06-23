package com.example.RateLimiterProject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true,nullable = false)
    private String key;
    private boolean active;
    @Column(nullable = false)
    private Integer rateLimit;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_Api_Key")
    private User user;
}
