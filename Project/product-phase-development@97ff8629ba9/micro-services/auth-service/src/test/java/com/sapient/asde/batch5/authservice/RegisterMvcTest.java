package com.sapient.asde.batch5.authservice;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.asde.batch5.authservice.entity.User;
import com.sapient.asde.batch5.authservice.service.AuthService;
import com.sapient.asde.batch5.authservice.service.ServiceException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.http.MediaType;
@SpringBootTest
@AutoConfigureMockMvc
class RegisterMvcTest {

    @Autowired
    MockMvc mockMvc; 
    @MockBean
    AuthService service;
@Autowired
ObjectMapper om;
    User newUser;
    User existingUser;
    List<String> auth;
    List<String> auth2;
    @BeforeEach
    void setup() throws ServiceException {
        newUser = new User("abcd1234", "Rohit","Sharma", "abc@gmail.com", "password", "customer",false);
        existingUser = new User("abcd1234", "Manvendra","Singh", "manvendra@google", "password", "customer",true);
        auth = Arrays.asList(JwtUtil.createDayExpireToken(existingUser.getId(), existingUser.getFirstName(),
                existingUser.getEmail(), existingUser.getUserType()), existingUser.getFirstName(),existingUser.getId(),existingUser.getEmail());
        auth2 = Arrays.asList(JwtUtil.createDayExpireToken(newUser.getId(), newUser.getFirstName(),
                newUser.getEmail(), newUser.getUserType()), newUser.getFirstName(),newUser.getId(),newUser.getEmail());
    }

    @Test
    void shouldRegisterValidUser() throws Exception {
        Mockito.when(service.register(any(User.class))).thenReturn(auth2);
        String url = "/api/auth/register";
        mockMvc.perform(post(url).content(asJsonString(newUser)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowExceptionForExistingUserRegister() throws Exception {
        Mockito.when(service.register(any(User.class))).thenThrow(new ServiceException("User already exists"));
        String url = "/api/auth/register";
        mockMvc.perform(post(url).content(asJsonString(existingUser)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

   String asJsonString( Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}