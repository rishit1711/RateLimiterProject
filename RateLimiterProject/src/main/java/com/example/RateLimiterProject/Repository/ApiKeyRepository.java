package com.example.RateLimiterProject.Repository;

import com.example.RateLimiterProject.Entity.ApiKey;
import com.example.RateLimiterProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey,Long> {


    List<ApiKey> findByUser(User user);
}
