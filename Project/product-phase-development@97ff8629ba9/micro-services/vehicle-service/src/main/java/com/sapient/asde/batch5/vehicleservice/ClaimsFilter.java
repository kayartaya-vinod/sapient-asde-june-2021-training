package com.sapient.asde.batch5.vehicleservice;

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

  private static final String FIRST_NAME = "firstName";
  private static final String USER_ID = "userId";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    String authorization = req.getHeader("Authorization");

    if (authorization != null) {
      String[] parts = authorization.split(" ");
      if (parts.length == 2) {
        String jwt = parts[1];
        if (!jwt.equals("undefined")) {
          String url = String.format("%s/?jwt=%s", authVerifyUrl, jwt);
          String claims = template.getForObject(url, String.class);
          Map<String, Object> map = om.readValue(claims, typeRef);
          req.setAttribute("claims", map);

          log.info("{}, {} accessing vehicle service", map.get(USER_ID).toString(), map.get(FIRST_NAME).toString());
        }
      }
    }
    chain.doFilter(req, response);
  }

}