package com.sapient.asde.batch5.customerservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@WebFilter(urlPatterns = "/")
@Order(1)
public class ClaimsFilter implements Filter {

  @Value("${authVerifyUrl}")
  String authVerifyUrl;

  @Autowired
  RestTemplate template;

  @Autowired
  TypeReference<HashMap<String, Object>> typeRef;

  @Autowired
  ObjectMapper om;

  @Autowired
  private CircuitBreakerFactory circuitBreakerFactory;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    CircuitBreaker circuitBreaker = circuitBreakerFactory.create("claimscircuitbreaker");
    HttpServletRequest req = (HttpServletRequest) request;
    String authorization = req.getHeader("Authorization");

    if (authorization != null) {
      String[] parts = authorization.split(" ");
      if (parts.length == 2) {
        String jwt = parts[1];
        if (!jwt.equals("undefined")) {
          String url = String.format("%s/?jwt=%s", authVerifyUrl, jwt);
          String claims = circuitBreaker.run(() -> template.getForObject(url, String.class), (Throwable e) -> {
            log.trace("Error from claims: {}", e.getMessage());
            return "Error: " + e.getMessage();
          });
          if (claims.contains("Error:")) {
            Map<String, String> map = new HashMap<>();
            map.put("error", claims);
            req.setAttribute("claims", map);
          } else {
            Map<String, Object> map = om.readValue(claims, typeRef);
            req.setAttribute("claims", map);
          }

        }
      }
    }
    chain.doFilter(req, response);
  }
}