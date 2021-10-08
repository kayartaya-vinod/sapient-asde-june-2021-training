package com.kvinod;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null) {
            try {
                String token = authHeader.split(" ")[1];
                log.info("token is {}", token);
                String userId = JwtUtil.verify(token);
                req.setAttribute("userId", userId);
                log.info("id is {}", userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }

}
