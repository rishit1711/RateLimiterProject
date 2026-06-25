package com.example.RateLimiterProject.TokenBucket;

import jakarta.persistence.Column;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class TokenBucketFilter extends OncePerRequestFilter {

    private TokenBucketService tokenBucketService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String apiKey = request.getHeader("API-KEY");

        if(apiKey==null || apiKey.isBlank()){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Api Key is Required");


        }
        boolean allowed= tokenBucketService.allowRequest(apiKey);
        if(!allowed){
            response.setStatus(403);
            response.getWriter().write("Too Many Requests!!");
            return;
        }
        filterChain.doFilter(request,response);



    }
}
