package com.example.RateLimiterProject.Repository;

import com.example.RateLimiterProject.Entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey,Long> {


}
