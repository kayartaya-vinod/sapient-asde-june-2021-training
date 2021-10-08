package com.sapient.asde.batch5.authservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
 class UpdatePasswordMvcTest {
    @Autowired
    ObjectMapper om;
    @Autowired
    MockMvc mockMvc; 
    @MockBean
    AuthService service;
    User newUser;
    User existingUser;
    List<String> auth;
    List<String> auth2;
    String jwt;
    String invalidJwt;
    String password;
    String token;
    @BeforeEach
    void setup() throws ServiceException {
        newUser = new User("abcd1234", "Rohit","Sharma", "abc@gmail.com", "password", "customer",false);
        existingUser = new User("abcd1234", "Manvendra","Singh", "manvendra@google", "password", "customer",true);
        token=JwtUtil.createDayExpireToken(existingUser.getId(), existingUser.getFirstName(),
        existingUser.getEmail(), existingUser.getUserType());
        auth = Arrays.asList(token, existingUser.getFirstName(),existingUser.getId(),existingUser.getEmail());
        auth2 = Arrays.asList(JwtUtil.createDayExpireToken(newUser.getId(), newUser.getFirstName(),
                newUser.getEmail(), newUser.getUserType()), newUser.getFirstName(),newUser.getId(),newUser.getEmail());
        jwt="jwt "+token;  
invalidJwt="jwt"+"invalid";
        Mockito.when(service.updatePassword(token,password)).thenReturn(auth2);
        Mockito.when(service.updatePassword("invalid",password)).thenThrow(new ServiceException());
    }

    @Test
  void shouldUpdatePassword() throws Exception {
    String url = "/api/auth/update-password";
    Map<String, Object> payload = new HashMap<>();
    payload.put("password", password);

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).header("Authorization", jwt).content(om.writeValueAsString(payload)))
        .andExpect(status().isOk());
  }
  
  @Test
  void shouldSendBadRequestStatusOnIncorrectTokenPassword() throws Exception {
    String url = "/api/auth/update-password";
    Map<String, Object> payload = new HashMap<>();
    payload.put("password", password);

    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).header("Authorization", invalidJwt).content(om.writeValueAsString(payload)))
        .andExpect(status().isBadRequest());
  }
}
