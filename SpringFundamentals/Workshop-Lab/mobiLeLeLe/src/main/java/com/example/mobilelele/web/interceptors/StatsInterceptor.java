package com.example.mobilelele.web.interceptors;

import com.example.mobilelele.services.StatsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class StatsInterceptor implements HandlerInterceptor {

    private StatsService statsService;

    public StatsInterceptor(StatsService statsService) {
        this.statsService = statsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        statsService.onRequest();

        return true;
    }
}
