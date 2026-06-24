package com.example.RateLimiterProject.Redis;

import com.example.RateLimiterProject.Service.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
@RequiredArgsConstructor
public class RateLimitingFilter extends OncePerRequestFilter {
    private final RateLimiterService rateLimiterService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String ApiKey = request.getHeader("API-KEY");

        //api key missing
        if(ApiKey==null || ApiKey.isBlank()){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Api Key is Required");
            return;

        }

        boolean allowed = rateLimiterService.allowRequest(ApiKey);
        if (!allowed) {
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded");
            return;
        }

        filterChain.doFilter(request,response);




    }
}
